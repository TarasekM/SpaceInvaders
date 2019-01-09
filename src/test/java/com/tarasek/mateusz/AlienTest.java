package com.tarasek.mateusz;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlienTest {

    Alien alien;

    @BeforeEach
    void setUp(){
        alien = new Alien(50,60);
    }


    @Test
    void init(){
        assertTrue(alien.getImage() != null);
        assertEquals(50, alien.getX());
        assertEquals(60, alien.getY());
    }

    @Test
    void getBomb(){
        assertTrue(alien.getBomb() != null);
    }

    @Test
    void move(){
        alien.move(20,30);
        assertEquals(20, alien.getX());
        assertEquals(30, alien.getY());
    }
}
