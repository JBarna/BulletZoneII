package edu.unh.cs.cs619_2014_project2.g6;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.ImageButton;
import android.widget.Toast;

import org.androidannotations.annotations.EActivity;

/**
 * activity for selecting which character to play the game with
 */
@EActivity
public class SelectCharacter extends ActionBarActivity {
    MediaPlayer player;

    /**
     * method to join the game using the charmander character, is called when the charmander button
     * is pressed.
     * @param btn - button that calls the function
     */
    public void joinGameC(View btn)
    {
        ImageButton imgBtn = (ImageButton) btn;
        Intent playGame = new Intent(this, PlayGame_.class);
        playGame.putExtra("charType", 0);
        playCharacterSound(0);
        startActivity(playGame);
    }

    /**
     * method to join the game using the pikachu character, is called when the pikachu button is
     * pressed
     * @param btn - button view for the button that calls the function
     */
    public void joinGameP(View btn){
        ImageButton imgBtn = (ImageButton) btn;
        Intent playGame = new Intent(this, PlayGame_.class);
        playGame.putExtra("charType", 1);
        playCharacterSound(1);
        startActivity(playGame);
    }

    /**
     * method to join the game using the bulbasaur character, is called when the bulbasaur button
     * is pressed
     * @param btn - button view for the button that calls the function
     */
    public void joinGameB(View btn)
    {
        ImageButton imgBtn = (ImageButton) btn;
        Intent playGame = new Intent(this, PlayGame_.class);
        playGame.putExtra("charType", 2);
        playCharacterSound(2);
        startActivity(playGame);
    }

    /**
     * method to join the game using the squirtle character, is called when the bulbasaur button
     * is pressed
     * @param btn - button view for the button that calls the function
     */
    public void joinGameS(View btn)
    {
        ImageButton imgBtn = (ImageButton) btn;
        Intent playGame = new Intent(this, PlayGame_.class);
        playGame.putExtra("charType", 3);
        playCharacterSound(3);
        startActivity(playGame);
    }

    /**
     * method to play soundeffect for selected character
     * @param i - integer that tells what character sound to play(0 - charmander, 1 - pikachu,
     *          2 - bulbasaur, 3 - squirtle)
     */
    public void playCharacterSound(int i)
    {
       if(i == 0)
       {
          player = MediaPlayer.create(this, R.raw.charmander);
       }
       else if(i == 1)
       {
          player = MediaPlayer.create(this, R.raw.pikachu);
       }
        else if(i == 2)
       {
          player = MediaPlayer.create(this, R.raw.bulbasaur);
       }
        else if(i ==3)
       {
           player = MediaPlayer.create(this, R.raw.squirtle);
       }

       player.setLooping(false); // Set looping
       player.setVolume(100,100);
       player.start();
//       while(player.isPlaying())
//       {
//
//       }
    }


    /**
     * me
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     *Called when the activity is resumed
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Called when the activity is paused
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(player != null) {
            player.pause();
        }
    }


        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.select_character, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_select_character, container, false);
            return rootView;
        }
    }
}
