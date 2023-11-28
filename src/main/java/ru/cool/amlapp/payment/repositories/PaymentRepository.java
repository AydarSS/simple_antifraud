package ru.cool.amlapp.payment.repositories;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.cool.amlapp.payment.models.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

  Optional<Payment> findPaymentByPaymentId(long paymentId);

  @Query("SELECT coalesce(sum(p.amount),0) FROM Payment p WHERE p.paymentDateTime >= ?1 AND p.status = 'SUCCESS'")
  BigDecimal findPreviousPaymentSumMoreThanDate (OffsetDateTime paymentDateTime);


}
