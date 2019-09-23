package com.kim_seokwon.quiz.bank.statdevice.authentication;

import com.kim_seokwon.quiz.bank.statdevice.TestUtils;
import com.kim_seokwon.quiz.bank.statdevice.config.SecurityConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(JwtAuthenticationController.class)
@ContextConfiguration(classes={SecurityConfig.class})
public class JwtAuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("회원 가입 테스트")
    public void successToSignUp() throws Exception {

    }


}
