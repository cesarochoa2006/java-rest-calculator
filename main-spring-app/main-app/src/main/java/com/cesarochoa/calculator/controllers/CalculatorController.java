package com.cesarochoa.calculator.controllers;

import com.cesarochoa.calculator.commands.impl.AddOperandCommand;
import com.cesarochoa.calculator.commands.impl.CalculateCommand;
import com.cesarochoa.calculator.commands.impl.GenerateTokenCommand;
import com.cesarochoa.calculator.pojo.ApiPojo;
import com.cesarochoa.calculator.pojo.DataPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/calculadora")
public class CalculatorController {

    ApplicationContext appContext;

    @Autowired
    public CalculatorController(ApplicationContext context){
        this.appContext = context;
    }

    @GetMapping(value = "/token", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiPojo> getToken(){
        var command = appContext.getBean(GenerateTokenCommand.class);
        return command.execute();
    }

    @PostMapping(value = "/agregar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiPojo> addOperand(@RequestBody DataPojo body){
        var command = appContext.getBean(AddOperandCommand.class);
        command.setContext(body.getDatos());
        command.setToken(body.getToken());
        return command.execute();
    }

    @PostMapping(value = "/calcular", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ApiPojo> doCalculate(@RequestBody DataPojo body){
        var command = appContext.getBean(CalculateCommand.class);
        command.setContext(body.getOperacion());
        command.setToken(body.getToken());
        return command.execute();
    }

}
