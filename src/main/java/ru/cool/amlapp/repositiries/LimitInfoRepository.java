package ru.cool.amlapp.repositiries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.cool.amlapp.models.LimitInfo;

@Repository
public interface LimitInfoRepository  extends JpaRepository<LimitInfo,Long> {

}
