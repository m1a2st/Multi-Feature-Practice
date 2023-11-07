package com.example.spring.propmap;

import com.example.spring.propmap.annotation.PropMapDefaultValue;
import com.example.spring.propmap.annotation.PropMapKey;
import org.springframework.core.env.Environment;

public class PropMapBeanReflector extends AbstractPropMapReflector<PropMapKey, PropMapDefaultValue> {

    public PropMapBeanReflector(Object instance, Environment environment) {
        super(instance, environment);
    }

    @Override
    public Class<PropMapKey> getKeyTypeAnnoClazz() {
        return PropMapKey.class;
    }

    @Override
    public Class<PropMapDefaultValue> getDefaultValueTypeAnnoClazz() {
        return PropMapDefaultValue.class;
    }
}
