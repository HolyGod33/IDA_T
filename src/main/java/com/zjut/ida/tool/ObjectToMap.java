package com.zjut.ida.tool;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Casterx on 2020/7/22.
 */
public class ObjectToMap {
    public static Map objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map = objectToMap(obj, false);
        return map;
    }

    private static Map objectToMap(Object obj, boolean keepNullVal) {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if (keepNullVal == true) {
                    map.put(field.getName(), field.get(obj));
                } else {
                    if (field.get(obj) != null && !"".equals(field.get(obj).toString())) {
                        map.put(field.getName(), field.get(obj));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
