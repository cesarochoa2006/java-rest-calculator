package com.cesarochoa.calculator.commands;

import com.cesarochoa.calculator.errormanagers.BusinessException;
import com.cesarochoa.calculator.pojo.ApiPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

/**
 * Defines how commands should work.
 * Valid concrete commands overwrites the process method, and
 * commands may interact with command token and command context.
 * Calculator command execute process and handle errors with
 * BusinessException class
 */
public abstract class CalculatorCommand {

    private final Logger logger = LoggerFactory.getLogger(CalculatorCommand.class);
    private String token;
    private String context;

    protected abstract Mono<ApiPojo> process();

    public Mono<ApiPojo> execute() {
        logger.info("Executing command with context {} ", context);
        return process().onErrorMap(BusinessException::new);
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public String getContext() {
        return this.context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
