package com.kim_seokwon.quiz.bank.statdevice.repository;

import com.kim_seokwon.quiz.bank.statdevice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
