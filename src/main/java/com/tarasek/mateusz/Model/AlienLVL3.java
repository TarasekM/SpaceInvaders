package com.tarasek.mateusz.Model;

/**
 * @author Mateusz Tarasek
 * Model for AlienLVL3
 */
class AlienLVL3 extends Alien {

    /**
     * Default dimensions of alien is 48x48 pixels.
     * For flexibility you can create alien with other width and height.
     */

    AlienLVL3(int x, int y){
        super(x,y,48,48,"/ALIEN_LVL3.png");
        setHP(3);
        setSpeed(1.4f);
    }

    AlienLVL3(int x, int y, int width, int height){
        super(x, y, width, height,"/ALIEN_LVL3.png");
        setHP(3);
        setSpeed(1.4f);
    }

}
