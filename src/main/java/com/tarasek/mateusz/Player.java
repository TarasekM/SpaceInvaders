package com.tarasek.mateusz;

import javax.swing.*;

public class Player extends Sprite implements SharedVariables{

    private final int START_X = 216;
    private final int START_Y = 544;
    private final int PLAYER_WIDTH = 48;
    private final int PLAYER_HEIGHT = 48;

    public Player(){
        initPLayer();
    }

    private void initPLayer(){
        try{
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("DODO_SpaceShip.png"));
            setImage(imageIcon.getImage());
        }catch (NullPointerException e){
            System.out.println(e);
        }

        setX(START_X);
        setY(START_Y);
        width = PLAYER_WIDTH;
        height = PLAYER_HEIGHT;

    }

    public void move(float x, float y){

        if (x >= BOARD_WIDTH - width/2){
            x = BOARD_WIDTH - width/2;
        }

        if (y >= BOARD_HEIGHT - height/2){
            y = BOARD_HEIGHT - height/2;
        }

        if (x <= width/2){
            x = width/2;
        }

        if(y <= height/2){
            y = height/2;
        }

        this.x = x - width/2;
        this.y = y - width/2;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
