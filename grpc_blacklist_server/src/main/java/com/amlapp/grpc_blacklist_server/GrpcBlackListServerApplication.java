package com.amlapp.grpc_blacklist_server;

import com.amlapp.grpc_blacklist_server.model.BlackList;
import com.amlapp.grpc_blacklist_server.repository.BlackListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GrpcBlackListServerApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(GrpcBlackListServerApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);

  }

  @Bean
  public CommandLineRunner commandLineRunnerBean(BlackListRepository blackListRepository) {
    return args -> {
      blackListRepository.deleteAll();
      blackListRepository.save(new BlackList("79003231305"));
    };
  }

}
