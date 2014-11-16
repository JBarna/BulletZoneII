package com.scrumdogmillionaire.bulletzoneii.BulletZoneRestClient;

import android.os.SystemClock;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

/**
 * Class used to poll the server
 */
@EBean
public class Poller {
    private static final String TAG = "PollServer";

    @RestService
    BulletZoneRestClient restClient;

    /**
     * Used to stare the poller
     */
    @Background(id = "grid_poller_task")
    public void doPoll() {
        while (true) {
            onGridUpdate(restClient.grid());

            // poll server every 100ms
            SystemClock.sleep(100);
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