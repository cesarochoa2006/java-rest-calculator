package com.cesarochoa.calculator.commands.impl;

import com.cesarochoa.calculator.calculate.CalculatorService;
import com.cesarochoa.calculator.commands.CalculatorCommand;
import com.cesarochoa.calculator.pojo.ApiPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CalculateCommand extends CalculatorCommand {

    private final CalculatorService calculatorService;

    @Autowired
    CalculateCommand(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @Override
    protected Mono<ApiPojo> process() {
        return Mono.fromCallable(() -> {
            var result = new ApiPojo();
            result.setToken(this.getToken());
            result.setResultado(String.valueOf(
                    calculatorService.calculate(this.getContext(), this.getToken())));
            return result;
        });
    }
}
