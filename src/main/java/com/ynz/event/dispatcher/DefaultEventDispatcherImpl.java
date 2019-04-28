package com.ynz.event.dispatcher;

import com.ynz.event.entities.Event;
import com.ynz.event.listener.EventListener;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Data
@Component("dispatcher")
@Scope("singleton")
public class DefaultEventDispatcherImpl implements EventDispatcher {
    protected BlockingDeque<Event> eventBlockingDeque;
    protected Set<EventListener> listeners;
    protected Thread worker;

    protected volatile boolean exit;

    public DefaultEventDispatcherImpl() {
        this.eventBlockingDeque = new LinkedBlockingDeque<>(256);
        this.listeners = new HashSet<>();
    }

    @PostConstruct
    public void init(){
        this.start();
    }

    @Override
    public void start() {
        exit = false;

        worker = new Thread(() -> {
            while (!exit) {
                dispatch();
            }
        }
        );

        worker.start();
    }

    @Override
    public void stop() {
        this.exit = true;
    }

    @Override
    public boolean post(Event event) {
        return this.eventBlockingDeque.offer(event);
    }

    private void dispatch() {
        Optional.ofNullable(this.eventBlockingDeque.poll())
                .ifPresent(event -> listeners
                        .forEach(listener -> listener.doSomething(event)));
    }

    @Override
    public void register(EventListener listener) {
        this.listeners.add(listener);
    }
}
