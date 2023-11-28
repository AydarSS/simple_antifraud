package ru.cool.amlapp.payment.handlers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cool.amlapp.payment.handlers.PaymentHandler;
import ru.cool.amlapp.payment.handlers.checkers.api.LimitChecker;
import ru.cool.amlapp.payment.models.Payment;
import ru.cool.amlapp.payment.response.CheckDetail;

@Component
public class LimitPaymentHandler implements PaymentHandler {

  @Autowired
  LimitChecker limitChecker;

  @Override
  public CheckDetail handle(Payment payment) {
    return limitChecker.checkLimit(payment);
  }
}
