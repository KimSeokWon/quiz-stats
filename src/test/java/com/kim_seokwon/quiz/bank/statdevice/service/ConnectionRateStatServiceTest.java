package com.kim_seokwon.quiz.bank.statdevice.service;

import com.kim_seokwon.quiz.bank.statdevice.model.DeviceRate;
import com.kim_seokwon.quiz.bank.statdevice.model.ResponseDevice;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DisplayName("기기별 접속률 조회 서비스 테스트")
public class ConnectionRateStatServiceTest {


    @Autowired
    private ConnectionRateStatService connectionRateStatService;



    public void setUp() {
        connectionRateStatService.createTempCollections();
    }
    @Test
    @DisplayName("연간 최대 접속 단말기 조회 테스트")
    public void successToGetMaxRateDeviceAnnually() {
        List<DeviceRate> conns = connectionRateStatService.getMaxRateAnnually();
        assertTrue(conns != null);
        assertTrue(conns.get(0).getYear() == 2011);
        assertTrue(conns.get(0).getRate() == 95.1);
        assertTrue(StringUtils.equals(conns.get(0).getDevice_name(), "데스크탑 컴퓨터"));

        assertTrue(conns.get(1).getYear() == 2012);
        assertTrue(conns.get(1).getRate() == 93.9);
        assertTrue(StringUtils.equals(conns.get(1).getDevice_name(), "데스크탑 컴퓨터"));

        assertTrue(conns.get(2).getYear() == 2013);
        assertTrue(conns.get(2).getRate() == 67.1);
        assertTrue(StringUtils.equals(conns.get(2).getDevice_name(), "데스크탑 컴퓨터"));

        assertTrue(conns.get(3).getYear() == 2014);
        assertTrue(conns.get(3).getRate() == 64.2);
        assertTrue(StringUtils.equals(conns.get(3).getDevice_name(), "스마트폰"));

        assertTrue(conns.get(4).getYear() == 2015);
        assertTrue(conns.get(4).getRate() == 73.2);
        assertTrue(StringUtils.equals(conns.get(4).getDevice_name(), "스마트폰"));
    }

    @Test
    @DisplayName("단말기별 최대 접속 연도 조회 테스트")
    public void successToGetMaxRateYearByDevice() {
        List<DeviceRate> list = connectionRateStatService.getMaxRateYearByDevice("DEVICE_00004");
        assertTrue(list.size() == 1);
        assertTrue(list.get(0).getRate() == 3.3);
        assertTrue(StringUtils.equals(list.get(0).getDevice_name(), "스마트패드"));

        list = connectionRateStatService.getMaxRateYearByDevice("DEVICE_00000");
        assertTrue(list.size() == 1);
        assertTrue(list.get(0).getRate() == 90.6);
        assertTrue(list.get(0).getYear() == 2017);
        assertTrue(StringUtils.equals(list.get(0).getDevice_name(), "스마트폰"));
    }
    @Test
    @DisplayName("연도별 최대 접속 단말기 조회 테스트")
    public void successToGetMaxRateDeviceByYear() {
        List<DeviceRate> list = connectionRateStatService.getMaxRateDeviceByYear(2018);
        assertTrue(list.size() == 1);
        assertTrue(list.get(0).getRate() == 90.5);
        assertTrue(StringUtils.equals(list.get(0).getDevice_name(), "스마트폰"));

        list = connectionRateStatService.getMaxRateDeviceByYear(2017);
        assertTrue(list.size() == 1);
        assertTrue(list.get(0).getRate() == 90.6);
        assertTrue(StringUtils.equals(list.get(0).getDevice_name(), "스마트폰"));
    }
}
