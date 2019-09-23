package com.kim_seokwon.quiz.bank.statdevice.repository;

import com.kim_seokwon.quiz.bank.statdevice.model.Connection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConnectionRepository extends MongoRepository<Connection, String> {
}
