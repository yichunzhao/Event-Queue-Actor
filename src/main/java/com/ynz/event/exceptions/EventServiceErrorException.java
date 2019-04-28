package com.ynz.event.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EventServiceErrorException extends Exception {

    public EventServiceErrorException(Throwable e){
        addSuppressed(e);
    }

}
