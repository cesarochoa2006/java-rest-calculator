package com.cesarochoa.calculator.services;

import com.cesarochoa.calculator.TestApplication;
import com.cesarochoa.calculator.calculate.CalculatorService;
import com.cesarochoa.calculator.calculate.operators.Operator;
import com.cesarochoa.calculator.calculate.operators.strategies.*;
import com.cesarochoa.calculator.domain.entities.SessionEntity;
import com.cesarochoa.calculator.domain.repositories.SessionRepository;
import com.cesarochoa.calculator.errormanagers.BusinessException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = {TestApplication.class})
class CalculatorServicesTest {
    private final Logger logger = LoggerFactory.getLogger(CalculatorServicesTest.class);

    @Mock
    SessionRepository sessionRepo;

    @Autowired
    ApplicationContext context;


    CalculatorService service;

    @BeforeEach
    void init() {
        service = new CalculatorService(context, sessionRepo);
    }

    @Test
    void initOperators() {
        var sum = context.getBean(Sum.class);
        var subtract = context.getBean(Subtract.class);
        var multiply = context.getBean(Multiply.class);
        var divide = context.getBean(Divide.class);
        var pow = context.getBean(Pow.class);
        assertNotNull(sum);
        assertInstanceOf(Operator.class, sum);
        assertNotNull(subtract);
        assertInstanceOf(Operator.class, subtract);
        assertNotNull(multiply);
        assertInstanceOf(Operator.class, multiply);
        assertNotNull(divide);
        assertInstanceOf(Operator.class, divide);
        assertNotNull(pow);
        assertInstanceOf(Operator.class, pow);
    }


    @Test
    void sumTest() {
        logger.info("Testing sum service...");
        Mockito.when(sessionRepo.findByToken("1")).thenReturn(Optional.of(
                new SessionEntity(1L, "1", "")
        ));
        Mockito.when(sessionRepo.findByToken("2")).thenReturn(Optional.of(
                new SessionEntity(2L, "2", "1;2;3")
        ));
        Mockito.when(sessionRepo.findByToken("3")).thenReturn(Optional.of(
                new SessionEntity(3L, "3", "1 ")
        ));
        Mockito.when(sessionRepo.findByToken("4")).thenReturn(Optional.of(
                new SessionEntity(4L, "4", "-1;-5")
        ));
        assertEquals(6, service.calculate("suma", "2"));
        assertEquals(1, service.calculate("suma", "3"));
        assertEquals(-6, service.calculate("suma", "4"));
        assertThrows(BusinessException.class, () -> service.calculate("suma", ""));
        assertThrows(BusinessException.class, () -> service.calculate("suma", "987898889"));
    }

    @Test
    void subtractTest() {
        logger.info("Testing subtract service...");
        Mockito.when(sessionRepo.findByToken("1")).thenReturn(Optional.of(
                new SessionEntity(1L, "1", "")
        ));
        Mockito.when(sessionRepo.findByToken("2")).thenReturn(Optional.of(
                new SessionEntity(2L, "2", "3;2")
        ));
        Mockito.when(sessionRepo.findByToken("3")).thenReturn(Optional.of(
                new SessionEntity(3L, "3", "1")
        ));
        Mockito.when(sessionRepo.findByToken("4")).thenReturn(Optional.of(
                new SessionEntity(4L, "4", "0;5")
        ));
        assertEquals(1, service.calculate("resta", "2"));
        assertEquals(1, service.calculate("resta", "3"));
        assertEquals(-5, service.calculate("resta", "4"));
        assertThrows(BusinessException.class, () -> service.calculate("resta", ""));
        assertThrows(BusinessException.class, () -> service.calculate("resta", "987898889"));
    }

    @Test
    void multiplyTest() {
        logger.info("Testing multiply service...");
        Mockito.when(sessionRepo.findByToken("1")).thenReturn(Optional.of(
                new SessionEntity(1L, "1", "")
        ));
        Mockito.when(sessionRepo.findByToken("2")).thenReturn(Optional.of(
                new SessionEntity(2L, "2", "3;2")
        ));
        Mockito.when(sessionRepo.findByToken("3")).thenReturn(Optional.of(
                new SessionEntity(3L, "3", "1;5")
        ));
        Mockito.when(sessionRepo.findByToken("4")).thenReturn(Optional.of(
                new SessionEntity(4L, "4", "0;5")
        ));
        assertEquals(6, service.calculate("multiplicacion", "2"));
        assertEquals(5, service.calculate("multiplicacion", "3"));
        assertEquals(0, service.calculate("multiplicacion", "4"));
        assertThrows(BusinessException.class, () -> service.calculate("multiplicacion", ""));
        assertThrows(BusinessException.class, () -> service.calculate("multiplicacion", "987898889"));
    }

    @Test
    void divideTest() {
        logger.info("Testing divide service...");
        Mockito.when(sessionRepo.findByToken("1")).thenReturn(Optional.of(
                new SessionEntity(1L, "1", "")
        ));
        Mockito.when(sessionRepo.findByToken("2")).thenReturn(Optional.of(
                new SessionEntity(2L, "2", "3;2")
        ));
        Mockito.when(sessionRepo.findByToken("3")).thenReturn(Optional.of(
                new SessionEntity(3L, "3", "5;1")
        ));
        Mockito.when(sessionRepo.findByToken("4")).thenReturn(Optional.of(
                new SessionEntity(4L, "4", "0;5")
        ));
        Mockito.when(sessionRepo.findByToken("5")).thenReturn(Optional.of(
                new SessionEntity(5L, "5", "5;0")
        ));
        assertEquals(1.5, service.calculate("division", "2"));
        assertEquals(5, service.calculate("division", "3"));
        assertEquals(0, service.calculate("division", "4"));
        assertThrows(BusinessException.class, () -> service.calculate("division", ""));
        assertThrows(BusinessException.class, () -> service.calculate("division", "5"));
        assertThrows(BusinessException.class, () -> service.calculate("division", "987898889"));
    }

    @Test
    void powTest() {
        logger.info("Testing pow service...");
        Mockito.when(sessionRepo.findByToken("1")).thenReturn(Optional.of(
                new SessionEntity(1L, "1", "")
        ));
        Mockito.when(sessionRepo.findByToken("2")).thenReturn(Optional.of(
                new SessionEntity(2L, "2", "3;2")
        ));
        Mockito.when(sessionRepo.findByToken("3")).thenReturn(Optional.of(
                new SessionEntity(3L, "3", "5;0")
        ));
        Mockito.when(sessionRepo.findByToken("4")).thenReturn(Optional.of(
                new SessionEntity(4L, "4", "0;5")
        ));
        assertEquals(9, service.calculate("potenciacion", "2"));
        assertEquals(1, service.calculate("potenciacion", "3"));
        assertEquals(0, service.calculate("potenciacion", "4"));
        assertThrows(BusinessException.class, () -> service.calculate("potenciacion", ""));
        assertThrows(BusinessException.class, () -> service.calculate("potenciacion", "987898889"));
    }
}
