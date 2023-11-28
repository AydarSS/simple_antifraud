package ru.cool.amlapp.payment.handlers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cool.amlapp.payment.handlers.PaymentHandler;
import ru.cool.amlapp.payment.handlers.checkers.api.BlackListChecker;
import ru.cool.amlapp.payment.models.Payment;
import ru.cool.amlapp.payment.response.CheckDetail;

@Component
public class BlackListPaymentHandler implements PaymentHandler {

  @Autowired
  BlackListChecker blackListChecker;

  @Override
  public CheckDetail handle(Payment payment) {
    return blackListChecker.checkByBlackList(payment);
  }
}
