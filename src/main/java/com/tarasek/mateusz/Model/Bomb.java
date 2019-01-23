package com.tarasek.mateusz.Model;

import com.tarasek.mateusz.SharedVariables;

import javax.swing.*;

public class Bomb extends Sprite implements SharedVariables {

    private boolean destroyed;

    public Bomb(int x,int y){
        initBomb(x, y);
    }

    private void initBomb(int x, int y){
        setDestroyed(true);
        try{
            ImageIcon imageIcon = new ImageIcon("src/main/resources/DODO_BOMB.png");
            setImage(imageIcon.getImage());
        }catch (NullPointerException e){
            setImage(null);
        }
        setX(x);
        setY(y);
        width = 12;
        height = 20;
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
