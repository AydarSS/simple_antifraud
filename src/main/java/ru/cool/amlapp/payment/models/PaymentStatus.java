package ru.cool.amlapp.payment.models;

public enum PaymentStatus {

  PROCESSED("PROCESSED"),
  SUCCESS("SUCCESS"),
  REJECTED("REJECTED");

  private String code;

  PaymentStatus(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
