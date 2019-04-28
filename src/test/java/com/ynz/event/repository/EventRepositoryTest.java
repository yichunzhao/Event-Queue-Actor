package com.ynz.event.repository;

import com.ynz.event.domainvalue.Action;
import com.ynz.event.domainvalue.Source;
import com.ynz.event.entities.Event;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class EventRepositoryTest {
    @Autowired
    protected EventRepository eventRepository;

    private Event event;

    @Before
    public void setup() {
        event = Event.builder()
                .createdAt(LocalDateTime.now())
                .action(Action.ADD.toString())
                .source(Source.A.toString()).build();
    }

    @Test
    public void WhenSaveAEventSuccessfully_ThenReturnTheSame() {
        Event persisted = eventRepository.save(event);
        assertNotNull(persisted);
        assertThat(persisted, is(event));
    }

    @Test
    public void WhenFindAEventByID_ThenReturnSaved() {
        Event persisted = eventRepository.save(event);
        assertNotNull(persisted);

        Event found = eventRepository.findById(persisted.getId())
                .orElseThrow(() -> new NoSuchElementException("not found"));

        assertNotNull(found);
        assertThat(found, is(persisted));
    }

}