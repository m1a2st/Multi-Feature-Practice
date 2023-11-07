package com.example.spring.propmap.models;

import com.example.spring.propmap.annotation.PropMapBean;
import com.example.spring.propmap.annotation.PropMapDefaultValue;
import com.example.spring.propmap.annotation.PropMapKey;
import lombok.Getter;

@Getter
@PropMapBean(group = PropMapGroup.CACHE)
public class ServerPropMap {

    @PropMapKey("url")
    private String url;
    @PropMapKey("auth")
    private String auth;
    @PropMapKey("none")
    @PropMapDefaultValue("default")
    private String none;
}
