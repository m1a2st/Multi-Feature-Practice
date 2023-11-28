package com.example;

import com.example.util.JwtUtil;
import org.junit.jupiter.api.Test;

public class JwtUtilTest {

    @Test
    public void test() throws InterruptedException {
        String test = JwtUtil.generateToken("test");
        System.out.println(test);
        System.out.println(JwtUtil.validateToken(test));
        System.out.println(JwtUtil.getSubject(test));
    }
}
