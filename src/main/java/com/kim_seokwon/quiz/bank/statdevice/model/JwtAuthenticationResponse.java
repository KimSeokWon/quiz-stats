package com.kim_seokwon.quiz.bank.statdevice.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(final String accessToken) {
        this.accessToken = accessToken;
    }
}
