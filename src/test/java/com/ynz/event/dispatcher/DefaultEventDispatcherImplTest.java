package com.ynz.event.dispatcher;

import com.ynz.event.domainvalue.Action;
import com.ynz.event.domainvalue.Source;
import com.ynz.event.entities.Event;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DefaultEventDispatcherImplTest {


    protected DefaultEventDispatcherImpl eventDispatcher = new DefaultEventDispatcherImpl();

    private Event event;

    @Before
    public void setup() {
        event = Event.builder().source(Source.B.toString())
                .action(Action.ADD.toString())
                .createdAt(LocalDateTime.now()).build();

        eventDispatcher = new DefaultEventDispatcherImpl();
    }

    @Test
    public void TestPostEventToDispatcher() {
        assertTrue(eventDispatcher.post(event));
        assertTrue(eventDispatcher.getEventBlockingDeque().contains(event));
        assertTrue(eventDispatcher.getEventBlockingDeque().poll().equals(event));
    }

    @Test
    public void TestStartWorkerToWork() throws InterruptedException {
        eventDispatcher.start();
        assertNotNull(eventDispatcher.worker);
        Thread.sleep(20);
        assertThat(eventDispatcher.worker.isAlive(), is(true));
    }

    @Test
    public void TestStopWorkerToWork() throws InterruptedException {
        eventDispatcher.start();
        Thread.sleep(20);
        assertNotNull(eventDispatcher.worker);
        assertTrue(eventDispatcher.worker.isAlive());

        eventDispatcher.stop();
        Thread.sleep(20);
        assertThat(eventDispatcher.worker.isAlive(), is(false));
    }


}