package ru.cool.amlapp;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.cool.amlapp.limitinfo.models.LimitInfo;
import ru.cool.amlapp.limitinfo.models.LimitType;
import ru.cool.amlapp.limitinfo.repositories.LimitInfoRepository;
import ru.cool.amlapp.payment.models.Payment;
import ru.cool.amlapp.payment.models.PaymentStatus;
import ru.cool.amlapp.payment.repositories.PaymentRepository;

@SpringBootApplication
public class AmlappApplication {

  public static void main(String[] args) {
    SpringApplication.run(AmlappApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunnerBean(LimitInfoRepository limitInfoRepository,
      PaymentRepository paymentRepository) {
    return args -> {
      limitInfoRepository.deleteAll();
      limitInfoRepository.save(
          new LimitInfo(LimitType.DAY, new BigDecimal(10000.00)));
      limitInfoRepository.save(
          new LimitInfo(LimitType.MONTH, new BigDecimal(12000.00)));
      paymentRepository.deleteAll();
      paymentRepository.save(new Payment(123L, "+79003231300", "+79001110000", new BigDecimal(15000.00),
          OffsetDateTime.now(), PaymentStatus.SUCCESS));
    };
  }

}
