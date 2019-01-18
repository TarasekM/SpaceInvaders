package com.tarasek.mateusz.model;

import javax.swing.*;

/**
 * @author Mateusz Tarasek
 * Model for AlienLVL2
 */
class AlienLVL2 extends Alien {

    /**
     * Default dimensions of alien is 48x48 pixels.
     * For flexibility you can create alien with other width and height.
     */

    AlienLVL2(int x, int y, ImageIcon sprite){
        super(x,y,48,48);
        setSprite(sprite);

        setHP(2);
        setSpeed(1.2f);
    }

    AlienLVL2(int x, int y, int width, int height, ImageIcon sprite){
        super(x, y, width, height);
        setSprite(sprite);
        setHP(2);
        setSpeed(1.2f);
    }

}
