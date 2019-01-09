package com.tarasek.mateusz;

import javax.swing.*;

public class Bomb extends Sprite implements SharedVariables{

    private final String bombSprite = "";
    private boolean destroyed;

    public Bomb(int x,int y){
        initBomb(x, y);
    }

    private void initBomb(int x, int y){
        setDestroyed(false);
        ImageIcon imageIcon = new ImageIcon(bombSprite);
        setX(x);
        setY(y);
        setImage(imageIcon.getImage());

    }

    void moveDownwards(){
        setY(getY() + BOMB_SPEED);
    }

    void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
    }

    public boolean isDestroyed(){
        return destroyed;
    }

}
