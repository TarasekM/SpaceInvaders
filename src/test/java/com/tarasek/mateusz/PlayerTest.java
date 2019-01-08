package com.tarasek.mateusz;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Robot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest{

    Robot robot;
    Player player;

    @BeforeEach
    void setUp(){
        try {
            robot = new Robot();
        }catch (java.awt.AWTException e){
            System.out.println(e);

        }
        player = new Player();
    }

    @Test
    void moveInsideBoard(){
        player.move(100,100);
        assertEquals(100 - player.getWidth()/2, player.getX());
        assertEquals(100 - player.getHeight()/2, player.getY());
    }

    @Test
    void moveOutsideBoard(){

        player.move(20,20);
        assertEquals(0, player.getX());
        assertEquals(0,player.getY());

        player.move(player.BOARD_WIDTH,player.BOARD_HEIGHT);
        assertEquals(player.BOARD_WIDTH - player.getWidth(), player.getX());
        assertEquals(player.BOARD_HEIGHT - player.getHeight(), player.getY());
    }
}