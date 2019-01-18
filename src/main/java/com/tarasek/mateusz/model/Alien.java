package com.tarasek.mateusz.model;

import com.tarasek.mateusz.Bomb;

import javax.swing.*;
import java.awt.geom.Rectangle2D;

/**
 * @author Mateusz Tarasek
 * Abstract class for Alien.
 */

public abstract class Alien extends Rectangle2D.Float {

    private Bomb bomb;
    private boolean moving;
    private ImageIcon sprite;
    private int HP;
    private float step;
    private float speed;

    Alien(int x, int y, int width, int height, ImageIcon sprite) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
        bomb = new Bomb(x - 3 + width/2 , y + height);
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    public void setBombCoordinates(){
        bomb.setX((int) this.x - 3 + this.width/2);
        bomb.setY((int) this.y + this.height);
    }

    public ImageIcon getSprite() {
        return sprite;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
