package ru.cool.amlapp;

import java.math.BigDecimal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.cool.amlapp.limitinfo.models.LimitInfo;
import ru.cool.amlapp.limitinfo.models.LimitType;
import ru.cool.amlapp.limitinfo.repositories.LimitInfoRepository;

@SpringBootApplication
public class AmlappApplication {

  public static void main(String[] args) {
    SpringApplication.run(AmlappApplication.class, args);
    System.out.println("hello");
  }

  @Bean
  public CommandLineRunner commandLineRunnerBean(LimitInfoRepository limitInfoRepository) {
    return args -> {
      limitInfoRepository.deleteAll();
      limitInfoRepository.save(
          new LimitInfo(LimitType.DAY, new BigDecimal(10000.00)));
      limitInfoRepository.save(
          new LimitInfo(LimitType.MONTH, new BigDecimal(40000.00)));
    };      
  }

}
