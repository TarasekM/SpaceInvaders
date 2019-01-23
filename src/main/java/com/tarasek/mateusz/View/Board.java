package com.tarasek.mateusz.View;

import com.tarasek.mateusz.Controller.SpaceInvadersEngine;
import com.tarasek.mateusz.Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class Board extends JPanel {

    Dimension d;
    SpaceInvadersEngine controller;
    public Point mousePos;

    int playerLives;
    long score;

    ImageIcon hearth;
    Player player;
    ArrayList<Shot> shots;
    ArrayList<Alien> aliens;
    String message;

    private final Color LIGHT_GREEN = new Color(48, 255, 135);

    public Board(int width, int height){
        d = new Dimension(width,height);
        setSize(d);
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);
        mousePos = new Point(0,0);

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                mousePos.x = e.getX();
                mousePos.y = e.getY();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                controller.mouseClicked(true);
            }

            @Override
            public void mouseReleased(MouseEvent e){
                controller.mouseClicked(false);
            }
        });

    }

    public void showCursor(){
        setCursor(Cursor.getDefaultCursor());
    }

    public void hideCursor(){
        Cursor marker = new Cursor((Cursor.MOVE_CURSOR));
        setCursor(marker);

    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, d.width, d.height);

        drawPoints((Graphics2D) graphics);
        if (controller.modelIsInGame()) {
            drawPlayer(graphics);
            drawAliensAndBombs(graphics);
            drawShots(graphics);
            drawHearths(graphics);
            drawPlayer(graphics);
        }else {
            drawMessage((Graphics2D) graphics);
        }

        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
    }

    private void drawShots(Graphics graphics) {
        if (!shots.isEmpty()) {
            for (Shot shot : shots) {
                graphics.drawImage(shot.getImage(), (int) shot.getX(), (int) shot.getY(), this);
            }
        }
    }

    private void drawHearths(Graphics graphics){
        for (int i = 0; i < playerLives; i++){
            graphics.drawImage(hearth.getImage(), 480 - (i + 1) * 28, 3,this );
        }
    }

    private void drawPoints(Graphics2D graphics2D){
        String msg = String.format("Points: " + score);
        graphics2D.setColor(LIGHT_GREEN);
        graphics2D.setFont(new Font("Arial", Font.BOLD, 20));
        graphics2D.drawString(msg,5,20);
    }

    private void drawPlayer(Graphics graphics) {
        graphics.drawImage(player.getImage(), (int) player.getX(), (int) player.getY(), this);
    }

    private void drawAliensAndBombs(Graphics graphics) {

        Iterator<Alien> alienIterator = aliens.iterator();
        Bomb bomb;

        while (alienIterator.hasNext()) {
            Alien alien = alienIterator.next();
            bomb = alien.getBomb();

            if (alien.isVisible()) {
                graphics.drawImage(alien.getSprite().getImage(), (int) alien.getX(), (int) alien.getY(), this);
            }

            if (!bomb.isDestroyed()) {
                graphics.drawImage(bomb.getImage(), (int) bomb.getX(), (int) bomb.getY(), this);
            }
        }
    }

    private void drawMessage(Graphics2D graphics2D){
        graphics2D.setColor(Color.green);
        graphics2D.setFont(new Font("Arial", Font.BOLD, 50));
        graphics2D.drawString(message, 50,320);
    }

    public void updateState(int playerLives, long score, ImageIcon hearth, Player player, ArrayList<Shot> shots,
                            ArrayList<Alien> aliens, String message){
        this.playerLives = playerLives;
        this.score = score;

        this.hearth = hearth;
        this.player = player;
        this.shots = shots;
        this.aliens = aliens;
        this.message = message;
    }

    public void setController(SpaceInvadersEngine controller) {
        this.controller = controller;
    }
}
