package ru.cool.amlapp.payment.api;

import ru.cool.amlapp.payment.models.Payment;
import ru.cool.amlapp.payment.response.PaymentCheckResponse;
import ru.cool.amlapp.payment.response.PaymentCheckResponseWithErrors;

public interface PaymentCheckService {

  PaymentCheckResponse checkPayment(Payment payment);

}
