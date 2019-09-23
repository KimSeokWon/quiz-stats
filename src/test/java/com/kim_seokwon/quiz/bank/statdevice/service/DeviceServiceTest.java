package com.kim_seokwon.quiz.bank.statdevice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("접속기기 조회 서비스 테스트")
public class DeviceServiceTest {
    @Autowired
    private DeviceService deviceService;
    @Test
    @DisplayName("서비스 접속 기기 목록 얻기 성공")
    public void successToGetDevices() {
        assertTrue(
                deviceService.getDevices() != null && deviceService.getDevices().size() == 5
        );
    }

}
