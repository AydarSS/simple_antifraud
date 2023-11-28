package ru.cool.amlapp.limitinfo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name= "LimitInfo",indexes = {
    @Index(name = "unique_by_limittype_and_amount", columnList = "limitType, maximumAmountInLimit", unique = true)
})
public class LimitInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @LimiTypeSet(anyOf = {LimitType.DAY, LimitType.MONTH, LimitType.WEEK})
    @NotNull
    LimitType limitType;

    @Min(1)
    @Max(100000000)
    @NotNull
    BigDecimal maximumAmountInLimit;

    public LimitInfo(LimitType limitType, BigDecimal maximumAmountInLimit) {
        this.limitType = limitType;
        this.maximumAmountInLimit = maximumAmountInLimit;
    }

}
