package com.learning.microservice.mywallet.repository;

import com.learning.microservice.mywallet.domain.LoginInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginInfoRepository extends CrudRepository<LoginInfo, Long> {
}
