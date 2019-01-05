package com.tarasek.mateusz;

import javax.swing.*;
import java.awt.*;

public class Player extends Sprite implements SharedVariables{

    private final int START_X = 240;
    private final int START_Y = 520;
    private final int width = 64;
    private final int heigth = 64;

    private final String playerSprite = "";

    public Player(){
        initPLayer();
    }

    private void initPLayer(){
        ImageIcon imageIcon = new ImageIcon(playerSprite);
        setImage(imageIcon);
        setX(START_X);
        setY(START_Y);
    }

    public void move(){
        int x, y;
        Point mousePosition = MouseInfo.getPointerInfo().getLocation();
        x = mousePosition.x;
        y = mousePosition.y;

        if (x >= BOARD_WIDTH - width){
            x = BOARD_WIDTH - width;
        }

        if (y >= BOARD_HEIGTH - heigth){
            y = BOARD_HEIGTH - heigth;
        }

        if (x <= width){
            x = width;
        }

        if(y <= heigth){
            y = heigth;
        }

        this.x = x;
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeigth() {
        return heigth;
    }
}
