package ru.cool.amlapp.payment.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Data
@NoArgsConstructor
@Table (name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long  paymentId;
    String payerId;
    String payeeId;
    BigDecimal amount;
    OffsetDateTime paymentDateTime;
    PaymentStatus  status;

    public Payment(Long paymentId, String payerId, String payeeId, BigDecimal amount,
        OffsetDateTime paymentDateTime) {
        this.paymentId = paymentId;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        this.paymentDateTime = paymentDateTime;
    }

    public Payment(Long paymentId, String payerId, String payeeId, BigDecimal amount,
        OffsetDateTime paymentDateTime, PaymentStatus status) {
        this.paymentId = paymentId;
        this.payerId = payerId;
        this.payeeId = payeeId;
        this.amount = amount;
        this.paymentDateTime = paymentDateTime;
        this.status = status;
    }
}
