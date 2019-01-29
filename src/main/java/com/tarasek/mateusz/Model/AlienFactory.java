package com.tarasek.mateusz.Model;

import javax.swing.*;
import java.awt.*;

/**
 * @author Mateusz Tarasek
 * Simple Aliens Factory.
 */

class AlienFactory {

    enum ALIEN_NAME {
        ALIEN_LVL1,
        ALIEN_LVL2,
        ALIEN_LVL3,
        ALIEN_LVL4,
        ALIEN_LVL5,
        ALIEN_BOSS
    }

    /**
    *This function generates new alien
    *@return new generated Aliens object */

    Alien generateAlien(ALIEN_NAME alien_sprite, int x, int y){
        switch (alien_sprite){
            case ALIEN_LVL1:
                return new AlienLVL1(x, y);

            case ALIEN_LVL2:
                return new AlienLVL2(x, y);

            case ALIEN_LVL3:
                return new AlienLVL3(x, y);

            case ALIEN_LVL4:
                return new AlienLVL4(x, y);

            case ALIEN_LVL5:
                return new AlienLVL5(x, y);

            case ALIEN_BOSS:
                return new AlienBoss(x, y);

            default:
                return null;
        }
    }
}
