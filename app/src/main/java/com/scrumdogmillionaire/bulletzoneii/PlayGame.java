package com.scrumdogmillionaire.bulletzoneii;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_play_game)
public class PlayGame extends ActionBarActivity {
    private ShakeManager shakeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        //create our shake manager
        shakeManager = new ShakeManager(this);
        shakeManager.addShakeListener(new ShakeManager.ShakeListener() {

            @Override
            public void deviceHasShook() {
                Toast.makeText(getApplicationContext(), "Device has shook 10", Toast.LENGTH_SHORT).show();
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


    /**
     * On resume
     */
    @Override
    protected void onResume(){
        super.onResume();
        //re-register the shake listener
        shakeManager.registerSensor();
    }

    /**
     * onpause
     */
    protected void onPause(){
        shakeManager.unRegisterSensor();
        super.onPause();
    }
    /**
     * buttomMovement will be called when left, right , up or down
     * has been pressed
     */
    @Click ({R.id.button_move_up, R.id.button_move_right, R.id.button_move_left, R.id.button_move_down})
    public void buttonMovement(View view){
        Button btn = (Button) view;

        Toast.makeText(getApplicationContext(), getResources().getResourceEntryName(btn.getId()), Toast.LENGTH_SHORT ).show();
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

            //get the table layout
            TableLayout gameTable = (TableLayout) rootView.findViewById(R.id.game_table);

            //add 16 rows to the gameTable
            for (int i = 0; i < 16; i++){
                TableRow tr = new TableRow(getActivity());
                tr.setId(i);
                //add 16 text cells to the gameTable
                for (int j = 0; j < 16; j++){
                    TextView cell = new TextView(getActivity());
                    cell.setText("x");
                    cell.setId(j);

                    //add cell to the table row
                    tr.addView(cell);
                }

                //add table row to the tablelayout
                gameTable.addView(tr);
            }

            return rootView;
        }
    }
}
