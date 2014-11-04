package com.scrumdogmillionaire.bulletzoneii;

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


public class PlayGame extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
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
     * buttomMovement will be called when left, right , up or down
     * has been pressed
     */
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
