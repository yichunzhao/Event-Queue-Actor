package com.ynz.event.dispatcher;

import com.ynz.event.entities.Event;
import com.ynz.event.listener.EventListener;

public interface EventDispatcher {
    void start(); //start dispatcher dispatching.
    void stop() throws InterruptedException; //stop dispatcher dispatching.

    boolean post(Event event); //post an event into the q
    void register(EventListener listener);

}
