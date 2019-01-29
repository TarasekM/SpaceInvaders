package com.tarasek.mateusz.Model;

/**
 * @author Mateusz Tarasek
 * Model for Alien_LVL3
 */
class AlienBoss extends Alien {

    /**
     * Default dimensions of alien boss is 192x96 pixels.
     * For flexibility you can create alien with other width and height.
     */

    AlienBoss(int x, int y){
        super(x,y,192,96,"/ALIEN_BOSS.png");

        setHP(20);
        setSpeed(0.8f);
    }

    AlienBoss(int x, int y, int width, int height){
        super(x, y, width, height,"/ALIEN_BOSS.png");
        setHP(20);
        setSpeed(0.8f);
    }

}
