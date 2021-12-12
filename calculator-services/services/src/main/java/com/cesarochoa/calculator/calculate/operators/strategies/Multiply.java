package com.cesarochoa.calculator.calculate.operators.strategies;

import com.cesarochoa.calculator.calculate.operators.Operator;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Multiply implements Operator {
    @Override
    public String getName() {
        return "multiplicacion";
    }

    @Override
    public double compute(String[] values) {
        return Arrays.stream(values).parallel().mapToDouble(Double::valueOf)
                .reduce((x, y) -> x * y).orElse(0);
    }
}
