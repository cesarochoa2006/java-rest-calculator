package com.cesarochoa.calculator.calculate.operators;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * Defines how operators should work. Operations are
 * loaded via reflections api. Valid operators overwrite
 * compute and getName methods
 */
@Component
public interface Operator {

    String getName();

    double compute(DoubleStream values);

    default double doOperate(String[] values) {
        if (values.length == 1 && !values[0].equals(";")) return Double.parseDouble(values[0]);
        return compute(Arrays.stream(values).filter(n -> !n.isBlank()).mapToDouble(Double::valueOf));
    }


}
