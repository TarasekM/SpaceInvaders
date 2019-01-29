package com.tarasek.mateusz.Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpriteTest {
    private Sprite sprite;

    @BeforeEach
    void setUp(){
        sprite = new Sprite();
    }

    @Test
    void explode() {
        sprite.explode();
        assertFalse(sprite.isVisible());
    }

    @Test
    void isVisible() {
        assertTrue(sprite.isVisible());
    }

    @Test
    void setVisible() {
        sprite.setVisible(false);
        assertFalse(sprite.isVisible());
    }

    @Test
    void setImage() {
    }

    @Test
    void setX() {
        sprite.setX(10);
        assertEquals(10, sprite.getX());
    }

    @Test
    void setY() {
        sprite.setY(10);
        assertEquals(10, sprite.getY());
    }

    @Test
    void setExplode() {
        sprite.setExplode(false);
        assertFalse(sprite.isExploding());
    }

    @Test
    void getX() {
        assertEquals(0, sprite.getX());
    }

    @Test
    void getY() {
        assertEquals(0, sprite.getY());
    }

    @Test
    void isExploding() {
        assertFalse(sprite.isExploding());
    }
}