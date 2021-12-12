package com.cesarochoa.calculator.commands.impl;

import com.cesarochoa.calculator.commands.CalculatorCommand;
import com.cesarochoa.calculator.domain.entities.SessionEntity;
import com.cesarochoa.calculator.domain.repositories.SessionRepository;
import com.cesarochoa.calculator.pojo.ApiPojo;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;

@Component
public class GenerateTokenCommand extends CalculatorCommand {

    private final SessionRepository sessionRepository;

    @Autowired
    public GenerateTokenCommand(SessionRepository sessionRepository) {
        super();
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected Mono<ApiPojo> process() {
        return Mono.fromCallable(() -> {
            var generatedToken = Hashing.sha256()
                    .hashString(OffsetDateTime.now().toString(), StandardCharsets.UTF_8)
                    .toString();
            var session = new SessionEntity();
            session.setToken(generatedToken);
            this.sessionRepository.save(session);
            var result = new ApiPojo();
            result.setToken(generatedToken);
            result.setResultado(ApiPojo.OK);
            return result;
        });
    }
}
