package com.cesarochoa.calculator.commands.impl;

import com.cesarochoa.calculator.commands.CalculatorCommand;
import com.cesarochoa.calculator.domain.repositories.SessionRepository;
import com.cesarochoa.calculator.errormanagers.BusinessException;
import com.cesarochoa.calculator.errormanagers.EnumError;
import com.cesarochoa.calculator.pojo.ApiPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AddOperandCommand extends CalculatorCommand {

    private final SessionRepository sessionRepository;

    @Autowired
    public AddOperandCommand(SessionRepository sessionRepository) {
        super();
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected Mono<ApiPojo> process() {
        this.contextValidation();
        return Mono.fromCallable(() -> {
            var session = this.sessionRepository.findByToken(this.getToken())
                    .orElseThrow(() -> new BusinessException(EnumError.ABSENT_SESSION));
            var currentOperands = session.getOperands();
            var newOperands = Stream.concat(
                    Stream.of(currentOperands.split(";")), Stream.of(this.getContext().split(";"))
            ).map(String::valueOf).collect(Collectors.joining(";"));
            session.setOperands(newOperands);
            this.sessionRepository.save(session);
            var result = new ApiPojo();
            result.setResultado(ApiPojo.OK);
            result.setToken(session.getToken());
            return result;
        });
    }

    private void contextValidation() {
        if (this.getContext() == null || this.getContext().isBlank())
            throw new BusinessException(EnumError.ABSENT_OPERANDS);
        if (!this.getContext().matches("^([+-]?(\\d+\\.)?\\d+;?)$"))
            throw new BusinessException(EnumError.INVALID_OPERANDS);
    }
}
