package com.kim_seokwon.quiz.bank.statdevice.model;


import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor @ToString
public class ResponseDevice {
    private List device;

    @JsonGetter("device")
    public List getDevice() {
        return device;
    }
}
