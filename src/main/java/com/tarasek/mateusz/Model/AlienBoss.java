package com.tarasek.mateusz.Model;

import javax.swing.*;

/**
 * @author Mateusz Tarasek
 * Model for Alien_LVL3
 */
class AlienBoss extends Alien {

    /**
     * Default dimensions of alien boss is 192x96 pixels.
     * For flexibility you can create alien with other width and height.
     */

    AlienBoss(int x, int y, ImageIcon sprite){
        super(x,y,192,96);
        setSprite(sprite);

        setHP(20);
        setSpeed(0.8f);
    }

    AlienBoss(int x, int y, int width, int height, ImageIcon sprite){
        super(x, y, width, height);
        setSprite(sprite);
        setHP(20);
        setSpeed(0.8f);
    }

}
