package edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.widget.Toast;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import edu.unh.cs.cs619_2014_project2.g6.PlayGame;

/**
 * Class used to poll the server
 */
@EBean
public class Poller {
    private static final String TAG = "PollServer";

    PlayGame parent;

    @RestService
    BulletZoneRestClient restClient;


    /**
     * Contsructor for parent Poller class
     * @param parent - take in a parent context
     */
    public Poller(Context parent){
        this.parent = (PlayGame) parent;
        //check internet connectivity
        boolean isOnline = false;

        /*ConnectivityManager cm = (ConnectivityManager) parent.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = PlayGame parent; cm.getActiveNetworkInfo();
        isOnline = netInfo != null && netInfo.isConnectedOrConnecting();

        if (!isOnline) {
            Toast.makeText(parent, "Not connected to the internt!", Toast.LENGTH_SHORT);
            this.parent.finish();
        }
        */
    }
    /**
     * Used to stare the poller
     */
    @Background(id = "grid_poller_task")
    public void doPoll() {
        while (true) {
            try {
                onGridUpdate(restClient.grid());
                // poll server every 100ms
                SystemClock.sleep(100);
            } catch (org.springframework.web.client.HttpClientErrorException err) {
                parent.finish();
            }
        }
    }

    /**
     *used to handle the updating of the grid
     * @param gw - our GridWrapper object
     */
    @UiThread
    public void onGridUpdate(GridWrapper gw) {
        BusProvider.getInstance().post(new GridUpdateEvent(gw.getGrid(), gw.getTimeStamp()));
    }
}