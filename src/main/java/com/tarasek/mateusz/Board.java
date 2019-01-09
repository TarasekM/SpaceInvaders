package com.tarasek.mateusz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Board extends JPanel implements Runnable, SharedVariables {

    private Dimension d;
    private ArrayList<Alien> aliens = new ArrayList<>();
    private ArrayList<Shot> shots = new ArrayList<>();
    private Player player;

    private final int FIRST_ALIEN_X = 48;
    private final int FIRST_ALIEN_Y = 48;

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
                player.move(e.getX(),e.getY());
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

        for (int y = 0; y < 5; y++){
            for (int x = 0; x < 8; x++){
                Alien alien = new Alien(FIRST_ALIEN_X + 48 * x,
                        FIRST_ALIEN_Y +  48 * y);
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

            graphics.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isExploding()) {

            player.explode();
            inGame = false;
        }
    }

    public void drawAliens(Graphics graphics){

        for(Alien alien : aliens){
            if(alien.isVisible()){
                graphics.drawImage(alien.getImage(), alien.getX(), alien.getY(),this);
            }
            if (alien.isExploding()){
                alien.explode();
            }
        }
    }

    public void drawShots(Graphics graphics){

        if (!shots.isEmpty()){
            for(Shot shot : shots){
                if(shot.isVisible()){
                    graphics.drawImage(shot.getImage(), shot.getX(), shot.getY(),this);
                }
                if (shot.isExploding()){
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

        if (inGame){
            drawPlayer(graphics);
            drawAliens(graphics);
            drawShots(graphics);
        }

        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep = 2000;
        beforeTime = System.currentTimeMillis();

        while (inGame) {
            timeDiff = System.currentTimeMillis() - beforeTime;



            if (sleep - timeDiff < 0){
                shots.add(new Shot(player.getX(), player.getY()));
                sleep += 2000;
            }

            repaint();
        }

    }

    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }
}
