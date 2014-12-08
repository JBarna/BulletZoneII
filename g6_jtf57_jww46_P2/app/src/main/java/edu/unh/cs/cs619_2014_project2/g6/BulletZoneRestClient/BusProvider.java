package edu.unh.cs.cs619_2014_project2.g6.BulletZoneRestClient;

import com.google.common.eventbus.EventBus;

/**
 * Singleton class wrapper for a bus instance
 */
public class BusProvider {
    final static EventBus bus = new EventBus();

    /**
     * Used to return the bus variable
     * @return - return the bus variable
     */
    public static EventBus getInstance(){
        return bus;
    }

    /**
     * Base Constructor
     */
    private BusProvider(){
    }
}