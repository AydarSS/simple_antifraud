package ru.cool.amlapp.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.cool.amlapp.payment.api.PaymentCheckService;
import ru.cool.amlapp.payment.models.Payment;
import ru.cool.amlapp.payment.response.PaymentCheckResponse;

@RestController
public class PaymentController {

  @Autowired
  private PaymentCheckService paymentCheckService;


  @PostMapping("/payments")
  PaymentCheckResponse checkPayment(@RequestBody Payment payment) {
    return paymentCheckService.checkPayment(payment);
  }
}
