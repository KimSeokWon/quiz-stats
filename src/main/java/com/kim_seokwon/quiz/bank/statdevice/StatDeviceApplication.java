package com.kim_seokwon.quiz.bank.statdevice;

import com.kim_seokwon.quiz.bank.statdevice.authentication.JwtAuthenticationController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = {"com.kim_seokwon.quiz.bank.statdevice"})
@EntityScan(basePackageClasses = {StatDeviceApplication.class, JwtAuthenticationController.class})
public class StatDeviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatDeviceApplication.class, args);
	}

}
