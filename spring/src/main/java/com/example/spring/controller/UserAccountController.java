package com.example.spring.controller;

import com.example.spring.models.request.UserAccountRequest;
import com.example.spring.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user-accounts")
@RequiredArgsConstructor
public class UserAccountController {

    private final UserAccountService service;

    @PostMapping("create")
    public void create(@RequestBody UserAccountRequest request) {
        service.create(request);
    }

    @PostMapping("update")
    public void update(@RequestBody UserAccountRequest request) {
        service.update(request);
    }
}
