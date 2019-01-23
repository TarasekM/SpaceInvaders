package com.tarasek.mateusz.Model;

import org.junit.jupiter.api.Test;

class AlienFactoryTest {
    private AlienFactory alienFactory = new AlienFactory();


    @Test
    void generateAlienLVL1() {
        alienFactory.generateAlien(AlienFactory.ALIEN_NAME.ALIEN_LVL1, 0,0);

    }

    @Test
    void generateAlienLVL2() {
        alienFactory.generateAlien(AlienFactory.ALIEN_NAME.ALIEN_LVL2, 0,0);

    }

    @Test
    void generateAlienLVL3() {
        alienFactory.generateAlien(AlienFactory.ALIEN_NAME.ALIEN_LVL3, 0,0);

    }

    @Test
    void generateAlienLVL4() {
        alienFactory.generateAlien(AlienFactory.ALIEN_NAME.ALIEN_LVL4, 0,0);

    }

    @Test
    void generateAlienLVL5() {
        alienFactory.generateAlien(AlienFactory.ALIEN_NAME.ALIEN_LVL5, 0,0);

    }
    @Test

    void generateAlienBOSS() {
        alienFactory.generateAlien(AlienFactory.ALIEN_NAME.ALIEN_BOSS, 0,0);
    }
}