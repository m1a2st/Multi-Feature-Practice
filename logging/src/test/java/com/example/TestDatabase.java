package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDatabase {

    private static final Logger log = LoggerFactory.getLogger(TestDatabase.class);

    public static void main(String[] args) {
        log.info("select * from users");
        log.info("insert into users (id, name) values (1, 'John')");
        log.info("update users set name = 'John' where id = 1");
        log.info("delete from users where id = 1");
    }
}
