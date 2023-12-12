package com.amlapp.grpc_blacklist_server.service;

import com.amlapp.grpc_blacklist_server.service.BlackListCheckServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StartServer {

  @Value("${grpc.server.port}")
  int port;

  @Autowired
  BlackListCheckServiceImpl blackListCheckService;

  @PostConstruct
  public void startServer() throws IOException, InterruptedException {
    Server server = ServerBuilder.forPort(port).addService(blackListCheckService).build();
    server.start();
    System.out.println("server started");
    server.awaitTermination();
  }



}
