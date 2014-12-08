package edu.unh.cs.cs619_2014_project2.g6;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.common.eventbus.Subscribe;
import edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient.GridUpdateEvent;
import edu.unh.cs.cs619_2014_project2.g6.GuiItems.GuiItem;
import edu.unh.cs.cs619_2014_project2.g6.GuiItems.GuiItemFactory;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.Bullet;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.MapItem;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.MapItemFactory;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.Tank;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Timer;
import java.util.TimerTask;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by JBarna on 11/15/2014.
 */
@EBean
public class GameController {

    //-------------Global Variables--------------------------
    PlayGame parent;
    MapItemFactory itemFact = new MapItemFactory();
    GameConstraints constraints = new GameConstraints();

    @Bean
    GameDispatcher gameDispatcher;

    //direction of our tank!
    private int curDir = 0;

    //id of our tank
    public long tankId = -1;

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

    public void updateGameState(MapItem mapItems[]){
        //parse the grid, track the game state
        //reset constraints
        constraints.resetConstraints();
        for (int i = 0; i < mapItems.length; i++) {

            MapItem item = mapItems[i];


            //check bullets
            if ((item instanceof Bullet) && item.getAttribute(MapItem.ID) == tankId) {
                constraints.increaseBullet();
            }

            //check if tank is alive
            else if ((item instanceof Tank) && item.getAttribute(MapItem.ID) == tankId){
                constraints.setAlive();
            }
        }

        //check to see if tank is alive, if not, exit the activity
        if (!constraints.getAlive()) {
            Toast.makeText(parent, "Your tank died!", Toast.LENGTH_SHORT).show();
            parent.finish();

        }
    }

    //------------- User Interaction Methods ----------------------------

    /**
     * Move
     */
    public void move(int direction) {
        if (constraints.canMove()) {
            if (curDir == direction || curDir == oppositeDir(direction)) {
                //we are going forwards or backwards
                gameDispatcher.move((byte) direction);
            } else {
                //we are going to turn left or right!
                gameDispatcher.turn((byte) direction);
                //update direction
                curDir = direction;
            }

            constraints.move();
        }
    }



    /**
     * Fire
     */
    public void fire() {
        if (constraints.canFire()) {
            gameDispatcher.fire();
            constraints.fire();
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
    }

    /**
     * Set the tank id
     * @param tankId
     */
    public void setTankId(long tankId){
        this.tankId = tankId;
    }
    //---------- Helper Functions ----------------------

    /**
     * Returns the opposite direction
     * @param dir direction tank is going
     * @return the opposite direction
     */
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
}
