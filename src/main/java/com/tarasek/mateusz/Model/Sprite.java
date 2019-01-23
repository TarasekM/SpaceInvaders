package com.tarasek.mateusz.Model;

import java.awt.Image;
import java.awt.geom.Rectangle2D;

public class Sprite extends Rectangle2D.Float {

    private boolean visible;
    private Image image;
    protected boolean exploding;

    public Sprite(){
        visible = true;
        image = null;
        this.x = 0;
        this.y = 0;
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

    public Image getImage() {
        return image;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setExplode(boolean exploding) {
        this.exploding = exploding;
    }

    public boolean isExploding() {
        return exploding;
    }

}
