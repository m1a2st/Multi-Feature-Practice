package com.example.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

@AutoConfigureMockMvc
@SpringBootTest
public class PollingTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void pollingTest() throws Exception {
        MvcResult asyncListener = mockMvc
                .perform(get("/api/bake/cookie?bakeTime=1000"))
                .andExpect(request().asyncStarted())
                .andReturn();
        String response = mockMvc
                .perform(asyncDispatch(asyncListener))
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(response)
                .isEqualTo("Bake for cookie complete and order dispatched. Enjoy!");
    }
}
