package com.scrumdogmillionaire.bulletzoneii;

import android.content.Context;
import android.util.Log;

import com.google.common.eventbus.Subscribe;
import com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient.GridUpdateEvent;
import com.scrumdogmillionaire.bulletzoneii.GuiItems.GuiItem;
import com.scrumdogmillionaire.bulletzoneii.GuiItems.GuiItemFactory;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.Bullet;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.MapItem;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.MapItemFactory;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.Tank;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JBarna on 11/15/2014.
 */
@EBean
public class GameController {

    //-------------Global Variables--------------------------
    PlayGame parent;
    MapItemFactory itemFact = new MapItemFactory();
    Timer timer = new Timer();

    @Bean
    GameDispatcher gameDispatcher;

    //direction of our tank!
    private int curDir = 0;

    //id of our tank
    long tankId = -1;

    //Constraints
    private int numBullets = 0;
    private boolean fireLock = false;
    private boolean moveLock = false;
    private boolean tankAlive = true;
    final static private long LOCK_DELAY = 1000; //milliseconds to lock movement/fire


    //static variables to represent the directions!
    final static public int UP = 0;
    final static public int RIGHT = 2;
    final static public int DOWN = 4;
    final static public int LEFT = 6;

    /*
    Constructor
    Takes the Play Game activity as the parameter
     */
    public GameController(Context parent) {
        this.parent = (PlayGame) parent;
    }

    //------------- Server to Client Interaction-------------------------

    public void updateGameState(GridUpdateEvent event){
        //parse the grid, track the game state
        int[][] grid = event.getGrid();

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int val = grid[i][j];
                MapItem item = null;
                item = itemFact.getItem(val);

                //reset constraints
                resetConstraints();
                //check bullets
                if ((item instanceof Bullet) && item.getAttribute(MapItem.ID) == tankId) {
                    numBullets++;
                    continue;
                }

                //check if tank is alive
                if ((item instanceof Tank) && item.getAttribute(MapItem.ID) == tankId){
                    tankAlive = true;
                }
            }
        }

        //check to see if tank is alive, if not, exit the activity
        /*if (!tankAlive)
            parent.finish();*/
    }

    //------------- User Interaction Methods ----------------------------

    /**
     * Move
     */
    public void move(int direction) {
        if (!moveLock) {
            if (curDir == direction || curDir == oppositeDir(direction)) {
                //we are going forwards or backwards
                gameDispatcher.move((byte) direction);
            } else {
                //we are going to turn left or right!
                gameDispatcher.turn((byte) direction);
                //update direction
                curDir = direction;

            }

            moveLock = true;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    moveLock = false;

                }

            }, GameController.LOCK_DELAY);
        }
    }

    /**
     * Fire
     */
    public void fire() {
        if (numBullets < 2 && !fireLock) {
            gameDispatcher.fire();
            numBullets++;
            fireLock = true;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    fireLock = false;
                    Log.v("Schedule", "We are unlocking fireLock");
                }

        }, GameController.LOCK_DELAY);
        }
    }

    /**
     * Quit
     */
    public void quit() {
        gameDispatcher.quit();
    }

    /**
     * Join
     */
    public void join() {
        gameDispatcher.join();
        this.tankId = gameDispatcher.tankId;
    }

    //---------- Helper Functions ----------------------
    private int oppositeDir(int dir) {

        switch (dir) {

            case UP:
                return DOWN;

            case RIGHT:
                return LEFT;

            case DOWN:
                return UP;

            case LEFT:
                return RIGHT;

            default:
                return -1;
        }
    }

    private void resetConstraints(){
        numBullets = 0;
        tankAlive = false;
    }
}

