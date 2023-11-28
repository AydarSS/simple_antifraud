package ru.cool.amlapp.payment.handlers;

import ru.cool.amlapp.payment.models.Payment;
import ru.cool.amlapp.payment.response.CheckDetail;

public interface PaymentHandler {

  CheckDetail handle(Payment payment);

}
