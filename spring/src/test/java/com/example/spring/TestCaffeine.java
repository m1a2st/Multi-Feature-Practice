package com.example.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.spring.entity.mysql.Student;
import com.example.spring.propmap.models.AccountPropMap;
import com.example.spring.propmap.models.ServerPropMap;
import com.example.spring.repository.postgres.DeciRepository;
import com.example.spring.service.AddressService;
import com.example.spring.service.SchoolService;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class TestCaffeine {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    AddressService addressService;

    @Autowired
    SchoolService schoolService;

    @Autowired
    AccountPropMap accountPropMap;

    @Autowired
    ServerPropMap serverPropMap;

    @Autowired
    DeciRepository deciRepository;

    @Test
    public void testCache() {
        addressService.getAddress("test");
        addressService.getAddress("test");
    }

    @Test
    public void testAop() {
        String tt = addressService.aopTest(null);
        System.out.println("testAOP: " + tt);
    }

    @Test
    public void testBigDecimal() {
        System.out.println(addressService.initBigDecimalData());
    }

    @Test
    public void testController() throws Exception {
        mockMvc.perform(get("/test")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void testFormatter() {
        DecimalFormat format = new DecimalFormat("###,###.00");
        BigDecimal bigDecimal = new BigDecimal("123456789.123456789");
        System.out.println(format.format(bigDecimal));
    }

    @Test
    public void testSchool() {
        schoolService.setClassroomAndStudent();
    }

    @Test
    public void testBatchInsertClassroom() {
        schoolService.batchInsertClassroom();
    }

    @Test
    public void testBatchInsertStudent() {
        schoolService.batchInsertStudent();
    }

    @Test
    public void testBatchInsertClassroomAndStudent() {
        Assertions.assertThrows(RuntimeException.class, () -> schoolService.batchInsertClassroomAndStudent());
    }

    @Test
    public void test() {
        assertEquals("qwert", accountPropMap.getAccount());
        assertEquals("qwert12345", accountPropMap.getPassword());
        assertEquals("127.0.0.1", serverPropMap.getUrl());
        assertEquals("me", serverPropMap.getAuth());
        assertEquals("default", serverPropMap.getNone());
    }

    @Test
    public void testInsertStudent() {
        schoolService.saveStudent(
                Student.builder().name("student132323232323").age(10).build());
    }

    @Test
    public void testDeci() {
        deciRepository.findAll().forEach(System.out::println);
    }
}
