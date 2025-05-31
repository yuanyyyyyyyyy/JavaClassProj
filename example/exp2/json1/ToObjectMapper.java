package com.example.exp2.json1;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 对象转JSON数据
 */
public class ToObjectMapper {

    public static String toJson(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return "null";
        }
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        StringBuilder json = new StringBuilder("{");
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);
            json.append("\"").append(field.getName()).append("\":");
            appendValue(json, value);
            json.append(",");
        }
        if (fields.length > 0) {
            json.deleteCharAt(json.length() - 1);
        }
        json.append("}");
        return json.toString();
    }

    private static void appendValue(StringBuilder json, Object value) throws IllegalAccessException {
        if (value == null) {
            json.append("null");
        } else if (value instanceof String || value instanceof Character) {
            json.append("\"").append(escapeString(value.toString())).append("\"");
        } else if (value instanceof Number || value instanceof Boolean) {
            json.append(value);
        } else {
            json.append(toJson(value));
        }
    }

    private static String escapeString(String s) {
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}