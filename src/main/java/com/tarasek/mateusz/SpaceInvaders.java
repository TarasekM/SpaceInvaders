package com.tarasek.mateusz;

import javax.swing.*;
import java.awt.*;

public class SpaceInvaders extends JFrame implements SharedVariables{

    public SpaceInvaders(){
        init();
    }

    private void init(){
        setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_WIDTH, BOARD_HEIGTH);
        setLocationRelativeTo(null);
        setResizable(false);
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            SpaceInvaders spaceInvaders = new SpaceInvaders();
            spaceInvaders.setVisible(true);
        });
    }
}
