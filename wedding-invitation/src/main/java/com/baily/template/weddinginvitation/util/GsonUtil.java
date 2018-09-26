package com.baily.template.weddinginvitation.util;

import com.google.gson.Gson;

public class GsonUtil {

    private static Gson gson = new Gson();

    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T fromJson(String json,Class<T> type) {
        return gson.fromJson(json,type);
    }
}
