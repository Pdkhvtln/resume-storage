package com.urise.webapp.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.urise.webapp.model.Section;

import java.io.Reader;
import java.io.Writer;

/**
 * Created by andrew on 07.03.17.
 */
public class JsonParser {
    private static Gson GSON = new GsonBuilder()
            .registerTypeAdapter(Section.class, new JsonSectionAdapter())
            .create();

    public JsonParser() {
    }

    public static <T> T read(Reader r, Class<T> c) {
        return GSON.fromJson(r, c);
    }

    public static <T> void write(T t, Writer w) {
        GSON.toJson(t, w);
    }

    public static <T> T read(String value, Class<T> c) {
        return GSON.fromJson(value, c);
    }

    public static <T> String write(T object) {
        return GSON.toJson(object);
    }

    public static <T> String write(T object, Class<T> c) {
        return GSON.toJson(object, c);
    }
}
