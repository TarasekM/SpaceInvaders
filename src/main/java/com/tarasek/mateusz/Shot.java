package com.tarasek.mateusz;

import javax.swing.*;

public class Shot extends Sprite{

    private final String shotSprite = "src/Sprites/DODO_SHOT.png";
    private final int widthSpace = 19;
    private final int heightSpace = 2;

    public Shot(int x, int y){
        initShot(x, y);
    }

    private void initShot(int x, int y){
        ImageIcon imageIcon = new ImageIcon(shotSprite);
        setImage(imageIcon.getImage());
        setX(x + widthSpace);
        setY(y + heightSpace);
    }
}
