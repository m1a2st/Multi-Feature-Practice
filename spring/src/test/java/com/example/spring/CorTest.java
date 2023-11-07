package com.example.spring;

import com.example.spring.cor.Message;
import com.example.spring.cor.chain.facade.EnrichmentStepFacade;
import com.example.spring.cor.test.Facade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
public class CorTest {

    @Autowired
    private EnrichmentStepFacade enrichmentStepFacade;
    @Autowired
    private Facade facade;

    @Test
    public void corTest(){
//        Message enrich = enrichmentStepFacade.enrich(new Message(new HashMap<>() {{
//            put("ip", "value1");
//            put("userId", "value2");
//            put("SESSIONID", "value3");
//        }}));
//        System.out.println(enrich);
    }

    @Test
    public void corTest1(){
        facade.doSomething();
    }
}
