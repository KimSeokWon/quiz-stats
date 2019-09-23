package com.kim_seokwon.quiz.bank.statdevice.exception;

public class DeviceStatException extends Exception {
    public static final int NOT_EXIST_DEVICE = 1;
    public static final int CANNOT_SAVE_DB = 2;

    private int errorCode;

    public DeviceStatException(final int errorCode) {
        this.errorCode = errorCode;
    }
}
