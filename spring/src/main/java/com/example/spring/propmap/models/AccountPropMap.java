package com.example.spring.propmap.models;

import com.example.spring.propmap.annotation.PropMapBean;
import com.example.spring.propmap.annotation.PropMapKey;
import lombok.Getter;

@Getter
@PropMapBean(group = PropMapGroup.DATABASE)
public class AccountPropMap {

    @PropMapKey("account")
    private String account;
    @PropMapKey("password")
    private String password;
}
