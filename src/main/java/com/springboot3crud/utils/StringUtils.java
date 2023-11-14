package com.springboot3crud.utils;

import com.google.gson.Gson;

import java.util.Objects;

public class StringUtils {

    public static boolean isNotEmpty(String str) {
        return Objects.nonNull(str) && !str.trim().isEmpty();
    }

    public static boolean nonNull(Object boj) {
        return Objects.nonNull(boj);
    }

    public static boolean isNotEmpty(Integer integer) {
        return Objects.nonNull(integer) && integer > 0;
    }

    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }

    public static boolean isNotEmpty(Object obj) {
        return Objects.nonNull(obj);
    }

    public static String objectToJson(Object obj) {
        return new Gson().toJson(obj);
    }

}