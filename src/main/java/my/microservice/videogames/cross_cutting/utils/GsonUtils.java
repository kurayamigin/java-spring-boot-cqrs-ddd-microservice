package my.microservice.videogames.cross_cutting.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class GsonUtils {
    private static final Gson gson = new Gson();

    public static <T> T fromJson(String json, Class<T> t) {
        return gson.fromJson(json, t);
    }
}
