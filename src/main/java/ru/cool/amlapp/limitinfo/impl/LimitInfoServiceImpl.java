package ru.cool.amlapp.limitinfo.impl;

import static ru.cool.amlapp.common.exceptions.MappedExceptions.RECORDEXISTS;
import static ru.cool.amlapp.common.exceptions.MappedExceptions.RECORDNOTEXISTS;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cool.amlapp.common.exceptions.ApiRequestException;
import ru.cool.amlapp.limitinfo.api.LimitInfoService;
import ru.cool.amlapp.limitinfo.models.LimitInfo;
import ru.cool.amlapp.limitinfo.repositories.LimitInfoRepository;

import java.util.List;
@Service
public class LimitInfoServiceImpl implements LimitInfoService {

    @Autowired
    private LimitInfoRepository limitInfoRepository;

    @Override
    public List<LimitInfo> getAllLimits() {
        return limitInfoRepository.findAll();
    }

    @Override
    public void saveLimit(LimitInfo limitInfo) {
         Optional<LimitInfo> limitInfoExisting = limitInfoRepository.
             findByLimitTypeAndMaximumAmountInLimit(
                 limitInfo.getLimitType(),
                 limitInfo.getMaximumAmountInLimit());
         if (limitInfoExisting.isPresent()) {
             throw new ApiRequestException(RECORDEXISTS);
         }
            limitInfoRepository.save(limitInfo);
    }

  @Override
  public LimitInfo updateLimit(long id, LimitInfo limitInfoNew) {
      LimitInfo limitInfoChangeable = limitInfoRepository.findById(id).orElseThrow(() -> new ApiRequestException(RECORDNOTEXISTS));
        limitInfoChangeable.setLimitType(limitInfoNew.getLimitType());
        limitInfoChangeable.setMaximumAmountInLimit(limitInfoNew.getMaximumAmountInLimit());
        return limitInfoRepository.save(limitInfoChangeable);
  }

  @Override
  public void deleteLimit(long id) {
    LimitInfo limitInfoDEleteable = limitInfoRepository.findById(id).orElseThrow(() -> new ApiRequestException(RECORDNOTEXISTS));
    limitInfoRepository.delete(limitInfoDEleteable);
  }
}
