package com.cesarochoa.calculator.domain.entities;

import com.cesarochoa.calculator.domain.entities.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "SESSION")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SessionEntity extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "token", nullable = false, updatable = false)
    private String token;

    @Column(name = "operands", nullable = false)
    private String operands = "";



}
