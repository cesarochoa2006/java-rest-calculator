package com.cesarochoa.calculator.errormanagers;


import reactor.core.Exceptions;


public class BusinessException extends RuntimeException{


    public BusinessException(EnumError error){
        super(error.getValue());
    }

    public BusinessException(Throwable t) {
        if (t instanceof BusinessException) {
            throw Exceptions.propagate(t);
        }
        throw new BusinessException(EnumError.GENERIC_ERROR);
    }
}
