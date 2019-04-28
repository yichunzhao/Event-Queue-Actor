package com.ynz.event.listener;

import com.ynz.event.dispatcher.EventDispatcher;
import com.ynz.event.entities.Event;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("Listener")
@DependsOn("dispatcher")
public class EventHandler implements EventListener {

    @Autowired
    @Getter
    private EventDispatcher eventDispatcher;

    @PostConstruct
    public void init() {
        eventDispatcher.register(this);
    }

    @Override
    public void doSomething(Event event) {
        System.out.println("we do something here for this event" + event.toString());
    }
}
