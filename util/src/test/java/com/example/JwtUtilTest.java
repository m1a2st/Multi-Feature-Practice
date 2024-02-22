package com.example;

import com.example.util.JwtUtil;

public class JwtUtilTest {

    public void test() throws InterruptedException {
        String test = JwtUtil.generateToken("test");
        System.out.println(test);
        System.out.println(JwtUtil.validateToken(test));
        System.out.println(JwtUtil.getSubject(test));
    }
}
