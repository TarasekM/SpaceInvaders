package com.tarasek.mateusz.Model;

public class UpgradeFactory {

    Upgrade generateUpgrade(int choice, int x, int y){

        switch (choice){
            case 1:
                return new UpgradeShotSpeed(x, y);

            case 2:
                return new UpgradeShotCounter(x, y);

            case 3:
                return new UpgradeShotDamage(x, y);

            case 4:
                return new UpgradePlayerSpeed(x, y);

            case 5:
                return new UpgradeShootingFrequency(x, y);

            default:
                return new UpgradePlayerSpeed(x,y);
        }
    }

}
