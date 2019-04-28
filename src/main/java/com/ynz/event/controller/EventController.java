package com.ynz.event.controller;

import com.ynz.event.dispatcher.EventDispatcher;
import com.ynz.event.entities.Event;
import com.ynz.event.exceptions.DispatcherServiceException;
import com.ynz.event.exceptions.EventServiceErrorException;
import com.ynz.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private EventDispatcher eventDispatcher;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@RequestBody Event event) throws Exception {
        //persist and get id
        Event created = eventService.create(event);

        //put it into the Queue and persist it into database.
        fetchEventDispatcher().post(created);

        //create an event in database.
        return created;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Event find(@PathVariable String id) throws Exception {
        UUID idUUID = String2UUID(id);
        return eventService.find(idUUID);
    }

    private EventDispatcher fetchEventDispatcher() throws Exception {
        return Optional.ofNullable(eventDispatcher)
                .orElseThrow(() -> new DispatcherServiceException("Dispatcher is not present"));
    }

    private UUID String2UUID(String str) throws Exception {
        UUID result;
        try {
            result = UUID.fromString(str);
        } catch (RuntimeException e) {
            throw new EventServiceErrorException(e);
        }
        return result;
    }

}
