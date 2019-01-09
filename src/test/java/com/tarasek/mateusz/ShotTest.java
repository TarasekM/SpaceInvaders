package com.tarasek.mateusz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShotTest implements SharedVariables{

    Shot shot;

    @BeforeEach
    void setShot(){
        shot = new Shot(10,20);
    }

    @Test
    void init(){
        assertNotNull(shot.getImage());
        assertEquals(10 + SHOT_WIDTH_SPACE, shot.getX());
        assertEquals(20 + SHOT_HEIGHT_SPACE, shot.getY());
    }

    @Test
    void moveUpwards(){
        shot.moveUpwards();
        assertEquals(20 - SHOT_SPEED + SHOT_HEIGHT_SPACE, shot.getY());
    }
}
