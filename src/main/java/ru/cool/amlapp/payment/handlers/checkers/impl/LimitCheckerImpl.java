package ru.cool.amlapp.payment.handlers.checkers.impl;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cool.amlapp.payment.handlers.checkers.api.LimitChecker;
import ru.cool.amlapp.limitinfo.models.LimitInfo;
import ru.cool.amlapp.limitinfo.repositories.LimitInfoRepository;
import ru.cool.amlapp.payment.models.Payment;
import ru.cool.amlapp.payment.repositories.PaymentRepository;
import ru.cool.amlapp.payment.response.CheckDetail;

@Component
public class LimitCheckerImpl implements LimitChecker {

  final private String limitExceededText = "Limit exceeded by %s (%s), previous sum %s";

  final private String type = "Limit";

  @Autowired
  LimitInfoRepository limitInfoRepository;

  @Autowired
  PaymentRepository paymentRepository;

  @Override
  public CheckDetail checkLimit(Payment payment) {
    List<LimitInfo> limitInfoList = limitInfoRepository.findAll();
    List<String> errorDescription= new ArrayList<>();
    BigDecimal  sumPreviousPaymentsByDate;
    OffsetDateTime beginDateTime;
    boolean isSuccessCheck = true;

    for (LimitInfo limitInfo: limitInfoList) {
      beginDateTime = limitInfo.getLimitType().calculateBeginDateByDate(payment.getPaymentDateTime());
      sumPreviousPaymentsByDate = paymentRepository.findPreviousPaymentSumMoreThanDate(beginDateTime);
      if (limitInfo.getMaximumAmountInLimit().compareTo(sumPreviousPaymentsByDate.add(payment.getAmount())) < 0) {
        errorDescription.add(
                String.format(limitExceededText, limitInfo.getLimitType(), limitInfo.getMaximumAmountInLimit(),
                    sumPreviousPaymentsByDate.toString()));
        isSuccessCheck = false;
      }
    }
    return new CheckDetail(type,isSuccessCheck, errorDescription);
  }
}
