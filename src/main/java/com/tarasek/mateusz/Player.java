package com.tarasek.mateusz;

import javax.swing.*;

public class Player extends Sprite implements SharedVariables{

    private final int START_X = 240;
    private final int START_Y = 520;
    private final int width = 64;

    private final String playerSprite = "src/img/player.png";

    public Player(){
        initPLayer();
    }

    private void initPLayer(){
        ImageIcon imageIcon = new ImageIcon(playerSprite);
        setImage(imageIcon);

    }
}
