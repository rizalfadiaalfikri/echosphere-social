package com.rizalfadiaalfikri.echosphere.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RowNotFoundException extends RuntimeException {

    public RowNotFoundException() {
    }

    public RowNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RowNotFoundException(final String message) {
        super(message);
    }

    public RowNotFoundException(final Throwable cause) {
        super(cause);
    }
}
