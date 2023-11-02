package ru.cool.amlapp.limitinfo.models;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class LimiTypeSetValidator implements ConstraintValidator<LimiTypeSet, LimitType> {
  private LimitType[] subset;

  @Override
  public void initialize(LimiTypeSet constraint) {
    this.subset = constraint.anyOf();
  }

  @Override
  public boolean isValid(LimitType value, ConstraintValidatorContext context) {
    return value == null || Arrays.asList(subset).contains(value);
  }

}
