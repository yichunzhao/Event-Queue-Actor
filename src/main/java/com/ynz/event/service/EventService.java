package com.ynz.event.service;

import com.ynz.event.entities.Event;

import java.util.UUID;

public interface EventService {
    Event find(UUID id ) throws Exception;
    Event create(Event event);
    void delete(UUID id) throws Exception;
    Event update(UUID id, Event event) throws Exception;
}
