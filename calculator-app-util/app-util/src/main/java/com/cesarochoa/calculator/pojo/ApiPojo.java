package com.cesarochoa.calculator.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiPojo {
    public static final String OK = "Ok";
    private String resultado;
    private String token;
}
