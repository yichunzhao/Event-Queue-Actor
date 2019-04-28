package com.ynz.event.service;

import com.ynz.event.entities.Event;
import com.ynz.event.exceptions.EntityNotFoundException;
import com.ynz.event.exceptions.EventRepositoryException;
import com.ynz.event.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DefaultEventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public DefaultEventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event find(UUID id) throws Exception{
        return eventRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UUID: " + id + " not found... "));
//        return null;
    }

    @Override
    public Event create(Event event) {
        Event created;

        try {
            created = eventRepository.save(event);
        } catch (Exception e) {
            throw new EventRepositoryException(e.getMessage(), e.getCause());
        }

        return created;
    }

    @Override
    public void delete(UUID id) throws Exception {
        this.find(id);
        this.eventRepository.deleteById(id);
    }

    @Override
    public Event update(UUID id, Event event) throws Exception {
        //this.find(id);
        return this.eventRepository.save(event);
//        return event;
    }
}
