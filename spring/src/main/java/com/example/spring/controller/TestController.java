package com.example.spring.controller;

import com.example.spring.converter.TransferDTO;
import com.example.spring.models.BigDecimalData;
import com.example.spring.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final AddressService service;

    @GetMapping
    public BigDecimalData test() {
        return service.initBigDecimalData();
    }

    @PostMapping("/test2")
    public TransferDTO test2(@RequestBody TransferDTO dto) {
        return dto;
    }
}
