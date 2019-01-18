package com.tarasek.mateusz.model;

import javax.swing.*;

/**
 * @author Mateusz Tarasek
 * Model for AlienLVL4
 */
class AlienLVL4 extends Alien {

    /**
     * Default dimensions of alien is 48x48 pixels.
     * For flexibility you can create alien with other width and height.
     */

    AlienLVL4(int x, int y, ImageIcon sprite){
        super(x,y,48,48);
        setSprite(sprite);
        setHP(4);
        setSpeed(1.6f);
    }

    AlienLVL4(int x, int y, int width, int height, ImageIcon sprite){
        super(x, y, width, height);
        setSprite(sprite);
        setHP(4);
        setSpeed(1.6f);
    }

}
