package com.cesarochoa.calculator.commands;

import com.cesarochoa.calculator.TestApplication;
import com.cesarochoa.calculator.commands.impl.AddOperandCommand;
import com.cesarochoa.calculator.commands.impl.CalculateCommand;
import com.cesarochoa.calculator.commands.impl.GenerateTokenCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestApplication.class})
class CommandsTest {

    @Autowired
    AddOperandCommand addOperandCommand;

    @Autowired
    CalculateCommand calculateCommand;

    @Autowired
    GenerateTokenCommand generateTokenCommand;

    @Test
    void testInitCommands(){
        assertNotNull(addOperandCommand);
        assertNotNull(calculateCommand);
        assertNotNull(generateTokenCommand);
        assertInstanceOf(CalculatorCommand.class, addOperandCommand);
        assertInstanceOf(CalculatorCommand.class, calculateCommand);
        assertInstanceOf(CalculatorCommand.class, generateTokenCommand);
    }
}
