package ru.cool.amlapp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "LimitInfo")
public class LimitInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LimitType limitType;
    BigDecimal maximumAmountInLimit;
}
