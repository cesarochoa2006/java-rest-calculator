package com.cesarochoa.calculator.calculate;

import com.cesarochoa.calculator.calculate.operators.Operator;
import com.cesarochoa.calculator.domain.repositories.SessionRepository;
import com.cesarochoa.calculator.errormanagers.BusinessException;
import com.cesarochoa.calculator.errormanagers.EnumError;
import org.reflections.Reflections;
import org.reflections.scanners.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalculatorService {

    private final Logger logger = LoggerFactory.getLogger(CalculatorService.class);
    private static final String OPERATORS_PATH = "com.cesarochoa.calculator.calculate.operators.strategies";
    private final List<? extends Operator> operators;
    private final ApplicationContext context;
    private final SessionRepository sessionRepository;

    @Autowired
    public CalculatorService(ApplicationContext context, SessionRepository sessionRepo) {
        this.context = context;
        this.sessionRepository = sessionRepo;
        this.operators = this.loadCatalog();
    }

    private List<? extends Operator> loadCatalog() {
        logger.info("Loading operators ...");
        return Stream.of(new Reflections(OPERATORS_PATH, new Scanner[0]).getSubTypesOf(Operator.class))
                .flatMap(Collection::parallelStream).map(clazz -> {
                    try {
                        logger.info("Operator found {}", clazz);
                        return this.context.getBean(clazz);
                    } catch (Exception e) {
                        logger.error("Error during load of class {}", clazz, e);
                        return null;
                    }
                }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public double calculate(String operation, String token){
        var session = sessionRepository.findByToken(token).orElseThrow(
                () -> new BusinessException(EnumError.ABSENT_SESSION)
        );
        if(session.getOperands() == null || session.getOperands().isBlank())
            throw new BusinessException(EnumError.ABSENT_OPERANDS);
        return operators.parallelStream()
                .filter(operator -> operator.getName().equals(operation.toLowerCase(Locale.ROOT)))
                .findFirst().orElseThrow(() -> new BusinessException(EnumError.INVALID_OPERATION))
                .compute(session.getOperands().split(";"));
    }


}
