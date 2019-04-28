package com.ynz.event.repository;

import com.ynz.event.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Do not implement the repository, somebody else will do that. You may change the interface though
 */

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    //void persist(Event event) throws EventRepositoryErrorException;
}
