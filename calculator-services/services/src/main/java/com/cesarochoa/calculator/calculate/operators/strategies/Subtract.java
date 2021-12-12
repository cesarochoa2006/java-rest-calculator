package com.cesarochoa.calculator.calculate.operators.strategies;

import com.cesarochoa.calculator.calculate.operators.Operator;
import org.springframework.stereotype.Component;

import java.util.stream.DoubleStream;

@Component
public class Subtract implements Operator {
    @Override
    public String getName() {
        return "resta";
    }

    @Override
    public double compute(DoubleStream values) {
        return values.reduce((x, y) -> x - y).orElse(0);
    }
}
