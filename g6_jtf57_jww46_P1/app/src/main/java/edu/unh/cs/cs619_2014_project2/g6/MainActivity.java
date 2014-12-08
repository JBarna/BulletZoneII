package edu.unh.cs.cs619_2014_project2.g6;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
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
import android.widget.Toast;


import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EFragment;

/**
 * Main activity for our app
 */

@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    TextView textViewTankId;
    MediaPlayer player;


    /**
     * onCreate handles things that should be done when the activity is created
     * @param savedInstanceState - Bundle object that hold the data of the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player = MediaPlayer.create(this, R.raw.pokemon_theme);
        player.setLooping(true); // Set looping
        player.setVolume(100,100);
        player.start();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivity.PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        player.stop();
    }

    @Override
    protected void onPause(){
        super.onPause();
        player.pause();
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
    public void joinGame(View view)
    {
        //create intent to start play game activity
        Intent chooseCharacter = new Intent(this, SelectCharacter_.class);
        Log.v("MainActivity", "We are going to start SelectCharacter");
        startActivity(chooseCharacter);
    }


    public void replayGame(View view)
    {
        //Intent playGame = new Intent(this, RePlayGame_.class);
        Intent intent = RePlayGame_.intent(this).get();
        Context context = getApplicationContext();
        CharSequence text = "Feature not implemented yet!";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
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
