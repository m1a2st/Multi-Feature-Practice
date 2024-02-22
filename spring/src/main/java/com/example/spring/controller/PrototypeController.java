package com.example.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/prototype")
@RestController
@RequiredArgsConstructor
public class PrototypeController {

    private final Map<String, String> specialMap;

    @GetMapping("{key}")
    public ResponseEntity<Object> modify(@PathVariable String key) {
        specialMap.put(key, key);
        specialMap.forEach((k, v) -> System.out.println(k + " " + v));
        return ResponseEntity.ok().build();
    }
}
