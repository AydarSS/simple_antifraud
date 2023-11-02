package ru.cool.amlapp.common.exceptions;

import lombok.Getter;

public class ApiRequestException  extends RuntimeException{

  @Getter
  int code;

  public ApiRequestException(MappedExceptions mappedException) {
    super(mappedException.getTextexception());
    this.code = mappedException.getCode();
  }
}

