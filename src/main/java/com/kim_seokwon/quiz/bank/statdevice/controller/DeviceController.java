package com.kim_seokwon.quiz.bank.statdevice.controller;

import com.kim_seokwon.quiz.bank.statdevice.model.ResponseDevice;
import com.kim_seokwon.quiz.bank.statdevice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value="/code-device")
public class DeviceController {
    private final DeviceService deviceService;

    @Autowired
    public DeviceController( final DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping(value = "devices")
    public @ResponseBody
    ResponseDevice getDevices() {
        return new ResponseDevice(this.deviceService.getDevices());
    }
}
