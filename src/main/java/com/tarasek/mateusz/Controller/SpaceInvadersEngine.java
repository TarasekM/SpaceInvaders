package com.tarasek.mateusz.Controller;

import com.tarasek.mateusz.Model.SpaceInvadersLogic;
import com.tarasek.mateusz.View.Board;

public class SpaceInvadersEngine implements Runnable{

    private final int TICKS_PER_SECOND = 50;
    private final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;

    private SpaceInvadersLogic model;
    private Board view;
    private boolean mouseClicked;

    public SpaceInvadersEngine(SpaceInvadersLogic model,Board view){
        this.model = model;
        this.view = view;
        this.view.setController(this);
    }

    @Override
    public void run() {
        long nextGameTick = System.currentTimeMillis();
        long nextShot = System.currentTimeMillis();
        long sleepTime, shootSleep = 1000;
        boolean isShooting = false;
        UpdateView();

        while (true) {

            if(mouseClicked && !model.isInGame()){
                model.startGame();
                view.hideCursor();
            }

            if (shootSleep - model.getLevel() * 50 <= System.currentTimeMillis() - nextShot) {
                isShooting = true;
                nextShot = System.currentTimeMillis();
            }

            UpdateView();
            if(model.isInGame()){
                UpdateLogic(isShooting);
            }else {
                view.showCursor();
            }

            nextGameTick += SKIP_TICKS;
            sleepTime = nextGameTick - System.currentTimeMillis();

            if (sleepTime >= 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
            }
            isShooting = false;

        }
    }

    private void UpdateView(){
        view.updateState(model.getPlayerLives(), model.getScore(), model.getHearth(), model.getPlayer(),
                model.getShots(), model.getAliens(),model.getMessage());
        view.repaint();
    }

    private void UpdateLogic(boolean isShooting) {
        model.endGameCheck();

        if (isShooting) {
            model.addShot();
        }
        model.shotCollisionDetection();
        model.playerAlienCollisionDetection();
        model.alienBombShots();

        fixedPlayerMove();

    }

    public void mouseClicked(boolean mouseClicked){
        this.mouseClicked = mouseClicked;
    }

    public boolean modelIsInGame() {
        return model.isInGame();
    }

    void fixedPlayerMove(){
        float playerSpeed = 8;
        float xPos = (float) model.getPlayer().getX();
        float yPos = (float) model.getPlayer().getY();
        float dx = (view.mousePos.x - 24 - xPos);
        float dy = (view.mousePos.y - 24 - yPos);
        float newPosX, newPosY, abs;

        try {
            abs = Math.abs(dx) + Math.abs(dy);
            if (abs == 0){
                throw new ArithmeticException("division by zero");
            }
            newPosX = dx / abs;
            newPosY = dy / abs;

            if (!(dx < playerSpeed && dx > -playerSpeed)){
                model.getPlayer().setX(xPos + newPosX * playerSpeed);
            }else {
                model.getPlayer().setX(xPos + dx/playerSpeed);
            }

            if (!(dy < playerSpeed && dy > -playerSpeed)){
                model.getPlayer().setY(yPos + newPosY * playerSpeed);
            }else {
                model.getPlayer().setY(yPos + dy/playerSpeed);

            }
        } catch (ArithmeticException e){
            System.out.println(e);
            return;
        }

    }

}
