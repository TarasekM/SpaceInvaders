package com.tarasek.mateusz;

import javax.swing.*;
import java.awt.*;

public class Player extends Sprite implements SharedVariables{

    private final int START_X = 216;
    private final int START_Y = 544;
    private final int width = 48;
    private final int height = 48;

    private final String playerSprite = "src/Sprites/DODO_SpaceShip.png";

    public Player(){
        initPLayer();
    }

    private void initPLayer(){
        ImageIcon imageIcon = new ImageIcon(playerSprite);
        setImage(imageIcon.getImage());
        setX(START_X);
        setY(START_Y);
    }

    public void move(int x, int y){

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
