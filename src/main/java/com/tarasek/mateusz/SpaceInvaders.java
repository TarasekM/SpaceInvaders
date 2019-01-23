package com.tarasek.mateusz;

import com.tarasek.mateusz.Controller.SpaceInvadersEngine;
import com.tarasek.mateusz.Model.SpaceInvadersLogic;
import com.tarasek.mateusz.View.Board;

import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SpaceInvaders implements Runnable{

    JFrame jf;
    SpaceInvadersEngine controller;
    Board view;
    SpaceInvadersLogic model;


    private SpaceInvaders(int width, int height){
        initModel(width,height);
        initView(width,height);
        initController();
        initJFrame(width,height);
    }

    private void initJFrame(int width, int height){
        width += 16;
        height += 39;

        jf = new JFrame();
        jf.setTitle("Space Invaders");
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setSize(width, height);
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.add(view);
    }

    private void initModel(int width, int height){
        this.model = new SpaceInvadersLogic(width, height);
    }

    private void initView(int width, int height){
        view = new Board(width, height);
    }

    private void initController(){
        controller = new SpaceInvadersEngine(model, view);
    }


    public void run() {
        jf.setVisible(true);
        new Thread(controller).start();
    }

    public static void main(String args[]){
        SwingUtilities.invokeLater(new SpaceInvaders(480,640));
    }
}
