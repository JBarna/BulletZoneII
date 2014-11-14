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


@EActivity(R.layout.activity_main)
public class MainActivity extends ActionBarActivity {

    //global variables
    TextView textViewTankId;

//    TextView gridTextView;
//
//    long tankId = -1;
//
//    @RestService
//    BulletZoneRestClient restClient;

//    @Bean
//    Poller poller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainActivity.PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

//    /**
//
//     * Update Tank Id
//     */
//    @UiThread
//    public void updateTankId(){
//        textViewTankId = (TextView) findViewById(R.id.text_view_tank_id);
//        textViewTankId.setText("Tankid " +  tankId);
//    }

    /**
     * Send the server the join command
     */
//    @Background
//    public void join(){
//        try {
//            tankId = restClient.join().getResult();
//            updateTankId();
//            poller.doPoll();
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//    }
    /**
     * Join Game-- method that gets called
     */
    @Click(R.id.button_join_game)
    public void joinGame(View view)
    {
        //create intent to start play game activity
        Intent playGame = new Intent(this, PlayGame_.class);
        Log.v("MainActivity", "We are going to start PlayGame");
        Intent intent = PlayGame_.intent(this).get();
        startActivity(intent);
       // intent.putExtra("tank_id", tankId);

       // join();

    }

//    private Object eventHandler = new Object()
//    {
//        @Subscribe
//        public void onUpdateGrid(GridUpdateEvent event) {
//            updateGrid(event);
//        }
//    };

//
//    @UiThread
//    void updateGrid(GridUpdateEvent event){
//        int [][] grid = event.getGrid();
//
//        StringBuilder builder = new StringBuilder();
//
//        builder.append("TimeStamp=").append(event.getTimestamp()).append("\n");
//        for (int i = 0; i < 16; i++){
//            for(int j = 0; j < 16; j++){
//                builder.append(grid[i][j]).append(":");
//            }
//            builder.append("\n");
//        }
//        gridTextView = (TextView) findViewById(R.id.text_view_grid);
//        gridTextView.setText(builder.toString());
//    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        BusProvider.getInstance().register(eventHandler);
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        BusProvider.getInstance().unregister(eventHandler);
//    }


    /**
     * A placeholder fragment cointentntaining a simple view.
     */
    @EFragment
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
