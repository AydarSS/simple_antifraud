package ru.cool.amlapp.payment.response;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentCheckResponseWithErrors extends PaymentCheckResponse {

  private int status;
  private List<CheckDetail> errorsDetails;

  @Override
  public String toString() {
    return "PaymentCheckResponse{" +
        "status=" + status +
        ", errorsDetails=" + errorsDetails +
        '}';
  }
}
