package com.cesarochoa.rest.calculator;

import com.cesarochoa.calculator.JavaRestCalculatorApplication;
import com.cesarochoa.calculator.controllers.CalculatorController;
import com.cesarochoa.calculator.controllers.TimeController;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {JavaRestCalculatorApplication.class})
class ApplicationTest {

    @Autowired
    ApplicationContext context;

    @Autowired
    TimeController timeController;

    @Autowired
    CalculatorController calculatorController;

    @Test
    void contextTest(){
        assertNotNull(context);
    }

    @Test
    void testControllers(){
        assertNotNull(timeController);
        assertNotNull(calculatorController);
    }
}
