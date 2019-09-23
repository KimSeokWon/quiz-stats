package com.kim_seokwon.quiz.bank.statdevice;

import com.google.gson.Gson;

public class TestUtils {
    public static String objToJson(Object o) {
        Gson gson = new Gson();

        return gson.toJson(o);
    }
}
