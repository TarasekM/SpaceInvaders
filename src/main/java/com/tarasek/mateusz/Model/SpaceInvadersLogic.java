package com.tarasek.mateusz.Model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Mateusz Tarasek
 * Main model for SpaceInvaders.
 */

public class SpaceInvadersLogic {

    final int FIRST_ALIEN_X = 27;
    final int FIRST_ALIEN_Y = 27;

    private int level;
    private int playerLives;
    private int boardWidth;
    private int boardHeight;
    private long score;
    private boolean inGame;

    private AlienFactory alienFactory;
    private UpgradeFactory upgradeFactory;
    private String message;
    private Player player;
    private ArrayList<Alien> aliens = new ArrayList<>();
    private ArrayList<Shot> shots = new ArrayList<>();
    private ArrayList<Upgrade> upgrades = new ArrayList<>();
    private ImageIcon hearth;


    public SpaceInvadersLogic(int width, int height){
        boardWidth = width;
        boardHeight = height;
        level = 1;
        playerLives = 3;
        score = 0;
        inGame = false;
        alienFactory = new AlienFactory();
        upgradeFactory = new UpgradeFactory();
        message = "Click to start!";
        player = new Player();
        initHearth();
    }

    public void startGame(){
        if (level <= 1){
            score = 0;
            player.resetBonus();
        }

        player.resetPosition();
        inGame = true;
        initAliens();
    }
    /**
     * Fill alien array with aliens adequate to level
     * */
    private void initAliens(){
        int xAxisSize = 8;
        int yAxisSize = 4;
        Alien alien;

        for (int y = yAxisSize; y >= 0; y--) {
            for (int x = 0; x < xAxisSize; x++) {
                alien = alienFactory.generateAlien(AlienFactory.ALIEN_NAME.values()[level-1],
                        FIRST_ALIEN_X + 53 * x, FIRST_ALIEN_Y + 53 * y);
                aliens.add(alien);
            }
        }
        aliens.get(0).setMoving(true);
        calculateStep(aliens.get(0));
    }
    /**
     * Load image for hearth.
     * */
    private void initHearth(){
        try{
            hearth = new ImageIcon(getClass().getResource("/HEARTH.png"));
        }catch (NullPointerException e){
            hearth = null;
        }
    }

    public void addStat(Upgrade upgrade){
        switch (upgrade.getName()){
            case "/PLAYER_SPEED.png":
                float speed = player.getPlayerSpeed();
                player.setPlayerSpeed(speed + upgrade.getValue());
                System.out.println("PSPEED");
                if (speed > 10){
                    player.setPlayerSpeed(10);
                }
                break;


            case "/SHOOTING_FREQUENCY.png":
                float frequency = player.getShootingFrequency();
                System.out.println("Frequency");
                if(frequency >= 800){
                    player.setShootingFrequency(800);
                }else{
                    player.setShootingFrequency(frequency + upgrade.getValue());
                }
                break;

            case "/SHOT_DAMAGE.png":
                System.out.println("PDAMAGE");
                float damage = player.getPlayerDamage();
                player.setPlayerDamage(damage + upgrade.getValue());
                break;
            case "/SHOT_SPEED.png":
                float shotSpeed = player.getShootSpeed();
                if(shotSpeed >= 8){
                    player.setShootSpeed(8);
                }else{
                    player.setShootSpeed(shotSpeed + upgrade.getValue());
                }
                break;


        }
    }

    public void upgradeCollisionDetection(){
        Iterator<Upgrade> upgradeIterator = upgrades.iterator();
        Upgrade upgrade;

        while(upgradeIterator.hasNext()){
            upgrade = upgradeIterator.next();

            if (upgrade.isVisible()){
                upgrade.y += upgrade.getSpeed();
            }

            if(upgrade.intersects(player)){
                addStat(upgrade);
                upgrade.setVisible(false);
            }

            if(!upgrade.isVisible()){
                upgradeIterator.remove();
            }
        }
    }

    public void addRandomUpgradeToGame(Alien alien){
        Random randomAlienBonusDrop = new Random();
        Random randomWhichBonusDrop = new Random();
        Upgrade upgrade;
        int valueAlienBonusDrop = randomAlienBonusDrop.nextInt(40);
        if (valueAlienBonusDrop < 40/8){
            upgrade = upgradeFactory.generateUpgrade(
                    randomWhichBonusDrop.nextInt(6),
                    (int) alien.getX(),
                    (int) alien.getY()
            );
            upgrade.setVisible(true);
            upgrades.add(upgrade);
        }
    }

