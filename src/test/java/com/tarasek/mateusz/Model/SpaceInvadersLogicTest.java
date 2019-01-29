package com.tarasek.mateusz.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;


import static org.junit.jupiter.api.Assertions.*;

class SpaceInvadersLogicTest {

    SpaceInvadersLogic sil;

    @BeforeEach
    void setUp(){
        sil = new SpaceInvadersLogic(480,640);
    }

    @Test
    void startGame() {
    }

    @Test
    void shotCollisionDetection() {
    }

    @Test
    void playerAlienCollisionDetection() {
    }

    @Test
    void gameOver() {
    }

    @Test
    void alienBombShots() {
    }

    @Test
    void addShot() {
        sil.addShot();
        assertEquals(1,sil.getShots().size());
    }

    @Test
    void endGameCheck() {
        sil.setInGame(true);
        assertTrue(sil.endGameCheck());
        sil.startGame();
        assertFalse(sil.endGameCheck());
    }

    @Test
    void getScore() {
        sil.startGame();
        assertEquals(0, sil.getScore());
    }

    @Test
    void isInGame() {
        sil.startGame();
        assertTrue(sil.isInGame());
    }

    @Test
    void setInGame() {
        sil.setInGame(true);
        assertTrue(sil.isInGame());
    }

    @Test
    void getPlayer() {
        assertNotNull(sil.getPlayer());
    }

    @Test
    void getAliens() {
        assertEquals(0, sil.getAliens().size());
        sil.startGame();
        assertEquals(40, sil.getAliens().size());

    }

    @Test
    void getShots() {
        assertEquals(0, sil.getShots().size());
        sil.addShot();
        assertEquals(1, sil.getShots().size());
    }

    @Test
    void getHearth() {
        ImageIcon hearth = sil.getHearth();
    }

    @Test
    void getPlayerLives() {
        assertEquals(3, sil.getPlayerLives());
    }

    @Test
    void getMessage() {
        String testString = "Test message";
        sil.gameOver(testString);
        assertTrue(testString.equals(sil.getMessage()));
    }

    @Test
    void getLevel() {
        assertEquals(1, sil.getLevel());
    }
}