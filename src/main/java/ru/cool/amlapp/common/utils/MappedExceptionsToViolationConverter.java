package ru.cool.amlapp.common.utils;

import ru.cool.amlapp.common.exceptions.MappedExceptions;
import ru.cool.amlapp.common.domain.Violation;

public class MappedExceptionsToViolationConverter {

 public static Violation convertToViolation (MappedExceptions mappedExceptions) {
    return new Violation(mappedExceptions.getCode(), mappedExceptions.getTextexception());
  }


}
