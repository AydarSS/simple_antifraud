package ru.cool.amlapp.payment.handlers.checkers.impl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.antifraud.grpc.BlackListCheckServiceGrpc;
import ru.antifraud.grpc.BlackListCheckServiceGrpc.BlackListCheckServiceBlockingStub;
import ru.antifraud.grpc.BlackListCheckerService.BlackListCheckRequest;
import ru.antifraud.grpc.BlackListCheckerService.BlackListCheckResponse;
import ru.antifraud.grpc.BlackListCheckerService.CheckResult;

@Component
class BlackListCheckerProvider {

  @Value("${grpcserver.url}")
  private static String url;

  ManagedChannel channel = ManagedChannelBuilder.forTarget("localhost:9090").usePlaintext().build();;


  public boolean isExistInBlackList(String personId) {
    BlackListCheckServiceBlockingStub stub = BlackListCheckServiceGrpc.newBlockingStub(channel);

    BlackListCheckRequest request = BlackListCheckRequest.newBuilder().setPersonId(personId).build();

    BlackListCheckResponse response = stub.blackListChecking(request);

    return response.getCheckResult().equals(CheckResult.EXIST_IN_BLACK_LIST);

  }


}
