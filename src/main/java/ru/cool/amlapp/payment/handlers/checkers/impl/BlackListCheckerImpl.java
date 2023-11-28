package ru.cool.amlapp.payment.handlers.checkers.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.cool.amlapp.payment.handlers.checkers.api.BlackListChecker;
import ru.cool.amlapp.payment.models.Payment;
import ru.cool.amlapp.payment.response.CheckDetail;

@Component
public class BlackListCheckerImpl  implements BlackListChecker {

  @Autowired
  BlackListCheckerProvider blackListCheckerProvider;

  @Override
  public CheckDetail checkByBlackList(Payment payment) {
    List<String> persons = getPersonsFromPayment(payment);
    List<String> resultDescriptions = new ArrayList<>();
    boolean isSuccessChecks =  true;
    persons.stream().forEach(person -> {
      if (blackListCheckerProvider.isExistInBlackList(person)){
        resultDescriptions.add(createDescriptionWhenFindedInBlackList(person));
      };
    });
    if (!resultDescriptions.isEmpty()) {
      isSuccessChecks = false;
    }

    return new CheckDetail("black_list", isSuccessChecks,resultDescriptions);
  }

  private String createDescriptionWhenFindedInBlackList (String personId) {
    return String.format("%s finded in black list", personId);
  }

  private List<String> getPersonsFromPayment(Payment payment) {
    return List.of(payment.getPayerId(),payment.getPayeeId());
  }

}
