package com.tarasek.mateusz;

import javax.swing.*;

public class Bomb extends Sprite {

    private final String bombSprite = "";
    private boolean destroyed;

    public Bomb(int x,int y){
        initBomb(x, y);
    }

    private void initBomb(int x, int y){
        setDestroyed(false);
        ImageIcon imageIcon = new ImageIcon(bombSprite);
        this.x = x;
        this.y = y;
        setImage(imageIcon);

    }

    private void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
    }

    public boolean isDestroyed(){
        return destroyed;
    }
}
