package com.cesarochoa.calculator.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataPojo {
    private String token;
    private String datos;
    private String operacion;
}
