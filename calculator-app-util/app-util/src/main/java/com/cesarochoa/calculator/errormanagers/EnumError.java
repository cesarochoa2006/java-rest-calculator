package com.cesarochoa.calculator.errormanagers;

public enum EnumError {


    GENERIC_ERROR("Ocurrió un error desconocido, por favor verifica los datos enviados e intenta nuevamente, si " +
                          "el problema persiste contacta con el administrador del sistema"),

    NON_ZERO_ERROR("Error, el dividendo no puede ser cero," +
            " por favor verifica la operación e intenta nuevamente"),
    ABSENT_SESSION("No se encuentra una sesión asociada al token enviado," +
            " por favor verificalo e intenta nuevamente"),
    INVALID_OPERATION("La operación enviada no es válida o no está soportada," +
            " por favor verifica e intenta nuevamente o contacta con el administrador del sistema"),
    ABSENT_OPERANDS("Debes agregar al menos un operando primero"),
    INVALID_OPERANDS("Los operandos enviados no son números o tienen un formato incorrecto, " +
            "por favor verifica e intenta nuevamente")

        ;



    private final String value;

    EnumError(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
