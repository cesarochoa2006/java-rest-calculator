package com.cesarochoa.calculator.calculate.operators;

import org.springframework.stereotype.Component;

/**
 * Defines how operators should work. Operations are
 * loaded via reflections api
 */
@Component
public interface Operator {

    String getName();

    double compute(String [] values);


}
