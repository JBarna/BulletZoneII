package com.scrumdogmillionaire.bulletzoneii;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;

/**
 * Main activity for our app
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    TextView textViewTankId;

    /**
     * onCreate handles things that should be done when the activity is created
     * @param savedInstanceState - Bundle object that hold the data of the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivity.PlaceholderFragment())
                    .commit();
        }
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
     * Starts the playgame activity when join game button is clicked
     * @param view - view that we a currently in
     */
    @Click(R.id.button_join_game)
    public void joinGame(View view)
    {
        //create intent to start play game activity
        Intent playGame = new Intent(this, PlayGame_.class);
        Log.v("MainActivity", "We are going to start PlayGame");
        Intent intent = PlayGame_.intent(this).get();
        startActivity(intent);

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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
