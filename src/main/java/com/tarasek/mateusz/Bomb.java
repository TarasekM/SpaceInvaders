package com.tarasek.mateusz;

import javax.swing.*;

public class Bomb extends Sprite implements SharedVariables{

    private boolean destroyed;

    public Bomb(int x,int y){
        initBomb(x, y);
    }

    private void initBomb(int x, int y){
        setDestroyed(true);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("DODO_BOMB.png"));
        setX(x);
        setY(y);
        width = 12;
        height = 20;
        setImage(imageIcon.getImage());

    }

    void moveDownwards(){
        setY((int)getY() + BOMB_SPEED);
    }

    void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
    }

    public boolean isDestroyed(){
        return destroyed;
    }

}
