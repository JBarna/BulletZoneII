package com.scrumdogmillionaire.bulletzoneii;

import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.google.common.eventbus.Subscribe;
import com.scrumdogmillionaire.bulletzoneii.GuiItems.GuiItem;
import com.scrumdogmillionaire.bulletzoneii.GuiItems.GuiItemFactory;
import com.scrumdogmillionaire.bulletzoneii.GuiItems.TextAdapter;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.MapItem;
import com.scrumdogmillionaire.bulletzoneii.LogicItems.MapItemFactory;
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

    @Bean
    GameController gameController;

    @RestService
    BulletZoneRestClient restClient;//rest client variable

    @Bean
    Poller poller;

    TextView textViewTankId;//global variable for textview that displays tank id
    //TextView gridTextView;

    /**
     * onCreate handles things that should be done when the activity is created
     * @param savedInstanceState - Bundle object that hold the data of the previous state
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        }, 10);
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

    /**
     *Called when the activity is resumed
     */
    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(eventHandler);
        shakeManager.registerSensor();
    }

    /**
     * Called when the activity is paused
     */
    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(eventHandler);
        shakeManager.unRegisterSensor();
        super.onPause();
    }

/*********************************Updating the UI*****************************************/
    /**
     * Updates the tank Id text view
     * @param tankId number of the tank ID
     */
    @UiThread
    public void updateTankId(long tankId) {
        Log.v("PlayGame", "in update tank");
        textViewTankId = (TextView) findViewById(R.id.text_view_tank_id);
        textViewTankId.setText("Tank id = " + tankId);//set the tank id text view to display the
        //the tank id
    }

    /**
     * event handler for our event bus
     */
    private Object eventHandler = new Object() {
        @Subscribe
        public void onUpdateGrid(GridUpdateEvent event) {
            gameController.updateGameState(event);
            updateGrid(event);
        }
    };


    /**
     * Updates the textAdapter with the Grid Update Event
     * @param event Holds the grid
     */
    @UiThread
    void updateGrid(GridUpdateEvent event) {
        int[][] grid = event.getGrid();
        String[] vals = new String[256];
        int k=0;
        for (int i = 0; i < 16; i++) {

            for (int j = 0; j < 16; j++) {
                int val = grid[i][j];
                MapItem item = null;
                MapItemFactory itemFact = new MapItemFactory();
                item = itemFact.getItem(val);
                GuiItem gui;
                GuiItemFactory guiFact = new GuiItemFactory();
                gui = guiFact.makeGuiItem(item);
                vals[k] = gui.getDisplay();
                k++;
            }
        }
        /*for(int i=0; i<16; i++)
        {
            System.out.println("***************" + vals[i]);
        }*/
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
                //add table row to the tablelayout
                //gameTable.addView(tr);
            }

            return rootView;
        }
    }
}
