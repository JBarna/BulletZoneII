package edu.unh.cs.cs619_2014_project2.g6;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.SystemClock;
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
import android.widget.Toast;


import edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient.GridUpdateEvent;
import edu.unh.cs.cs619_2014_project2.g6.DataBase.DBAdapter;
import edu.unh.cs.cs619_2014_project2.g6.GuiItems.GridAdapter;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.MapItem;
import edu.unh.cs.cs619_2014_project2.g6.LogicItems.MapItemFactory;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Main activity for our app
 */
@EActivity(R.layout.activity_replay_game)
public class RePlayGame extends ActionBarActivity {
    DBAdapter myDb;
    GridAdapter gridAdapter = null;
    MapItemFactory itemFact = new MapItemFactory();
    GridView gridView;
    Cursor curs;
    long tankId = 0;
    int charType = 0;
    int delay = 0;
    boolean playing = false;
    Timer time = new Timer();

    /**
     * onCreate handles things that should be done when the activity is created
     *
     * @param savedInstanceState - Bundle object that hold the data of the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_replay_game);
        openDB();
        curs = myDb.getAllRows();
        tankId = Long.valueOf(curs.getString(DBAdapter.COL_TANKID));
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new RePlayGame.PlaceholderFragment())
                    .commit();
        }
        loadFirst();

    }

    /**
     * handles updating the grid with the delay slected by the user, on the background thread
     * so that it can use the systemclock
     */
    @Background(id = "REPLAY_BROSLICE")
    public void replay() {
        while (curs.moveToNext()) {
            String s = curs.getString(DBAdapter.COL_DATA);
            int [] [] grid = makeGrid(s);
            updateGrid(grid);
            SystemClock.sleep(delay);
        }
    }

    /**
     * loads the first grid representation of the the game
     */
    public void loadFirst()
    {
        curs.moveToFirst();
        String s = curs.getString(DBAdapter.COL_DATA);
        int [] [] grid = makeGrid(s);
        updateGrid(grid);

    }

    /**
     * button for setting the replay speed to 50%
     * @param view - view for button
     */
    @Click(R.id.button_half)
    public void pointFiveButtonClick(View view)
    {
        start(200);
    }

    /**
     * button for setting the replay speed to %100
     * @param view - view for button
     */
    @Click(R.id.button_one)
    public void oneButtonClick(View view){start(100);}

    /**
     * button for setting the replay speed to %200
     * @param view - view for the button
     */
    @Click(R.id.button_two)
    public void twoButtonClick(View view){start(50);}

    /**
     * button for setting the replay speed to %400
     * @param view - view for the speed
     */
    @Click(R.id.button_four)
    public void fourButtonClick(View view){start(25);}

    /**
     * method for resetting speed or starting replay
     * @param i - integer to the delay variable to
     */
    public void start(int i)
    {
        if(playing==true)
        {
            delay = i;
        }
        else if(playing==false)
        {
            delay = i;
            replay();
            playing=true;
        }
    }

    /**
     * method for updating the grid representation of the game
     * @param - 2d array of inte for representing the game
     */
    @UiThread
    void updateGrid(int [] [] g) {
        int[][] grid = g;
        MapItem[] mapItems = new MapItem[256];
        String s = "";
        int k=0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int val = grid[i][j];
                mapItems[k] = itemFact.getItem(val, tankId, charType);
                k++;
            }
        }

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
    }

    /**
     * method used to initialize DBAdapter and open the database
     */
    private void openDB()
    {
        myDb = new DBAdapter(this);
        myDb.open();
    }

    /**
     * method to close the databse
     */
    public void closeDB()
    {
        myDb.close();
    }

    /**
     * method to take the string represntation of the game data and split it into a 2d array of
     * integers
     * @param s - string representation of game data
     * @return - return a 2d integer array of game data
     */
    public int [][] makeGrid(String s){
        int [][] test = new int[16][16];
        String[] datas = s.split(":");
        int index=0;
        for(int i=0; i<16; i++)
        {
            for(int j=0; j<16; j++)
            {
                test[i][j]= Integer.parseInt(datas[index]);
                index++;
            }
        }
        return test;
    }

    /**
     * what to do when the activity is stopped
     */
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    /**
     * what to do when the activity is paused
     */
    @Override
    protected void onPause(){
        super.onPause();
    }

    /**
     * Inflates the menu, adds items to the action bar if present
     * @param menu - Menu object for the menu
     * @return - return true to indicate success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * A place holder fragment holding a simple view
     *
     */
    @EFragment
    public static class PlaceholderFragment extends Fragment {

        /**
         *Base Constructor
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
            View rootView = inflater.inflate(R.layout.fragment_replay_game, container, false);
            return rootView;
        }
    }
}
