package com.example.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class PrototypeTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/prototype/asdfgh"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/prototype/asdfghhjkljl;j;l"))
                .andExpect(status().isOk());
    }
}
