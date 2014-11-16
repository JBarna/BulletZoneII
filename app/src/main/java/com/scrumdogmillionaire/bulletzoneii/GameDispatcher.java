package com.scrumdogmillionaire.bulletzoneii;

import android.content.Context;
import android.util.Log;

import com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient.BulletZoneRestClient;
import com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient.Poller;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.rest.RestService;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by JBarna on 11/14/2014.
 */

@EBean
public class GameDispatcher{

    //global variables
    long tankId = -1;

    PlayGame parent;

    @RestService
    BulletZoneRestClient restClient;

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
            tankId = restClient.join().getResult();//get the tank id from the server
            parent.updateTankId(tankId);//call to update the tank id
            poller.doPoll();//start the poller
            Log.v("GameDispatcher", "Tankid is " + tankId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Quit. Tell the server to leave
     */
    @Background
    public void quit(){

        if(restClient.leave(tankId).getResult().equalsIgnoreCase("accepted")) {
            parent.vibrate(100);
            parent.finish();
        }
    }

    /**
     * Tell the server to fire a bullet
     */
    @Background
    public void fire()
    {

        if (restClient.fire(tankId).getResult()) {
            parent.vibrate(100);

        }


    }

    /**
     * Tell the server to turn the tank
     */
    @Background
    public void turn(byte dir)
    {
        Log.v("GameD", "Tankid is " + tankId + " And dir is " + dir);
        if(restClient.turn(tankId, dir).getResult()) {
            parent.vibrate(50);
        }
    }

    /**
     * Tell the server to move the tank
     */
    @Background
    public void move(byte dir)
    {
        try {
            if (restClient.move(tankId, dir).getResult()) {
                parent.vibrate(50);
            }
        } catch (HttpClientErrorException err){
            //tank is gone
        }
    }
}
