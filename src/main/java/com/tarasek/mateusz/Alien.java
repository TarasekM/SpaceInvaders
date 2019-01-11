package com.tarasek.mateusz;

import javax.swing.*;

public class Alien extends Sprite{

    private final int ALIEN_WIDTH = 48;
    private final int ALIEN_HEIGHT = 48;
    private final String alienSprite = "src/Sprites/DODO_ALIEN.png";
    private Bomb bomb;

    public Alien(int x, int y){
        initAlien(x, y);
    }

    private void initAlien(int x, int y){
        bomb = new Bomb(x - 3 + ALIEN_WIDTH/2 , y + ALIEN_HEIGHT);
        ImageIcon imageIcon = new ImageIcon(alienSprite);
        setImage(imageIcon.getImage());
        width = ALIEN_WIDTH;
        height = ALIEN_HEIGHT;
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

    public void setBombCoordinates(){
       bomb.setX((int) this.x - 3 + ALIEN_WIDTH/2);
       bomb.setY((int) this.y + ALIEN_HEIGHT);
    }

}
