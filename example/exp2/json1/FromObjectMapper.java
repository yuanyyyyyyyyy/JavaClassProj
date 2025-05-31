package com.example.exp2.json1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * JSON数据转对象
 */
public class FromObjectMapper {

    public static <T> T fromJson(String json, Class<T> clazz) throws Exception {
        json = json.trim();
        if (json.equals("null")) {
            return null;
        }
        if (!json.startsWith("{") || !json.endsWith("}")) {
            throw new IllegalArgumentException("Invalid JSON format");
        }
        json = json.substring(1, json.length() - 1).trim();
        String[] pairs = json.split(",");
        Constructor<T> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        T instance = constructor.newInstance();

        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length != 2) continue;

            String key = keyValue[0].trim().replaceAll("^\"|\"$", "");
            String valueStr = keyValue[1].trim();
            Field field;
            try {
                field = clazz.getDeclaredField(key);
            } catch (NoSuchFieldException e) {
                continue;
            }
            field.setAccessible(true);
            Object value = parseValue(valueStr, field.getType());
            field.set(instance, value);
        }
        return instance;
    }

    private static Object parseValue(String valueStr, Class<?> type) {
        valueStr = valueStr.trim();
        if (type == String.class) {
            return valueStr.replaceAll("^\"|\"$", "")
                    .replace("\\\"", "\"")
                    .replace("\\\\", "\\");
        }
        if (type == int.class || type == Integer.class) {
            return Integer.parseInt(valueStr);
        }
        if (type == long.class || type == Long.class) {
            return Long.parseLong(valueStr);
        }
        if (type == double.class || type == Double.class) {
            return Double.parseDouble(valueStr);
        }
        if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(valueStr);
        }
        throw new IllegalArgumentException("Unsupported type: " + type);
    }
}
