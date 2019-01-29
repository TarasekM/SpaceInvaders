package com.tarasek.mateusz.Model;

/**
 * @author Mateusz Tarasek
 * Model for AlienLVL3
 */
class AlienLVL5 extends Alien {

    /**
     * Default dimensions of alien is 48x48 pixels.
     * For flexibility you can create alien with other width and height.
     */

    AlienLVL5(int x, int y){
        super(x,y,48,48,"/ALIEN_LVL5.png");
        setHP(5);
        setSpeed(1.8f);
    }

    AlienLVL5(int x, int y, int width, int height){
        super(x, y, width, height,"/ALIEN_LVL4.png");
        setHP(5);
        setSpeed(1.8f);
    }

}
