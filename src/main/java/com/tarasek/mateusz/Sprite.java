package com.tarasek.mateusz;

import java.awt.Image;

public class Sprite {

    private boolean visible;
    private Image image;
    protected int x;
    protected int y;
    protected boolean exploding;
    protected int dx;

    public Sprite(){
        visible = true;
        image = null;
        x = 0;
        y = 0;
        exploding = false;
    }

    public void explode(){
        visible = false;
    }

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setExplode(boolean exploding) {
        this.exploding = exploding;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isExploding() {
        return exploding;
    }
}
