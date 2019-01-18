package com.tarasek.mateusz.model;

import javax.swing.*;

/**
 * @author Mateusz Tarasek
 * Model for AlienLVL3
 */
class AlienLVL3 extends Alien {

    /**
     * Default dimensions of alien is 48x48 pixels.
     * For flexibility you can create alien with other width and height.
     */

    AlienLVL3(int x, int y, ImageIcon sprite){
        super(x,y,48,48,sprite);
        setHP(3);
        setSpeed(1.4f);
    }

    AlienLVL3(int x, int y, int width, int height, ImageIcon sprite){
        super(x, y, width, height, sprite);
        setHP(3);
        setSpeed(1.4f);
    }

}
