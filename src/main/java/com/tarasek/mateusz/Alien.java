package com.tarasek.mateusz;

import javax.swing.*;

public class Alien extends Sprite{

    private final String alienSprite = "src/Sprites/DODO_ALIEN.png";
    private Bomb bomb;

    public Alien(int x, int y){
        initAlien(x, y);
    }

    private void initAlien(int x, int y){
        bomb = new Bomb(x, y);
        ImageIcon imageIcon = new ImageIcon(alienSprite);
        setImage(imageIcon.getImage());

        this.x = x;
        this.y = y;
    }

    public void move(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Bomb getBomb() {

        return bomb;
    }

}
