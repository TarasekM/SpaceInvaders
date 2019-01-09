package com.tarasek.mateusz;

import javax.swing.*;

public class Shot extends Sprite implements SharedVariables{

    private final String shotSprite = "src/Sprites/DODO_SHOT.png";

    public Shot(int x, int y){
        initShot(x, y);
    }

    private void initShot(int x, int y){
        ImageIcon imageIcon = new ImageIcon(shotSprite);
        setImage(imageIcon.getImage());
        setX(x + SHOT_WIDTH_SPACE);
        setY(y + SHOT_HEIGHT_SPACE);
    }

    public void moveUpwards(){
        setY(getY() - SHOT_SPEED);
    }

}
