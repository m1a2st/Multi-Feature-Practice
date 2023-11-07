package com.example.spring.propmap.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PropMapGroup {
    GENERAL(0),
    DATABASE(1),
    CACHE(2),
    SECURITY(3),
    LOGGING(4),
    OTHER(5);

    private final Integer groupCode;
}
