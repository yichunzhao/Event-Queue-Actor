package com.ynz.event.service;

import com.ynz.event.domainvalue.Source;
import com.ynz.event.entities.Event;
import com.ynz.event.exceptions.EntityNotFoundException;
import com.ynz.event.repository.EventRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DefaultEventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    private DefaultEventServiceImpl eventService;

    @Mock
    private Event foundEvent;

    @Mock
    private Event event;

    @Before
    public void setup() {
        when(eventRepository.findById(any(UUID.class))).thenReturn(Optional.of(foundEvent));
        eventService = new DefaultEventServiceImpl(eventRepository);
    }

    @Test
    public void update_callsFind_withTheId() throws Exception {
        UUID id = UUID.randomUUID();
        eventService.update(id, event);
        verify(eventRepository).findById(id);
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_ifFindFoundNoElement_exceptionIsThrown() throws Exception {
        when(eventRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        eventService.update(UUID.randomUUID(), event);
    }

    @Test
    public void update_callsRepositoryUpdate_withTheEvent() throws Exception {
        eventService.update(UUID.randomUUID(), event);
        verify(eventRepository).save(event);
    }

//    @Test
//    public void WhenEventServiceIsInjected_ThenNotNull() {
//        assertNotNull(this.eventService);
//    }
//
//    @Test
//    public void WhenCreateEvent_ThenItReturnCreated() {
//        Event created = eventService.create(foundEvent);
//        assertNotNull(created);
//        assertEquals(foundEvent.getAction(), created.getAction());
//    }
//
//    @Test
//    public void WhenFindEventById_ThenItReturnIt() throws Exception {
//        Event created = eventService.create(foundEvent);
//        assertNotNull(created);
//
//        Event found = eventService.find(created.getId());
//        assertNotNull(found);
//
//        assertEquals(created, found);
//    }
//
//    @Test(expected = EntityNotFoundException.class)
//    public void WhenEventIsNotExisted_ThenThrowException() throws Exception {
//        eventService.find(UUID.randomUUID());
//    }
//
//    @Test
//    public void TestUpdateExistingEvent() throws Exception {
//        //insert one entry
//        Event created = eventService.create(foundEvent);
//        assertNotNull(created);
//
//        //modify it
//        Event updated = created;
//        updated.setSource(Source.C.toString());
//
//        //update
//        Event result = eventService.update(created.getId(), updated);
//        assertNotNull(result);
//
//        assertEquals(updated, result);
//    }
//
}