package com.tarasek.mateusz.Model;

import com.tarasek.mateusz.SharedVariables;

import javax.swing.*;
import java.awt.geom.Ellipse2D;

public class Shot extends Sprite implements SharedVariables {

    private final int RADIUS = 5;
    private Ellipse2D.Float collider;

    public Shot(int x, int y){
        initShot(x, y);
    }

    private void initShot(int x, int y){
        try{
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/DODO_SHOT.png"));
            setImage(imageIcon.getImage());
        }catch (NullPointerException e){
            System.out.println(e);
        }

        setX(x + SHOT_WIDTH_SPACE);
        setY(y + SHOT_HEIGHT_SPACE);
        collider = new Ellipse2D.Float(this.x-1, this.y-1, RADIUS*2, 2*RADIUS);
    }

    public void moveUpwards(float speed){
        setY((int)getY() - speed);
        collider.y = (int)getY() - speed;
    }

    public Ellipse2D.Float getCollider(){
        return collider;
    }

}
