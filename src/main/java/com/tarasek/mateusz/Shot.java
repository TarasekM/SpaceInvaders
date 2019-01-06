package com.tarasek.mateusz;

import javax.swing.*;

public class Shot extends Sprite{

    private final String shotSprite = "";
    private final int heightSpace = 10;

    public Shot(int x, int y){
        initShot(x, y);
    }

    private void initShot(int x, int y){
        ImageIcon imageIcon = new ImageIcon(shotSprite);
        setImage(imageIcon);
        setX(x);
        setY(y + heightSpace);
    }
}
