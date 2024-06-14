package com.rizalfadiaalfikri.echosphere.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RowNotFoundDetailNullException extends RuntimeException {

    public RowNotFoundDetailNullException() {
    }

    public RowNotFoundDetailNullException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RowNotFoundDetailNullException(final String message) {
        super(message);
    }

    public RowNotFoundDetailNullException(final Throwable cause) {
        super(cause);
    }
}
