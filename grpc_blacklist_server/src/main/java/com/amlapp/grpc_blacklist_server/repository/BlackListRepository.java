package com.amlapp.grpc_blacklist_server.repository;

import com.amlapp.grpc_blacklist_server.model.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlackListRepository  extends JpaRepository<BlackList, Long> {

 boolean existsByPhone(String phone);
}
