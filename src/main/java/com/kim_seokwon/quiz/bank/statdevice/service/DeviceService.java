package com.kim_seokwon.quiz.bank.statdevice.service;

import com.kim_seokwon.quiz.bank.statdevice.model.Device;
import com.kim_seokwon.quiz.bank.statdevice.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository    deviceRepository;
    @Autowired
    public DeviceService(final DeviceRepository    deviceRepository ) {
        this.deviceRepository = deviceRepository;
    }
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }


}
