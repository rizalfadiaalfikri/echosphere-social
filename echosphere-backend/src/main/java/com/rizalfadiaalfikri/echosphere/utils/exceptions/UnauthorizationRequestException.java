package com.rizalfadiaalfikri.echosphere.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizationRequestException extends RuntimeException {

    public UnauthorizationRequestException() {
    }

    public UnauthorizationRequestException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnauthorizationRequestException(final String message) {
        super(message);
    }

    public UnauthorizationRequestException(final Throwable cause) {
        super(cause);
    }
}
