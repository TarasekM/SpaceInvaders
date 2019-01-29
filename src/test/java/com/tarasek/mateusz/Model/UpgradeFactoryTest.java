package com.tarasek.mateusz.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpgradeFactoryTest {

    private UpgradeFactory upgradeFactory = new UpgradeFactory();

    @Test
    void generateShootSpeed() {
        UpgradeShotSpeed uss = (UpgradeShotSpeed) upgradeFactory.generateUpgrade(1, 0,0);
    }

    @Test
    void generateShotCounter() {
        UpgradeShotCounter usc = (UpgradeShotCounter) upgradeFactory.generateUpgrade(2, 0,0);
    }


    @Test
    void generateShotDamage() {
        UpgradeShotDamage usd = (UpgradeShotDamage) upgradeFactory.generateUpgrade(3, 0,0);
    }

    @Test
    void generateUpgradePlayerSpeed() {
        UpgradePlayerSpeed ups = (UpgradePlayerSpeed) upgradeFactory.generateUpgrade(4, 0,0);
    }

    @Test
    void generateShootingFrequency() {
        UpgradeShootingFrequency usf = (UpgradeShootingFrequency) upgradeFactory.generateUpgrade(5, 0,0);
    }


    @Test
    void generateDefault() {
        UpgradePlayerSpeed ups = (UpgradePlayerSpeed) upgradeFactory.generateUpgrade(-1, 0,0);
    }

}