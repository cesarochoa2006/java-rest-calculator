package com.cesarochoa.calculator.calculate.operators.strategies;

import com.cesarochoa.calculator.calculate.operators.Operator;
import org.springframework.stereotype.Component;

import java.util.stream.DoubleStream;

@Component
public class Sum implements Operator {

    @Override
    public String getName() {
        return "suma";
    }

    @Override
    public double compute(DoubleStream values) {
        return values.parallel().sum();
    }
}
