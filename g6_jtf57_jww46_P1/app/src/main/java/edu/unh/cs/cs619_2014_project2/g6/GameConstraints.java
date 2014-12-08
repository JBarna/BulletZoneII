package edu.unh.cs.cs619_2014_project2.g6;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by JBarna on 12/5/2014.
 */
public class GameConstraints {

    //global variables-------------------------------------
    //constraint variables
    final static private long LOCK_DELAY = 500; //milliseconds to lock movement/fire
    public int numBullets = 0;
    private boolean canFire = true;
    private boolean canMove = true;
    private boolean tankAlive = true;

    //timer for the locks
    Timer timer = new Timer();

    /**
     * Reset the Game Constraint variables
     */
    public void resetConstraints(){
        numBullets = 0;
        tankAlive = false;
    }

    /**
     * Increase the number of bullets
     * on the map by 1
     */
    public void increaseBullet(){
        numBullets++;
    }

    /**
     * Set tank to be alive
     */
    public void setAlive(){
        tankAlive = true;
    }

    /**
     * Returns if the tank can move
     * @return can move
     */
    public boolean canMove(){
        return canMove;
    }

    /**
     * Returns if the tank can fire
     * @return can fire
     */
    public boolean canFire(){
        return ((canFire) && (numBullets < 2));
    }

    /**
     * return if the tank is alive
     * @return alive
     */
    public boolean getAlive(){
        return tankAlive;
    }

    /**
     * Register the tank as moved
     */
    public void move() {
        canMove = false;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canMove = true;
            }

        }, GameConstraints.LOCK_DELAY);
    }

    /**
     * Register the tank as firing
     */
    public void fire() {
        canFire = false;
        numBullets++;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canFire = true;
            }

        }, GameConstraints.LOCK_DELAY);
    }
}
