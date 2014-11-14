package com.scrumdogmillionaire.bulletzoneii;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.eventbus.Subscribe;
import com.scrumdogmillionaire.bulletzoneii.items.MapItem;
import com.scrumdogmillionaire.bulletzoneii.items.MapItemFactory;
import com.scrumdogmillionaire.bulletzoneii.items.TextFactory;
import com.scrumdogmillionaire.bulletzoneii.items.TextGUI;
import com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient.BulletZoneRestClient;
import com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient.BusProvider;
import com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient.GridUpdateEvent;
import com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient.Poller;


import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.rest.RestService;
import org.androidannotations.annotations.UiThread;

@EActivity(R.layout.activity_play_game)
public class PlayGame extends ActionBarActivity{
    private ShakeManager shakeManager;
    int curDir = 0;

    TextView textViewTankId;//global variable for textview that displays tank id
    //TextView gridTextView;

    long tankId = -1;//global variable to hold tank id

    @RestService
    BulletZoneRestClient restClient;//rest client variable

    @Bean
    Poller poller;//poller variable


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        join();

        //create our shake manager
        shakeManager = new ShakeManager(this);
        shakeManager.addShakeListener(new ShakeManager.ShakeListener() {

            @Override
            public void deviceHasShook() {
                fire();
                vibrate(100);
            }
        }, 10);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.play_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


/*********************************Methods for server stuff*****************************************/
    /**
     * Update Tank Id
     */
    @UiThread
    public void updateTankId() {
        textViewTankId = (TextView) findViewById(R.id.text_view_tank_id);
        textViewTankId.setText("Tank id = " + tankId);//set the tank id text view to display the
        //the tank id
    }

    /**
     * Send the server the join command
     */
    @Background
    public void join() {
        try {
            tankId = restClient.join().getResult();//get the tank id from the server
            updateTankId();//call to update the tank id
            poller.doPoll();//start the poller
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object eventHandler = new Object() {
        @Subscribe
        public void onUpdateGrid(GridUpdateEvent event) {
            updateGrid(event);
        }
    };


    @UiThread
    void updateGrid(GridUpdateEvent event) {
        int[][] grid = event.getGrid();
        String[] vals = new String[256];
        String tmp="";
        int k=0;
        for (int i = 0; i < 16; i++) {

            for (int j = 0; j < 16; j++) {
                int val = grid[i][j];
                MapItem item = null;
                MapItemFactory itemFact = new MapItemFactory();
                item = itemFact.makeItem(val);
                TextGUI textGu;
                TextFactory textFact = new TextFactory();
                textGu = textFact.makeTextGUI(item);
                vals[k] = textGu.getDisplay();
                k++;
                //System.out.println("VALS i = " + vals[i]);
            }
        }
        for(int i=0; i<16; i++)
        {
            System.out.println("***************" + vals[i]);
        }
        GridView gridView = (GridView) findViewById(R.id.gridview);
        TextAdapter tA = new TextAdapter(this, vals);
        try
        {
          gridView.setBackgroundColor(Color.WHITE);
          gridView.setAdapter(tA);
        }catch(Exception e){
            e.printStackTrace();
        }


    }
/**************************************************************************************************/
    /**
     * vibrate -- vibrate to give feedback
     */
    public void vibrate(long l) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(l);
    }

//    /**
//     * buttomMovement will be called when left, right , up or down
//     * has been pressed
//     */
//    @Click({R.id.button_move_up, R.id.button_move_right, R.id.button_move_left, R.id.button_move_down})
//    public void buttonMovement(View view) {
//        Button btn = (Button) view;
//
//        Toast.makeText(getApplicationContext(), getResources().getResourceEntryName(btn.getId()), Toast.LENGTH_SHORT).show();
//        vibrate(50);
//    }


    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(eventHandler);
        shakeManager.registerSensor();
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(eventHandler);
        super.onPause();
    }

    @Click(R.id.button_move_right)
    public void rightButtonClick(View view)
    {
        int dirToMove=curDir;
        System.out.println("This is dir on right Button click: " + dirToMove);
        if(dirToMove>=0 && dirToMove < 6) {
            dirToMove+=2;
        }
        else if(dirToMove==6){
            dirToMove=0;
        }
        turn((byte)dirToMove);
    }

    @Click(R.id.button_move_left)
    public void leftButtonClick(View view)
    {
        int dirToMove=curDir;
        if(dirToMove>0) {
            dirToMove-=2;
        }
        else if(dirToMove == 0) {
            dirToMove=6;
        }
        turn((byte)dirToMove);
    }

    @Click(R.id.button_move_forward)
    public void forwardButtonClick(View view)
    {
        int dirToMove=curDir;
        move((byte) dirToMove);
    }

    @Click(R.id.button_move_backward)
    public void backwardButtonClick(View view)
    {
        int dirToMove=curDir;
        if(dirToMove<=2) {
            dirToMove+=4;
        }
        else if(dirToMove>2) {
            dirToMove-=4;
        }
        move((byte) dirToMove);
    }

    @Click(R.id.button_fire)
    public void fireButtonClick(View view)
    {
        fire();

    }

    @Background
    public void turn(byte dir)
    {
        if(restClient.turn(tankId, dir).getResult()) {
            curDir=dir;
            vibrate(50);
        }
        else
        {

        }
    }

    @Background
    public void move(byte dir)
    {
        if(restClient.move(tankId, dir).getResult()) {
            vibrate(50);
            //curDir=dir;
        }else
        {

        }
    }

    @Background
    public void fire()
    {
        if(restClient.fire(tankId).getResult()) {
            vibrate(100);
            //curDir=dir;
        }else
        {

        }
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_play_game, container, false);
            {
                //add table row to the tablelayout
                //gameTable.addView(tr);
            }

            return rootView;
        }
    }
}
