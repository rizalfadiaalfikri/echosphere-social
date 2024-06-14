package com.rizalfadiaalfikri.echosphere.utils.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rizalfadiaalfikri.echosphere.utils.responses.ErrorResponse;
import com.rizalfadiaalfikri.echosphere.utils.responses.StatusResponse;

@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

  @Value("${application.version}")
  private String version;

  public GlobalRestExceptionHandler() {
    super();
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleException(Exception ex) {
    ErrorResponse error = new ErrorResponse(
        StatusResponse.INTERNAL_SERVER_ERROR.getCode(),
        ex.getMessage() != null ? ex.getMessage() : StatusResponse.INTERNAL_SERVER_ERROR.getValue(),
        version);
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(BadRequestException.class)
  public final ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getMessage());
    ErrorResponse error = new ErrorResponse(
        StatusResponse.BAD_REQUEST.getCode(),
        ex.getMessage() != null ? ex.getMessage() : StatusResponse.BAD_REQUEST.getValue(),
        version);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RowNotFoundException.class)
  public final ResponseEntity<Object> handleEntityNotFoundDetailNullException(RowNotFoundException ex,
      WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getMessage());
    ErrorResponse error = new ErrorResponse(
        StatusResponse.NOT_FOUND.getCode(),
        ex.getMessage() != null ? ex.getMessage() : StatusResponse.NOT_FOUND.getValue(),
        version);
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(FileAlreadyExistsException.class)
  public final ResponseEntity<Object> handleFileAlreadyExistException(FileAlreadyExistsException ex,
      WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getMessage());
    ErrorResponse error = new ErrorResponse(
        StatusResponse.BAD_REQUEST.getCode(),
        ex.getMessage() != null ? ex.getMessage() : StatusResponse.BAD_REQUEST.getValue(),
        version);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DuplicateEntityException.class)
  public final ResponseEntity<Object> handleDuplicateEntityException(DuplicateEntityException ex, WebRequest request) {
    List<String> details = new ArrayList<>();
    details.add(ex.getMessage());
    ErrorResponse error = new ErrorResponse(
        StatusResponse.BAD_REQUEST.getCode(),
        ex.getMessage() != null ? ex.getMessage() : StatusResponse.BAD_REQUEST.getValue(),
        version);
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

}
