package com.ynz.event.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class EventHandlerTest {
    @Autowired
    private EventHandler eventHandler;

    @Test
    public void testEventHandlerIsInjected() {
        assertNotNull(eventHandler);
        assertNotNull(eventHandler.getEventDispatcher());
    }

}