package com.example.spring.service;

import com.example.spring.aop.ServiceAnnotation;
import com.example.spring.models.BigDecimalData;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final CacheManager cacheManager;

    @Cacheable(cacheNames = "addressTest", key = "#name")
    public String getAddress(String name) {
        System.out.println(cacheManager.getCacheNames());
        System.out.println("getAddress");
        return name + " address";
    }

    @ServiceAnnotation
    public String aopTest(LocalDateTime test) {
        System.out.println("In Service do something: " + test);
        return test == null ? "undefined" : test.toString();
    }

    public BigDecimalData initBigDecimalData() {
        return BigDecimalData.builder()
                .time(LocalDateTime.now())
                .one(new BigDecimal("1.2345678"))
                .two(new BigDecimal("12345678"))
                .three(new BigDecimal("12345678"))
                .four(new BigDecimal("12345678"))
                .five(new BigDecimal("12345678"))
                .six(null)
                .build();
    }
}
