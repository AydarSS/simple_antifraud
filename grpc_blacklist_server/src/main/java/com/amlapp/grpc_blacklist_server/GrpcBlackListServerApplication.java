package com.amlapp.grpc_blacklist_server;

import com.amlapp.grpc_blacklist_server.model.BlackList;
import com.amlapp.grpc_blacklist_server.repository.BlackListRepository;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GrpcBlackListServerApplication {

  @Value("${server.port}")
  static int port;

  public static void main(String[] args) throws IOException, InterruptedException {
    new SpringApplicationBuilder(GrpcBlackListServerApplication.class)
        .web(WebApplicationType.NONE)
        .run(args);
    Server server = ServerBuilder.forPort(port).addService(new BlackListCheckServiceImpl()).build();
    server.start();
    System.out.println("server started");
    server.awaitTermination();
  }

  @Bean
  public CommandLineRunner commandLineRunnerBean(BlackListRepository blackListRepository) {
    return args -> {
      blackListRepository.save(new BlackList("79003231305"));
    };
  }

}
