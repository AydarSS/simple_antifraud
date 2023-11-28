package ru.cool.amlapp.payment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentCheckResponseSuccess extends PaymentCheckResponse {

  int code;

}
