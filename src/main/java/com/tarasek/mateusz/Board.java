package com.tarasek.mateusz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Board extends JPanel implements Runnable, SharedVariables {

    private Dimension d;
    private ArrayList<Alien> aliens = new ArrayList<>();
    private ArrayList<Shot> shots = new ArrayList<>();
    private Player player;

    private final int FIRST_ALIEN_X = 48;
    private final int FIRST_ALIEN_Y = 48;
    private final int TICKS_PER_SECOND = 50;
    private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;

    private boolean inGame = false;
    private String message = "Click to Start!";
    private Thread animator;

    Board() {

        initBoard();
    }

    private void initBoard() {

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if(inGame){
                    player.move(e.getX(), e.getY());
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!inGame){
                    gameInit();
                }
            }
        });

        setFocusable(true);
        d = new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
        setBackground(Color.black);
        setDoubleBuffered(true);
    }

    private void gameInit() {
        player = new Player();
        inGame = true;

        for (int y = 4; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                Alien alien = new Alien(FIRST_ALIEN_X + 48 * x,
                        FIRST_ALIEN_Y + 48 * y);
                aliens.add(alien);
            }
        }

        aliens.get(0).setMoving(true);
        calculateStep(aliens.get(0));

        if (animator == null) {

            animator = new Thread(this);
            animator.start();
        }

        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");
        setCursor(blankCursor);
    }

    private void drawMessage(Graphics2D graphics2D){
        graphics2D.setColor(Color.green);
        graphics2D.setFont(new Font("Arial", Font.BOLD, 50));
        graphics2D.drawString(message, 50,320);
    }

    private void drawPlayer(Graphics graphics) {
        graphics.drawImage(player.getImage(), (int) player.getX(), (int) player.getY(), this);
    }

    private void drawAliens(Graphics graphics) {

        Iterator<Alien> alienIterator = aliens.iterator();
        Bomb bomb;

        while (alienIterator.hasNext()) {
            Alien alien = alienIterator.next();
            bomb = alien.getBomb();
            if (alien.isVisible()) {
                graphics.drawImage(alien.getImage(), (int) alien.getX(), (int) alien.getY(), this);
            }

            if (alien.isMoving()) {
                if(alien.getStep() == 0){
                    calculateStep(alien);
                }
                alien.setX((float) (alien.getX() + ALIEN_SPEED * alien.getStep()));
                alien.setY((float) (alien.getY() + ALIEN_SPEED));
            }

            if (!bomb.isDestroyed()) {
                bomb.moveDownwards();
                graphics.drawImage(bomb.getImage(), (int) bomb.getX(), (int) bomb.getY(), this);
            }
        }
    }

    private void calculateStep(Alien alien){
        float step = (float) ((alien.getX() - player.getX()) / (alien.getY() - player.getY()));
        if (step > ALIEN_SPEED){
            step = ALIEN_SPEED;
        }else if(step < -ALIEN_SPEED){
            step = -ALIEN_SPEED;
        }
        alien.setStep(step);
    }

    private void drawShots(Graphics graphics) {
        if (!shots.isEmpty()) {
            for (Shot shot : shots) {
                graphics.drawImage(shot.getImage(), (int) shot.getX(), (int) shot.getY(), this);
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

        }else {
            drawMessage((Graphics2D) graphics);
        }

        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    private void playerAlienCollisionDetection(){

        for (Alien alien : aliens) {
            if (alien.intersects(player) || alien.getBomb().intersects(player)) {
                gameOver("You lost :(");
                return;
            }
        }
    }

    private void gameOver(String message){
        inGame = false;
        animator = null;
        shots.clear();
        aliens.clear();
        this.message = message;
        setCursor(Cursor.getDefaultCursor());
    }

    private void alienBombShots(){
        Random random = new Random();
        Bomb bomb;
        Iterator<Alien> alienIterator = aliens.iterator();

        while (alienIterator.hasNext()) {
            Alien alien = alienIterator.next();
            bomb = alien.getBomb();
            int value = random.nextInt(100 * aliens.size()) + 1;

            if (value > 0 && value <= 5){
                alien.getBomb().setDestroyed(false);
            }

            if (bomb.getY() > BOARD_HEIGHT){
                bomb.setDestroyed(true);
                alien.setBombCoordinates();

                if (!alien.isVisible()){
                    alienIterator.remove();
                }
            }

            if (alien.getY() > BOARD_HEIGHT || alien.getX() < 0 - alien.width || alien.getX() > BOARD_WIDTH){
                alien.setVisible(false);
                alien.setBombCoordinates();
                if (alien == aliens.get(0)){
                    aliens.get(1).setMoving(true);
                }else{
                    aliens.get(0).setMoving(true);
                }
            }
        }
    }

    private void shotCollisionDetection() {
        if (shots.isEmpty() || aliens.isEmpty()) {
            return;
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

                if (collision && alien.isVisible()) {
                    shotIterator.remove();
                    alien.setVisible(false);

                    if (aliens.size() >= 2){
                        if (alien == aliens.get(0)){
                            aliens.get(1).setMoving(true);
                        }else{
                            aliens.get(0).setMoving(true);
                        }
                        return;
                    }
                }

                if (alien.getBomb().isDestroyed() && !alien.isVisible()){
                    alienIterator.remove();
                }
            }

            if (shot.getY() < -10){
                shotIterator.remove();
                return;
            }
        }

    }


    @Override
    public void run() {

        long nextGameTick = System.currentTimeMillis();
        long nextShot = System.currentTimeMillis();
        long sleepTime, shootSleep = 800;
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

        if (aliens.isEmpty() && inGame) {
            gameOver("You won!");
        }

        if (isShooting) {
            shots.add(new Shot((int) player.getX(), (int) player.getY()));
        }
        shotCollisionDetection();
        playerAlienCollisionDetection();
        alienBombShots();
    }
}
