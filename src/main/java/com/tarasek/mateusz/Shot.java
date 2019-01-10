package com.tarasek.mateusz;

import javax.swing.*;
import java.awt.geom.Ellipse2D;

public class Shot extends Sprite implements SharedVariables{

    private final int RADIUS = 5;
    private Ellipse2D.Float collider;

    private final String shotSprite = "src/Sprites/DODO_SHOT.png";

    public Shot(int x, int y){
        initShot(x, y);
    }

    private void initShot(int x, int y){
        ImageIcon imageIcon = new ImageIcon(shotSprite);
        setImage(imageIcon.getImage());
        setX(x + SHOT_WIDTH_SPACE);
        setY(y + SHOT_HEIGHT_SPACE);
        collider = new Ellipse2D.Float(this.x-1, this.y-1, RADIUS*2, 2*RADIUS);
    }

    public void moveUpwards(){
        setY((int)getY() - SHOT_SPEED);
        collider.y = (int)getY() - SHOT_SPEED;
    }

    public Ellipse2D.Float getCollider(){
        return collider;
    }

}
