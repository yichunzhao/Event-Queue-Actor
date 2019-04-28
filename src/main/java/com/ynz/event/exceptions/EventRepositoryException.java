package com.ynz.event.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Event repository layer error.")
public  class EventRepositoryException extends RuntimeException {

    public EventRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
