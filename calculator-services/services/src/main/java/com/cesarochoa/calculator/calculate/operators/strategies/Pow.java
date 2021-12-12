package com.cesarochoa.calculator.calculate.operators.strategies;

import com.cesarochoa.calculator.calculate.operators.Operator;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Pow implements Operator {
    @Override
    public String getName() {
        return "potenciacion";
    }

    @Override
    public double compute(String[] values) {
        return Arrays.stream(values).mapToDouble(Double::valueOf).reduce(Math::pow).orElse(0);
    }
}
