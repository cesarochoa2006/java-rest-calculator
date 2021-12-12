package com.cesarochoa.rest.calculator;

import com.cesarochoa.calculator.JavaRestCalculatorApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {JavaRestCalculatorApplication.class})
class ApplicationTest {

    @Autowired
    ApplicationContext context;

    @Test
    void contextTest(){
        assertNotNull(context);
    }
}
