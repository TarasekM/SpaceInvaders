package com.tarasek.mateusz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Board extends JPanel implements Runnable, SharedVariables {

    private Dimension d;
    private ArrayList<Alien> aliens;
    private Player player;

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
                System.out.println("Mouse: " + e.getX()+ ", " + e.getY());
                player.move(e.getX(),e.getY());
                System.out.println("Player: " + player.getX()+ ", " + player.getY());

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

        if (animator == null || !inGame) {

            animator = new Thread(this);
            animator.start();
        }
    }

    public void drawPlayer(Graphics g) {

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isExploding()) {

            player.explode();
            inGame = false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        drawPlayer(g);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void run() {

        while (inGame) {
            repaint();
        }

    }

    @Override
    public void addNotify() {

        super.addNotify();
        gameInit();
    }
}
