package com.cesarochoa.calculator.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/")
public class TimeController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<OffsetDateTime> getTime(){
        return Mono.just(OffsetDateTime.now());
    }
}
