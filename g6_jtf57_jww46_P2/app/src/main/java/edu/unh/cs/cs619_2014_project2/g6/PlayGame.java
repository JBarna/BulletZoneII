package edu.unh.cs.cs619_2014_project2.g6;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.google.common.eventbus.Subscribe;
import edu.unh.cs.cs619_2014_project2.g6.DataBase.DBAdapter;
import edu.unh.cs.cs619_2014_project2.g6.GuiItems.GridAdapter;
import edu.unh.cs.cs619_2014_project2.g6.GuiItems.GuiItemFactory;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.MapItem;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.MapItemFactory;
import edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient.BusProvider;
import edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient.GridUpdateEvent;


import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

import java.util.Objects;

/**
 * Activity for game play
 */
@EActivity(R.layout.activity_play_game)
public class PlayGame extends ActionBarActivity{
    private ShakeManager shakeManager;
    int curDir = 0;
    long tankId = 0;
    DBAdapter myDb;
    int charType;
    @Bean
    GameController gameController;
    MediaPlayer player;

    //factory variables
    MapItemFactory itemFact = new MapItemFactory();

    TextView textViewTankId;//global variable for textview that displays tank id
    GridView gridView;

    GridAdapter gridAdapter = null;

    /**
     * onCreate handles things that should be done when the activity is created
     * @param savedInstanceState - Bundle object that hold the data of the previous state
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        charType = extras.getInt("charType");
        player = MediaPlayer.create(this, R.raw.pokemon_battle_music);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();
        openDB();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        gameController.join();

//create our shake manager
        shakeManager = new ShakeManager(this);
        shakeManager.addShakeListener(new ShakeManager.ShakeListener() {

            @Override
            public void deviceHasShook() {
                gameController.fire();
                vibrate(100);
            }
        }, 5);
    }

    /**
     * method for opening the databases, initializes the DBAdapter, opens the database, and clears
     * the database
     */
    public void openDB()
    {
        myDb = new DBAdapter(this);
        myDb.open();
        myDb.deleteTable();
        myDb.open();
    }

    /**
    public void closeDB()
    {
        myDb.close();
    }
    /**
     * Inflates the menu, adds items to the action bar if present
     * @param menu - Menu object for the menu
     * @return - return true to indicate success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.play_game, menu);
        return true;
    }

    /**
     * Handle action bar item clicks here. The action bar will Handle action bar item clicks here.
     * The action bar will ask you to specify a parent activity in AndroidManifest.xml
     * @param item - MenuItem object for select
     * @return - true to indicate success
     */
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

    public void setTankId(long l)
    {
        tankId = l;
        gameController.setTankId(l);
    }

    /**
     *Called when the activity is resumed
     */
    @Override
    protected void onResume() {
        super.onResume();
        player.start();
        BusProvider.getInstance().register(eventHandler);
        shakeManager.registerSensor();
    }

    /**
     * Called when the activity is paused
     */
    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
        BusProvider.getInstance().unregister(eventHandler);
        shakeManager.unRegisterSensor();
        super.onPause();
    }

    /**
     * called when the activity is destroyed
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myDb.close();
    }
/*********************************Updating the UI*****************************************/
    /**
     * Updates the tank Id text view
     * @param tankId number of the tank ID
     */
    @UiThread
    public void updateTankId(long tankId) {
        Log.v("PlayGame", "in update tank");
      //  textViewTankId = (TextView) findViewById(R.id.text_view_tank_id);
       // textViewTankId.setText("Tank id = " + tankId);//set the tank id text view to display the
        //the tank id
    }

    /**
     * event handler for our event bus
     */
    private Object eventHandler = new Object() {
        @Subscribe
        public void onUpdateGrid(GridUpdateEvent event) {
            //updateGrid(event);
        }
    };


    /**
     * Updates the textAdapter with the Grid Update Event
     * @param grid Holds the grid
     */
    @UiThread
    void updateGrid(int [][]grid) {
        MapItem[] mapItems = new MapItem[256];
        String s = "";
        int k=0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int val = grid[i][j];
                mapItems[k] = itemFact.getItem(val, tankId, charType);
                s += val + ":";
                k++;
            }
        }
        String tID = Objects.toString(tankId);
        myDb.insertRow(s,tID);
        Cursor curs = myDb.getAllRows();
        //create the adapter if it hasn't been created yet
        if (gridAdapter == null){
            //add the adapter to our
            gridView = (GridView) findViewById(R.id.gridview);

            //create the grid adapter
            gridAdapter = new GridAdapter(this, mapItems);
            try
            {
                gridView.setBackgroundColor(Color.WHITE);
                gridView.setAdapter(gridAdapter);
            }catch(Exception e){
                e.printStackTrace();
            }
        } else {
            gridAdapter.update(mapItems);
        }


        //pass our map items array to the game controller
        gameController.updateGameState(mapItems);
    }

    //----------------Helper methods -----------------------------------------
    /**
     * vibrate -- vibrate to give feedback
     * @param l How long to vibrate in milliseconds
     */
    public void vibrate(long l) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(l);
    }


    //-------------- Buttom methods-------------------------------------

    /**
     * When right button is clicked
     * @param view view of the button clicked.
     */
    @Click(R.id.button_move_right)
    public void rightButtonClick(View view)
    {
        gameController.move(GameController.RIGHT);
        System.out.println("We pressed right");
    }

    /**
     * When left button is clicked
     * @param view view of the button clicked.
     */
    @Click(R.id.button_move_left)
    public void leftButtonClick(View view)
    {
        gameController.move(GameController.LEFT);
    }

    /**
     * When up button is clicked
     * @param view view of the button clicked.
     */
    @Click(R.id.button_move_forward)
    public void forwardButtonClick(View view)
    {
        gameController.move(GameController.UP);
    }

    /**
     * When down button is clicked
     * @param view view of the button clicked.
     */
    @Click(R.id.button_move_backward)
    public void backwardButtonClick(View view)
    {
        gameController.move(GameController.DOWN);
    }

    /**
     * When fire button is clicked
     * @param view view of the button clicked.
     */
    @Click(R.id.button_fire)
    public void fireButtonClick(View view)
    {
        gameController.fire();
    }

    /**
     * When quit button is clicked
     * @param view view of the button clicked.
     */
    @Click(R.id.button_quit)
    public void quitButtonClick(View view)
    {
        gameController.quit();
    }


    //------------------ Fragment -------------------------------------
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        /**
         * Base Constructor
         */
        public PlaceholderFragment() {
        }

        /**
         * Handles what to do when the fragment is created
         * @param inflater -LayoutInflater to use
         * @param container - ViewGroup to use
         * @param savedInstanceState - - Bundle object that hold the data of the previous state
         * @return - return the rootview
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_play_game, container, false);
            {

            }

            return rootView;
        }
    }
}