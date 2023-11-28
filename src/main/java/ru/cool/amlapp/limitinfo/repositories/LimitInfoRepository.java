package ru.cool.amlapp.limitinfo.repositories;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cool.amlapp.limitinfo.models.LimitInfo;
import ru.cool.amlapp.limitinfo.models.LimitType;

@Repository
public interface LimitInfoRepository  extends JpaRepository<LimitInfo,Long> {

  Optional<LimitInfo> findByLimitTypeAndMaximumAmountInLimit (LimitType limitType, BigDecimal maximumAmountInLimit);

  Optional<LimitInfo> findByLimitType (LimitType limitType);

}
