package ru.cool.amlapp.common.exceptions;

import static ru.cool.amlapp.common.exceptions.MappedExceptions.INVALID_ARGUMENTS;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.cool.amlapp.common.exceptions.domain.ErrorResponseEntity;
import ru.cool.amlapp.common.exceptions.domain.Violation;
import ru.cool.amlapp.common.exceptions.utils.MappedExceptionsToViolationConverter;

@RestControllerAdvice
public class ApiRequestExceptionHandler {

  @ExceptionHandler(ApiRequestException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseEntity handleException(ApiRequestException apiRequestException) {
    Violation violation = new Violation(
        apiRequestException.getCode(),
        apiRequestException.getMessage());
    return new ErrorResponseEntity(Collections.singletonList(violation));
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseEntity handleException(
      HttpMessageNotReadableException notReadableException) {
    Violation violation = MappedExceptionsToViolationConverter.convertToViolation(INVALID_ARGUMENTS);
    return new ErrorResponseEntity(Collections.singletonList(violation));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponseEntity handleException(
      MethodArgumentNotValidException methodArgumentNotValidException) {
    final List<Violation> violations = methodArgumentNotValidException
        .getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> new Violation(
            0,
            String.format("%s - %s", error.getField(), error.getDefaultMessage())))
        .collect(Collectors.toList());
    return new ErrorResponseEntity(violations);
  }

}
