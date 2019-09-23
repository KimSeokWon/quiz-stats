package com.kim_seokwon.quiz.bank.statdevice.repository;

import com.kim_seokwon.quiz.bank.statdevice.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

public interface DeviceRepository extends MongoRepository<Device, String> {
}
