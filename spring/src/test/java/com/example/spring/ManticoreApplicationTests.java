package com.example.spring;

import com.example.config.RestTemplateConfig;
import com.example.manticore.ManticoreClient;
import com.manticoresearch.client.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ManticoreClient.class, RestTemplateConfig.class})
class ManticoreApplicationTests {

    @Autowired
    ManticoreClient manticoreClient;

    @Test
    void contextLoads() {
    }

    @Test
    public void testCreateTable() throws ApiException {
        manticoreClient.createTable();
    }

    @Test
    public void testInsert() {
        manticoreClient.insert();
    }

    @Test
    public void testSearchAll() throws ApiException {
        manticoreClient.searchAll();
    }
}
