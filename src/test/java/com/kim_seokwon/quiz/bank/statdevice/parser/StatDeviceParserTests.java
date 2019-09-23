package com.kim_seokwon.quiz.bank.statdevice.parser;

import com.kim_seokwon.quiz.bank.statdevice.repository.ConnectionRepository;
import com.kim_seokwon.quiz.bank.statdevice.repository.DeviceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StatDeviceParserTests {

	@Autowired
	private DeviceRepository 		deviceRepository;
	@Autowired
	private ConnectionRepository 	connectionRepository;

	@Test
	@DisplayName("단말기 코드 목록 저장 단위 테스트")
	public void successToSaveDevice() {
		assertTrue(
				deviceRepository.count() > 0
		);
	}
	@Test
	@DisplayName("접속정보 목록 저장 단위 테스트")
	public void successToSaveConnectionStat() {
		assertTrue(
				connectionRepository.count() > 0
		);
	}
}
