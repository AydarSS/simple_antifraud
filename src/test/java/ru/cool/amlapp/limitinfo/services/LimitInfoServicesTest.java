package ru.cool.amlapp.limitinfo.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.cool.amlapp.common.exceptions.ApiRequestException;
import ru.cool.amlapp.limitinfo.models.LimitInfo;
import ru.cool.amlapp.limitinfo.models.LimitType;
import ru.cool.amlapp.limitinfo.repositories.LimitInfoRepository;

@ExtendWith(MockitoExtension.class)
class LimitInfoServicesTest {

  @Mock
  private LimitInfoRepository limitInfoRepository;

  @InjectMocks
  private LimitInfoServiceImpl limitInfoService;

  List<LimitInfo> limits = getlimits();

  private List<LimitInfo> getlimits() {
    return List.of(new LimitInfo(LimitType.DAY,new BigDecimal(10000)),
        new LimitInfo(LimitType.MONTH,new BigDecimal(10000)) );
  }

  @Test
  void shouldReturnAllLimitsFidAllTest() {
    Mockito.when(limitInfoRepository.findAll()).thenReturn(limits);
    List<LimitInfo> limitsReturn = limitInfoService.getAllLimits();
    Assertions.assertEquals(limits,limitsReturn);
  }

  @Test
  void shouldThrowSaveLimitTest() {
    Optional<LimitInfo> limitInfo = Optional.of(new LimitInfo(LimitType.DAY,new BigDecimal(10000)));
    Mockito.when(limitInfoRepository.findByLimitTypeAndMaximumAmountInLimit(any(),any())).thenReturn(limitInfo);
    assertThatThrownBy(() ->  limitInfoService.saveLimit(new LimitInfo(LimitType.DAY,new BigDecimal(10000))))
        .isInstanceOf(ApiRequestException.class);
  }

  @Test
  void shouldGoodSaveLimitTest() {
    Mockito.when(limitInfoRepository.findByLimitTypeAndMaximumAmountInLimit(any(),any())).thenReturn(Optional.empty());
    limitInfoService.saveLimit(new LimitInfo(LimitType.DAY,new BigDecimal(10000)));
    verify(limitInfoRepository, times(1)).save(any());
  }

  @Test
  void shouldThrowUpdateLimitTest() {
    Mockito.when(limitInfoRepository.findById(any())).thenReturn(Optional.empty());
    assertThatThrownBy(() ->  limitInfoService.updateLimit(1,new LimitInfo(LimitType.DAY,new BigDecimal(10000))))
        .isInstanceOf(ApiRequestException.class);
  }

  @Test
  void shouldGoodUpdateLimitTest() {
    LimitInfo limitNew = new LimitInfo(LimitType.DAY,new BigDecimal(50000));
    Optional<LimitInfo> limitInfo = Optional.of(new LimitInfo(LimitType.DAY,new BigDecimal(90000)));
    Mockito.when(limitInfoRepository.findById(any())).thenReturn(limitInfo);
    limitInfoService.updateLimit(1, new LimitInfo(LimitType.DAY,new BigDecimal(50000)));
    verify(limitInfoRepository, times(1)).save(limitNew);
  }


}
