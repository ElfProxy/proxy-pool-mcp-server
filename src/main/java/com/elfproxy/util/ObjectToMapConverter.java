package com.elfproxy.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Convert objects to maps
 *
 * @author ElfProxy
 */
public class ObjectToMapConverter {

    // 缓存类的字段信息
    private static final ConcurrentHashMap<Class<?>, Field[]> FIELD_CACHE = new ConcurrentHashMap<>();

    public static Map<String, Object> convertToMap(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return new HashMap<>();
        }

        Class<?> clazz = obj.getClass();
        Field[] fields = FIELD_CACHE.computeIfAbsent(clazz, ObjectToMapConverter::getDeclaredFields);

        Map<String, Object> resultMap = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            resultMap.put(field.getName(), field.get(obj));
        }

        return resultMap;
    }

    private static Field[] getDeclaredFields(Class<?> clazz) {
        Field[] allFields = clazz.getDeclaredFields();
        Field[] publicFields = clazz.getFields();
        Field[] result = new Field[allFields.length + publicFields.length];

        System.arraycopy(allFields, 0, result, 0, allFields.length);
        System.arraycopy(publicFields, 0, result, allFields.length, publicFields.length);

        return result;
    }

}
