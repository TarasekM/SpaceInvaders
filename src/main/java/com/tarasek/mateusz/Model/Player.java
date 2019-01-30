package com.tarasek.mateusz.Model;

import com.tarasek.mateusz.SharedVariables;

import javax.swing.*;

public class Player extends Sprite implements SharedVariables {

    private final int START_X = 216;
    private final int START_Y = 544;
    private final int PLAYER_WIDTH = 48;
    private final int PLAYER_HEIGHT = 48;

    private float playerSpeed;
    private float shootingFrequency;
    private float playerDamage;
    private float shootSpeed;


    public Player(){
        initPLayer();
    }

    private void initPLayer(){
        try{
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/DODO_SpaceShip.png"));
            setImage(imageIcon.getImage());
        }catch (NullPointerException e){
            setImage(null);
        }
        resetPosition();
        width = PLAYER_WIDTH;
        height = PLAYER_HEIGHT;
        playerSpeed = 3f;
        shootingFrequency = 0;
        playerDamage = 1;
        shootSpeed = 2;
    }

    public void move(float x, float y){

        if (x >= BOARD_WIDTH - width/2){
            x = BOARD_WIDTH - width/2;
        }

        if (y >= BOARD_HEIGHT - height/2){
            y = BOARD_HEIGHT - height/2;
        }

        if (x <= width/2){
            x = width/2;
        }

        if(y <= height/2){
            y = height/2;
        }

        this.x = x - width/2;
        this.y = y - width/2;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    public float getShootingFrequency() {
        return shootingFrequency;
    }

    public void setShootingFrequency(float shootingFrequency) {
        this.shootingFrequency = shootingFrequency;
    }

    public float getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(float playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    public float getPlayerDamage() {
        return playerDamage;
    }

    public void setPlayerDamage(float playerDamage) {
        this.playerDamage = playerDamage;
    }

    public float getShootSpeed() {
        return shootSpeed;
    }

    public void setShootSpeed(float shootSpeed) {
        this.shootSpeed = shootSpeed;
    }

    public void resetPosition(){
        setX(START_X);
        setY(START_Y);
    }

    public void resetBonus(){
        this.playerSpeed = 3f;
        this.shootingFrequency = 0;
        this.playerDamage = 1;
        this.shootSpeed = 2;

    }
}
