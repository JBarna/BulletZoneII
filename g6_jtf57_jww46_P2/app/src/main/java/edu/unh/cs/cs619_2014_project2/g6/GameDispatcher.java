package edu.unh.cs.cs619_2014_project2.g6;

import android.content.Context;
import android.util.Log;

import edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient.BulletZoneRestClient;
import edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient.Poller;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.HttpClientErrorException;

import net.cserna.bence.bulletzone.core.BulletZoneLocalProxy;
import net.cserna.bence.bulletzone.core.BulletZoneServer;
import net.cserna.bence.bulletzone.core.BulletZoneServerListener;


/**
 * Created by JBarna on 11/14/2014.
 */

@EBean
public class GameDispatcher implements BulletZoneServerListener{

    //global variables
    long tankId = -1;

    PlayGame parent;

    @RestService
    BulletZoneRestClient restClient;

    BulletZoneServer bzs = BulletZoneLocalProxy.createBulletZoneServer();

    @Bean
    Poller poller;

    public GameDispatcher(Context parent){
        this.parent = (PlayGame) parent;
    }



    /**
     * Send the server the join command
     **/
    @Background
    public void join() {
        try {

            tankId = bzs.joinServer(null, "Scrumdog", this);
            System.out.println("Our tank id is" + tankId);
            //tankId = restClient.join().getResult();//get the tank id from the server
            parent.setTankId(tankId);
            parent.updateTankId(tankId);//call to update the tank id
            //poller.doPoll();//start the poller
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onFieldUpdate(int [][] grid, long timestamp){
        parent.updateGrid(grid);
    }

    public void onEvent(int code, Object object){
        System.out.println("Code is:" + code);
    }

    /**
     * Quit. Tell the server to leave
     */
    @Background
    public void quit(){

        try {
            if(restClient.leave(tankId).getResult().equalsIgnoreCase("accepted")) {
                parent.vibrate(100);
                parent.finish();
            }
        } catch (HttpClientErrorException err){
            parent.finish();
        }
    }

    /**
     * Tell the server to fire a bullet
     */
    @Background
    public void fire()
    {
        try {
            if (bzs.fireBullet()) {
                parent.vibrate(100);
            }
        } catch (HttpClientErrorException err){
            parent.finish();
        }
    }

    /**
     * Tell the server to turn the tank
     */
    @Background
    public void turn(byte dir)
    {

        try {
            if(bzs.turn(dir)) {
                parent.vibrate(50);
            }
        } catch (HttpClientErrorException err){
            parent.finish();
        }
    }

    /**
     * Tell the server to move the tank
     */
    @Background
    public void move(byte dir)
    {
        try {
            if (bzs.move(dir)) {
                parent.vibrate(50);
            }
        } catch (HttpClientErrorException err){
            parent.finish();
        }
    }

}