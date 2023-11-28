package ru.cool.amlapp.payment.handlers.checkers.api;

import ru.cool.amlapp.payment.models.Payment;
import ru.cool.amlapp.payment.response.CheckDetail;

public interface BlackListChecker {


  CheckDetail checkByBlackList (Payment payment);
}