    /**
     * Detect collision between player missile and alien ship.
     * If collision occurs set alien ship not visible.
     * When alien bomb is destroyed remove alien
     * */
    public void shotCollisionDetection() {
        if (shots.isEmpty() || aliens.isEmpty()) {
            return;
        }

        Iterator<Shot> shotIterator = shots.iterator();

        boolean collision;
        while (shotIterator.hasNext()) {
            Shot shot = shotIterator.next();
            shot.moveUpwards(player.getShootSpeed());

            Iterator<Alien> alienIterator = aliens.iterator();
            while (alienIterator.hasNext()) {

                Alien alien = alienIterator.next();
                collision = shot.getCollider().intersects(alien.getX(), alien.getY(), alien.width, alien.height);

                if (collision && alien.isVisible() && shot.isVisible()) {

                    alien.setHP(alien.getHP() - player.getPlayerDamage());
                    shot.setVisible(false);
                    if(alien.getHP() < 1){
                        alien.setVisible(false);
                        addRandomUpgradeToGame(alien);
                        score += 50 + (500 * level / (aliens.size() + 1));
                        if (aliens.size() >= 2){
                            if (alien == aliens.get(0)){
                                aliens.get(1).setMoving(true);
                            }else{
                                aliens.get(0).setMoving(true);
                            }
                            return;
                        }
                    }
                }

                if (alien.getBomb().isDestroyed() && !alien.isVisible()){
                    alienIterator.remove();
                }
            }
            if (shot.getY() < -10 || !shot.isVisible()){
                shotIterator.remove();
                return;
            }

        }
    }

    /**
     * Calculate alien speed for X axis.
     * (Alien remembers last player position before starts moving)
     * */
    private void calculateStep(Alien alien){
        float step = (float) ((alien.getX() - player.getX()) / (alien.getY() - player.getY()));
        if (step > alien.getSpeed()){
            step = alien.getSpeed();
        }else if(step < -alien.getSpeed()){
            step = -alien.getSpeed();
        }
        alien.setStep(step);
    }

    /**
     * Detect if player collide with alien. If so, game overs.
     * */
    public void playerAlienCollisionDetection(){

        for (Alien alien : aliens) {
            if (alien.getBomb().intersects(player)) {
                alien.getBomb().setDestroyed(true);
                alien.setBombCoordinates();
                if (playerLives > 1){
                    playerLives--;
                }else {
                    gameOver("You lost :(");
                    level = 1;
                }
                return;
            }else if(alien.intersects(player) && alien.isVisible()){
                gameOver("Alien Crush :(");
                level = 1;
                return;
            }
        }
    }

    /**
     * Behaviour when game ends.
     * Calculate points, clear variables.
     *
     * */
    public void gameOver(String message){
        playerLives--;
        score += playerLives * 300;
        if (aliens.size() == 0){
            score += 300;
        }
        inGame = false;
        shots.clear();
        aliens.clear();
        upgrades.clear();
        playerLives = 3;
        this.message = message;
    }

    /**
     * Behaviour of alien spaceship and their bombs.
     * Set moving alien.
     * Randomize bomb spawn.
     * If alien or bomb gets out of the board - remove them.
     * If bomb is not destroyed - move it down.
     * */
    public void alienBombShots(){
        Random random = new Random();
        Bomb bomb;
        Alien alien;
        Iterator<Alien> alienIterator = aliens.iterator();

        while (alienIterator.hasNext()) {
            alien = alienIterator.next();
            bomb = alien.getBomb();

            int value = random.nextInt(100 * aliens.size()) + 1;

            if (value > 0 && value <= 5 && alien.isVisible()){
                alien.getBomb().setDestroyed(false);
            }

            if (bomb.getY() > boardHeight){
                bomb.setDestroyed(true);
            }

            if (alien.getY() > boardHeight || alien.getX() < 0 - alien.width || alien.getX() > boardWidth){
                alien.setVisible(false);
                alien.setBombCoordinates();
                score -= 50 * level;
                if (aliens.size() >= 2) {
                    if (alien == aliens.get(0)) {
                        aliens.get(1).setMoving(true);
                    } else {
                        aliens.get(0).setMoving(true);
                    }
                    return;
                }
            }

            if (!bomb.isDestroyed()){
                bomb.moveDownwards();
            }

            if (alien.isMoving()) {
                if(alien.getStep() == 0){
                    calculateStep(alien);
                }
                alien.setX((float) (alien.getX() + alien.getSpeed() * alien.getStep()));
                alien.setY((float) (alien.getY() + alien.getSpeed()));
            }
        }
    }

    public void addShot(){
        shots.add(new Shot((int) player.getX(), (int) player.getY()));
    }

    public boolean endGameCheck(){
        if (aliens.isEmpty() && inGame) {
            if (level >= 5){
                gameOver("You won!");
                level = 1;
            }else{
                level++;
                gameOver(String.format("Level %d",level));
            }
            return true;
        }
        return false;
    }

    /**
     * Getters and setters.
     * */
    public long getScore() {
        return score;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }


    public Player getPlayer() {
        return player;
    }

    public ArrayList<Alien> getAliens() {
        return aliens;
    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public ArrayList<Upgrade> getUpgrades() {
        return upgrades;
    }

    public ImageIcon getHearth() {
        return hearth;
    }

    public int getPlayerLives() {
        return playerLives;
    }

    public String getMessage() {
        return message;
    }

    public int getLevel() {
        return level;
    }
}
