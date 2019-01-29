package com.tarasek.mateusz.Model;

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

    AlienLVL1(int x, int y){
        super(x,y,48,48, "/ALIEN_LVL1.png");
        setHP(1);
        setSpeed(1);
    }

    AlienLVL1(int x, int y, int width, int height){
        super(x, y, width, height,"/ALIEN_LVL1.png");
        setHP(1);
        setSpeed(1);
    }

}
