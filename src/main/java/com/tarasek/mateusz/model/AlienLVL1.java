package com.tarasek.mateusz.model;

import javax.swing.*;

/**
 * @author Mateusz Tarasek
 * Model for AlienLVL1
 */
class AlienLVL1 extends Alien {

    /**
     * Default dimensions of alien is 48x48 pixels.
     * For flexibility you can create alien with other width and height.
     */

    AlienLVL1(int x, int y, ImageIcon sprite){
        super(x,y,48,48,sprite);
        setHP(1);
        setSpeed(1);
    }

    AlienLVL1(int x, int y, int width, int height, ImageIcon sprite){
        super(x, y, width, height, sprite);
        setHP(1);
        setSpeed(1);
    }

}
