package com.rizalfadiaalfikri.echosphere.utils.responses;

public enum StatusResponse {

  SUCCESS(200, "Success"),
  CREATED(201, "Data Successfully Created"),
  READ(200, "Data Found"),
  UPDATED(200, "Data Has been Updated"),
  DELETED(200, "Data Successfully Deleted"),
  NO_CONTENT(204, "No Content"),
  BAD_REQUEST(400, "Bad Request"),
  NOT_FOUND(404, "Entity/Row Not Found"),
  INTERNAL_SERVER_ERROR(500, "Internal Server Error");

  private int code;

  private String value;

  StatusResponse(int code, String value) {
    this.code = code;
    this.value = value;
  }

  StatusResponse(int code) {
    this.code = code;
  }

  StatusResponse(String value) {
    this.value = value;
  }

  public int getCode() {
    return code;
  }

  public String getValue() {
    return value;
  }

}
