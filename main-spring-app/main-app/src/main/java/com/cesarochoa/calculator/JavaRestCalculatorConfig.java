package com.cesarochoa.calculator;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux()
@EntityScan(basePackages = {
        "com.cesarochoa.calculator.domain.entities"
})
@EnableJpaRepositories(basePackages = {"com.cesarochoa.calculator.domain.repositories"})
@ComponentScan(basePackages = {
        "com.cesarochoa.calculator.calculate.**"})
public class JavaRestCalculatorConfig {
}
