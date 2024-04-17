package com.qywk.common.core.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/**
 * @author qlh
 * @date 2023/10/27 11:38
 * @description
 */
public class MyBeanUtils {
    /**
     * 获取对象中值为null的属性名称数组
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }

        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 将源对象的字段值copy到目标对象
     * 只对相同字段名进行复制
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        Class<?> srcClass = source.getClass();
        Class<?> tgtClass = target.getClass();
        Field[] srcFields = srcClass.getDeclaredFields();
        Field[] tgtFields = tgtClass.getDeclaredFields();
        try {
            for (Field srcField : srcFields) {
                String srcFieldName = srcField.getName();
                String tgtFieldName = convertToCamelCase(srcFieldName);
                for (Field tgtField : tgtFields) {
                    if (convertToCamelCase(tgtField.getName()).equals(tgtFieldName)) {
                        srcField.setAccessible(true);
                        tgtField.setAccessible(true);
                        tgtField.set(target, srcField.get(source));
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用于将下划线命名的字符串转换为驼峰命名的字符串。
     * @param name 字段名
     * @return
     */
    private static String convertToCamelCase(String name) {
        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = false;
        for (char c : name.toCharArray()) {
            if (c == '_') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                sb.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
