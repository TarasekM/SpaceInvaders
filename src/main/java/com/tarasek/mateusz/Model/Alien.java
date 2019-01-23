package com.tarasek.mateusz.Model;

import javax.swing.*;
import java.awt.geom.Rectangle2D;

/**
 * @author Mateusz Tarasek
 * Abstract class for Alien.
 */

public abstract class Alien extends Rectangle2D.Float {

    private Bomb bomb;
    private ImageIcon sprite;
    private int HP;
    private float step;
    private float speed;
    private boolean moving;
    private boolean visible;

    Alien(int x, int y, int width, int height) {
        super(x, y, width, height);
        bomb = new Bomb(x - 3 + width/2 , y + height);
        visible = true;
        moving = false;
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

    public void setSprite(ImageIcon sprite) {
        this.sprite = sprite;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public float getStep() {
        return step;
    }

    public void setStep(float step) {
        this.step = step;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }
}
