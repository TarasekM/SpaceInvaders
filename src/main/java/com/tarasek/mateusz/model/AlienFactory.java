package com.tarasek.mateusz.model;

import javax.swing.*;

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

        ImageIcon imageIcon;
        switch (alien_sprite){
            case ALIEN_LVL1:
                try{
                    imageIcon = new ImageIcon(getClass().getResource("ALIEN_LVL1.png"));
                }catch (NullPointerException e){
                    imageIcon = null;
                }
                return new AlienLVL1(x, y, imageIcon);

            case ALIEN_LVL2:
                try{
                    imageIcon = new ImageIcon(getClass().getResource("ALIEN_LVL2.png"));
                }catch (NullPointerException e){
                    imageIcon = null;
                }
                return new AlienLVL2(x, y, imageIcon);

            case ALIEN_LVL3:
                try{
                    imageIcon = new ImageIcon(getClass().getResource("ALIEN_LVL3.png"));

                }catch (NullPointerException e){
                    imageIcon = null;
                }
                return new AlienLVL3(x, y, imageIcon);

            case ALIEN_LVL4:
                try{
                    imageIcon = new ImageIcon(getClass().getResource("ALIEN_LVL4.png"));

                }catch (NullPointerException e){
                    imageIcon = null;
                }

                return new AlienLVL4(x, y, imageIcon);

            case ALIEN_LVL5:
                try{
                    imageIcon = new ImageIcon(getClass().getResource("ALIEN_LVL5.png"));
                }catch (NullPointerException e){
                    imageIcon = null;
                }
                return new AlienLVL5(x, y, imageIcon);

            case ALIEN_BOSS:
                try{
                    imageIcon = new ImageIcon(getClass().getResource("ALIEN_BOSS.png"));

                }catch (NullPointerException e){
                    imageIcon = null;
                }
                return new AlienBoss(x, y, imageIcon);

            default:
                return null;
        }
    }
}
