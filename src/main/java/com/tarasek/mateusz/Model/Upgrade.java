package com.tarasek.mateusz.Model;

import javax.swing.*;
import java.awt.geom.Ellipse2D;

public abstract class Upgrade extends Ellipse2D.Float {

    private ImageIcon sprite;
    private float value;
    private float speed;

    Upgrade(int x, int y, int radius, float value, String name){
        super(x,y,radius,radius);
        this.value = value;
        this.speed = 2;
        setSprite(name);
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public ImageIcon getSprite() {
        return sprite;
    }

    public void setSprite(String name){
        try{
            this.sprite = new ImageIcon(getClass().getResource(name));
        }catch (NullPointerException e){
            this.sprite = null;
        }
    }

    public void setSprite(ImageIcon sprite) {
        this.sprite = sprite;
    }
}
