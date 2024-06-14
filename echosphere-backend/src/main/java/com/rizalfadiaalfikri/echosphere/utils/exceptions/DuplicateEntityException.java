package com.rizalfadiaalfikri.echosphere.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rizalfadiaalfikri.echosphere.utils.Utils;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateEntityException extends RuntimeException {

    public DuplicateEntityException() {
    }

    public DuplicateEntityException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DuplicateEntityException(final String message) {
        super(message);
    }

    public DuplicateEntityException(final Throwable cause) {
        super(cause);
    }

    @Override
    public String toString() {
        return "{code: " + HttpStatus.BAD_REQUEST.value() + ", message: " + getMessage()
                + ", success: " + false + ", version: " + Utils.getVersion();
    }
}
