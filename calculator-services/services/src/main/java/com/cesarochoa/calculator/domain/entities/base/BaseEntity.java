package com.cesarochoa.calculator.domain.entities.base;

import javax.persistence.Transient;

public abstract class BaseEntity<K> {

    @Transient
    public abstract K getId();
}