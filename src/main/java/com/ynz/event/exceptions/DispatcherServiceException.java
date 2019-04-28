package com.ynz.event.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "maybe bean is not ejected...")
public class DispatcherServiceException extends Exception {

    public DispatcherServiceException(String message) {
        super(message);
    }

}
