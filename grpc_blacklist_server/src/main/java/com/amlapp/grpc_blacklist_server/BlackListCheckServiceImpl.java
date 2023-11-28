package com.amlapp.grpc_blacklist_server;

import com.amlapp.grpc_blacklist_server.repository.BlackListRepository;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antifraud.grpc.BlackListCheckServiceGrpc;
import ru.antifraud.grpc.BlackListCheckerService;
import ru.antifraud.grpc.BlackListCheckerService.BlackListCheckRequest;
import ru.antifraud.grpc.BlackListCheckerService.BlackListCheckResponse;
import ru.antifraud.grpc.BlackListCheckerService.CheckResult;

@Service
public class BlackListCheckServiceImpl extends BlackListCheckServiceGrpc.BlackListCheckServiceImplBase {

  @Autowired
  BlackListRepository blackListRepository;

  public void blackListChecking(BlackListCheckRequest request,
      StreamObserver<BlackListCheckResponse> responseStreamObserver) {
    CheckResult checkResult;

    if(blackListRepository.existsByPhone(request.getPersonId())){
      checkResult = CheckResult.EXIST_IN_BLACK_LIST;
    } else {
      checkResult = CheckResult.NO_MATCH_FOUND;
    };

    BlackListCheckResponse  response = BlackListCheckerService.BlackListCheckResponse.newBuilder().
        setCheckResult(checkResult)
        .setPersonId(request.getPersonId())
        .build();

    responseStreamObserver.onNext(response);
    responseStreamObserver.onCompleted();

  }

}
