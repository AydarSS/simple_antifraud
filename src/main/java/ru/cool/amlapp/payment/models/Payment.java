package ru.cool.amlapp.payment.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
}