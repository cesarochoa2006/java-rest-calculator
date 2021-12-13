package com.cesarochoa.calculator.services;

import com.cesarochoa.calculator.TestApplication;
import com.cesarochoa.calculator.pojo.ApiPojo;
import com.cesarochoa.calculator.pojo.DataPojo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {TestApplication.class})
class PojoClazzTest {
    private static final String ONE = "1";

    @Test
    void testApiPojo() {
        var pojo = Mockito.spy(new ApiPojo());
        pojo.setResultado(ONE);
        pojo.setToken(ONE);
        assertEquals(ONE, pojo.getResultado());
        assertEquals(ONE, pojo.getToken());
    }

    @Test
    void testDataPojo() {
        var pojo = Mockito.spy(new DataPojo());
        pojo.setDatos(ONE);
        pojo.setToken(ONE);
        assertEquals(ONE, pojo.getDatos());
        assertEquals(ONE, pojo.getToken());
    }
}
