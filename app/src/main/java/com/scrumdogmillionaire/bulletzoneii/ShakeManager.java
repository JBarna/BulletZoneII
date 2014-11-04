package com.scrumdogmillionaire.bulletzoneii;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This will provide a simple facade to
 * use the sensorEventListner to
 * utilize the shake function of the device
 *
 * The shake manager will handle all the code to register the sensor Listener
 * and the inner interface will provide the callback functions
 */
public class ShakeManager implements SensorEventListener{

    //global variables
    private Activity parent;
    private Listeners listeners = new Listeners();
    //shake variables
    private SensorManager mSensorManager;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;

    public ShakeManager(Activity parent){

        //create the sensor manager and register the listener
        mSensorManager = (SensorManager) parent.getSystemService(Context.SENSOR_SERVICE);
        registerSensor();

        //set values to be default
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

    }

    public void onSensorChanged(SensorEvent se) {
        float x = se.values[0];
        float y = se.values[1];
        float z = se.values[2];

        mAccelLast = mAccelCurrent;
        mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));
        float delta = mAccelCurrent - mAccelLast;
        mAccel = mAccel * 0.9f + delta;

        listeners.notifyListeners();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }

    /*
    Add listener
     */
    public void addShakeListener(ShakeListener listener, int threshhold){
        listeners.add(listener, threshhold);
        Log.v("ShakeManager", "Shake Listener has been added");
    }

    /*
    remove listener
     */
    public void removeShakeListener(ShakeListener listener){
        listeners.remove(listener);
    }

    /**
     * unregister the sensorlistener (for on pause)
     */
    public void unRegisterSensor(){

        mSensorManager.unregisterListener(this);
    }

    public void registerSensor(){
        mSensorManager.registerListener(this,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    /*
    Inner class to hold values of desired Accel values matched
    with listeners
     */
    private class Listeners{
        //global variables
        ArrayList<Integer> accelValues = new ArrayList<Integer>();
        ArrayList<ShakeListener> shakeListeners = new ArrayList<ShakeListener>();

        public void add(ShakeListener listener, int threshhold){
            accelValues.add(threshhold);
            shakeListeners.add(listener);
        }

        public void remove(ShakeListener listener){
            int index = shakeListeners.indexOf(listener);
            //remove from both arrays
            shakeListeners.remove(index);
            accelValues.remove(index);
        }

        public void notifyListeners(){
            //i is our index

            for (int i = 0; i < accelValues.size(); i++) {
                if (mAccel > accelValues.get(i)) {
                    shakeListeners.get(i).deviceHasShook();
                }
            }
        }
    }

    /*
    public interface ShakeListener
    will include function to be called if the
    device has been shook over a specific value
     */
    public interface ShakeListener{
        public void deviceHasShook();
    }
}
