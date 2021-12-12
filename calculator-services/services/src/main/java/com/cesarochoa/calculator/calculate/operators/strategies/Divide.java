package com.cesarochoa.calculator.calculate.operators.strategies;

import com.cesarochoa.calculator.calculate.operators.Operator;
import com.cesarochoa.calculator.errormanagers.BusinessException;
import com.cesarochoa.calculator.errormanagers.EnumError;
import org.springframework.stereotype.Component;

import java.util.stream.DoubleStream;

@Component
public class Divide implements Operator {

    @Override
    public String getName() {
        return "division";
    }

    @Override
    public double compute(DoubleStream values) {
        return values.reduce((x, y) -> {
            if (y == 0) throw new BusinessException(EnumError.NON_ZERO_ERROR);
            return x / y;
        }).orElse(0);
    }
}
