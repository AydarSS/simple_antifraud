package ru.cool.amlapp.payment.impl;

import static ru.cool.amlapp.common.exceptions.MappedExceptions.RECORDEXISTS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cool.amlapp.common.exceptions.ApiRequestException;
import ru.cool.amlapp.payment.handlers.PaymentHandler;
import ru.cool.amlapp.payment.api.PaymentCheckService;
import ru.cool.amlapp.payment.models.Payment;
import ru.cool.amlapp.payment.models.PaymentStatus;
import ru.cool.amlapp.payment.repositories.PaymentRepository;
import ru.cool.amlapp.payment.response.CheckDetail;
import ru.cool.amlapp.payment.response.PaymentCheckResponse;
import ru.cool.amlapp.payment.response.PaymentCheckResponseSuccess;
import ru.cool.amlapp.payment.response.PaymentCheckResponseWithErrors;

@Service
public class PaymentCheckServiceImpl  implements PaymentCheckService {

  @Autowired
  List<PaymentHandler> paymentHandlers;

  @Autowired
  private PaymentRepository paymentRepository;

  @Override
  public PaymentCheckResponse checkPayment(Payment payment) {
    List<CheckDetail> checksResults;

    saveAndMarkAsProcessed(payment);

    checksResults = check(payment);

    if (!isChecksPassed(checksResults)) {

      markNotPassedChecks(payment);
      return new PaymentCheckResponseWithErrors(1,checksResults);
    }

    markSuccessPassedChecks(payment);
    return new PaymentCheckResponseSuccess(0);
  }

  private boolean isChecksPassed(List<CheckDetail> checksResults) {
    return checksResults.stream().filter(checkDetail -> !checkDetail.isSuccessCheck()).collect(Collectors.toList()).isEmpty();
  }

  private void saveAndMarkAsProcessed(Payment payment) {
    Optional<Payment> paymentExisting = paymentRepository.findPaymentByPaymentId(payment.getPaymentId());
    if (paymentExisting.isPresent()){
      throw new ApiRequestException(RECORDEXISTS);
    }
    payment.setStatus(PaymentStatus.PROCESSED);
    paymentRepository.save(payment);
  }

  private void markNotPassedChecks(Payment payment) {
    payment.setStatus(PaymentStatus.REJECTED);
    paymentRepository.save(payment);
  }

  private void markSuccessPassedChecks(Payment payment) {
    payment.setStatus(PaymentStatus.SUCCESS);
    paymentRepository.save(payment);
  }

  private List<CheckDetail> check (Payment payment) {
    List<CheckDetail> checkDetailList = new ArrayList<>();
    for (PaymentHandler paymentHandler: paymentHandlers) {
      checkDetailList.add(paymentHandler.handle(payment));
    }
    return checkDetailList;
  }
}
