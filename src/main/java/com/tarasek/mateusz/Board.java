package com.tarasek.mateusz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class Board extends JPanel implements Runnable, SharedVariables {

    private Dimension d;
    private ArrayList<Alien> aliens = new ArrayList<>();
    private ArrayList<Shot> shots = new ArrayList<>();
    private Player player;

    private final int FIRST_ALIEN_X = 48;
    private final int FIRST_ALIEN_Y = 48;
    private final int TICKS_PER_SECOND = 50;
    private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;

    private boolean inGame = true;
    private String message = "Game Over";
    private Thread animator;

    public Board() {

        initBoard();
    }

    private void initBoard() {

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
//                System.out.println("Mouse: " + e.getX()+ ", " + e.getY());
                player.move(e.getX(), e.getY());
//                System.out.println("Player: " + player.getX()+ ", " + player.getY());

            }

        });

        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);
        gameInit();
        setDoubleBuffered(true);
    }

    private void gameInit() {
        player = new Player();

        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 8; x++) {
                Alien alien = new Alien(FIRST_ALIEN_X + 48 * x,
                        FIRST_ALIEN_Y + 48 * y);
                aliens.add(alien);
            }
        }

        if (animator == null || !inGame) {

            animator = new Thread(this);
            animator.start();
        }
    }

    public void drawPlayer(Graphics graphics) {

        if (player.isVisible()) {

            graphics.drawImage(player.getImage(), (int) player.getX(), (int) player.getY(), this);
        }

        if (player.isExploding()) {

            player.explode();
            inGame = false;
        }
    }

    @SuppressWarnings("Duplicates")
    public void drawAliens(Graphics graphics) {

        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                graphics.drawImage(alien.getImage(), (int) alien.getX(), (int) alien.getY(), this);
            }
            if (alien.isExploding()) {
                alien.explode();
            }
        }
    }

    public void drawShots(Graphics graphics) {

        if (!shots.isEmpty()) {
            for (Shot shot : shots) {
                if (shot.isVisible()) {
                    graphics.drawImage(shot.getImage(), (int) shot.getX(), (int) shot.getY(), this);
                }
                if (shot.isExploding()) {
                    shot.explode();
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, d.width, d.height);

        if (inGame) {
            drawPlayer(graphics);
            drawAliens(graphics);
            drawShots(graphics);

        }

        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    @Override
    public void run() {

        long nextGameTick = System.currentTimeMillis();
        long nextShot = System.currentTimeMillis();
        long sleepTime, shootSleep = 2000;
        boolean isShooting = false;

        while (inGame) {

            if (shootSleep <= System.currentTimeMillis() - nextShot) {
                isShooting = true;
                nextShot = System.currentTimeMillis();
            }

            Update(isShooting);
            repaint();

            nextGameTick += SKIP_TICKS;
            sleepTime = nextGameTick - System.currentTimeMillis();

            if (sleepTime >= 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
            isShooting = false;

        }
    }

    private void Update(boolean isShooting) {

        if (aliens.isEmpty()) {
            inGame = false;
            message = "You won!";
        }

        if (isShooting) {
            shots.add(new Shot((int) player.getX(), (int) player.getY()));
        }

        collisionDetection();

    }

    public boolean collisionDetection() {
        // TODO collision detection

        if (shots.isEmpty() || aliens.isEmpty()) {
            return false;
        }

        Iterator<Shot> shotIterator = shots.iterator();


        boolean collision;
        while (shotIterator.hasNext()) {
            Shot shot = shotIterator.next();
            shot.moveUpwards();

            Iterator<Alien> alienIterator = aliens.iterator();
            while (alienIterator.hasNext()) {

                Alien alien = alienIterator.next();
                int alienWidth = alien.getImage().getWidth(this);
                int alienHeight = alien.getImage().getHeight(this);
                collision = shot.getCollider().intersects(alien.getX(), alien.getY(), alienWidth, alienHeight);

                if (collision) {
                    shotIterator.remove();
                    alienIterator.remove();
                    return true;
                }
            }

            if (shot.getY() < -10){
                shotIterator.remove();
                return false;
            }
        }

        return false;
    }
}
