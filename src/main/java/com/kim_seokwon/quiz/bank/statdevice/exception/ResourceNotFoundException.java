package com.kim_seokwon.quiz.bank.statdevice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter @AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Object fieldValue;
}
