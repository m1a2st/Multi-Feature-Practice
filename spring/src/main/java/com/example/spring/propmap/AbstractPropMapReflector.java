package com.example.spring.propmap;

import com.example.spring.propmap.annotation.PropMapSubMap;
import com.example.spring.propmap.annotation.PropMapSubMapDefaultValue;
import com.example.spring.propmap.annotation.PropMapSubMapKey;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@AllArgsConstructor
public abstract class AbstractPropMapReflector<T1 extends Annotation, T2 extends Annotation> {

    private final Object instance;
    private final Environment environment;

    public abstract Class<T1> getKeyTypeAnnoClazz();

    public abstract Class<T2> getDefaultValueTypeAnnoClazz();

    public Object reflectDataToInstance(Map<String, String> data) {
        if (CollectionUtils.isEmpty(data)) {
            return instance;
        }
        Field[] declaredFields = instance.getClass().getDeclaredFields();

        Arrays.stream(declaredFields)
                .filter(field -> field.isAnnotationPresent(getKeyTypeAnnoClazz()))
                .forEach(field -> {
                    try {
                        T1 keyAnno = field.getAnnotation(getKeyTypeAnnoClazz());
                        String keyAnnoValue = getAnnoValue(keyAnno);
                        String value = null;
                        if (!data.containsKey(keyAnnoValue)) {
                            if (field.isAnnotationPresent(getDefaultValueTypeAnnoClazz())) {
                                T2 defaultValueAnno = field.getAnnotation(getDefaultValueTypeAnnoClazz());
                                String defaultValue = getAnnoValue(defaultValueAnno);
                                if (StringUtils.hasText(defaultValue)) {
                                    value = defaultValue;
                                }
                            }
                        } else {
                            value = data.get(keyAnnoValue);
                        }

//                        overrideByEnvPropertiesIfExist(field, keyAnnoValue, value);
                    } catch (Exception ignored) {
                    }
                });

        return instance;
    }

    private <T extends Annotation> String getAnnoValue(T keyAnnoInstance)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method valueMethod = keyAnnoInstance.annotationType().getMethod("value");
        Object invoke = valueMethod.invoke(keyAnnoInstance);
        if (invoke instanceof String) {
            return (String) invoke;
        }
        return invoke != null ? invoke.toString() : null;
    }

    /**
     * find in Environment properties
     * 優先權最高，即使 DB 有設定，抑或是 Java 端有指定 DefaultValue，都會被 Properties 中的覆蓋
     * 格式 PROP_MAP_CUSTOM.{PROP_KEY}
     */
    private void overrideByEnvPropertiesIfExist(Field field, String keyAnnoValue, String value) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        String valueInEnv = environment.getProperty("PROP_MAP_CUSTOM." + keyAnnoValue);
        if (valueInEnv != null) {
            value = valueInEnv;
        }

        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, instance, convertValue(value, field.getType()));
    }

    private Object convertValue(String value, Class<?> targetType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (value == null) {
            return null;
        }
        if (targetType == String.class) {
            return value;
        } else if (targetType == int.class || targetType == Integer.class) {
            return Integer.parseInt(value);
        } else if (targetType == long.class || targetType == Long.class) {
            return Long.parseLong(value);
        } else if (targetType == double.class || targetType == Double.class) {
            return Double.parseDouble(value);
        } else if (targetType == BigDecimal.class) {
            return new BigDecimal(value);
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            return Boolean.parseBoolean(value);
        } else if (targetType.isAnnotationPresent(PropMapSubMap.class)) {
            Map<String, String> subMap = new HashMap<>();
            try (Scanner scanner = new Scanner(value)) {
                scanner.useDelimiter(";");
                while (scanner.hasNext()) {
                    String pair = scanner.next();
                    try (Scanner pairScanner = new Scanner(pair)) {
                        pairScanner.useDelimiter("=");
                        subMap.put(pairScanner.next(), pairScanner.next());
                    }
                }
            }

            Constructor<?> declaredConstructor = targetType.getDeclaredConstructor();
            Object declaredInstance = declaredConstructor.newInstance();

            AbstractPropMapReflector<PropMapSubMapKey, PropMapSubMapDefaultValue> subMapReflector =
                    new AbstractPropMapReflector<>(declaredInstance, environment) {

                        @Override
                        public Class<PropMapSubMapKey> getKeyTypeAnnoClazz() {
                            return PropMapSubMapKey.class;
                        }

                        @Override
                        public Class<PropMapSubMapDefaultValue> getDefaultValueTypeAnnoClazz() {
                            return PropMapSubMapDefaultValue.class;
                        }
                    };
            return subMapReflector.reflectDataToInstance(subMap);
        } else {
            throw new RuntimeException("Unsupported property type: " + targetType.getName());
        }
    }
}
