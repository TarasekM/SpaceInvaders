package com.tarasek.mateusz.Model;

/**
 * @author Mateusz Tarasek
 * Model for AlienLVL4
 */
class AlienLVL4 extends Alien {

    /**
     * Default dimensions of alien is 48x48 pixels.
     * For flexibility you can create alien with other width and height.
     */

    AlienLVL4(int x, int y){
        super(x,y,48,48,"/ALIEN_LVL4.png");
        setHP(6);
        setSpeed(1.6f);
    }

    AlienLVL4(int x, int y, int width, int height){
        super(x, y, width, height,"/ALIEN_LVL4.png");
        setHP(6);
        setSpeed(1.6f);
    }

}
