package com.tarasek.mateusz.Model;

/**
 * @author Mateusz Tarasek
 * Model for AlienLVL2
 */
class AlienLVL2 extends Alien {

    /**
     * Default dimensions of alien is 48x48 pixels.
     * For flexibility you can create alien with other width and height.
     */

    AlienLVL2(int x, int y){
        super(x,y,48,48, "/ALIEN_LVL2.png");
        setHP(2);
        setSpeed(1.2f);
    }

    AlienLVL2(int x, int y, int width, int height){
        super(x, y, width, height, "/ALIEN_LVL2.png");
        setHP(2);
        setSpeed(1.2f);
    }

}
