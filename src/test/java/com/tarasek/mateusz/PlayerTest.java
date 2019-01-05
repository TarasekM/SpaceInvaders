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
        robot.mouseMove(100,100);
        player.move();
        assertEquals(100, player.getX());
        assertEquals(100, player.getY());
    }

    @Test
    void moveOutsideBoard(){

        robot.mouseMove(0,0);
        player.move();
        assertEquals(player.getWidth(), player.getX());
        assertEquals(player.getHeigth(),player.getY());

        robot.mouseMove(player.BOARD_WIDTH,player.BOARD_HEIGTH);
        player.move();
        assertEquals(player.BOARD_WIDTH - player.getWidth(), player.getX());
        assertEquals(player.BOARD_HEIGTH - player.getHeigth(), player.getY());
    }


}