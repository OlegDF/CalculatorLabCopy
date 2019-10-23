package com.oleg.calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class InputTest extends CommandIntaker {

    public InputTest() {
        super(new Calculator());
    }

    @Test
    public void testInputType() {
        readLineFromProgram("2 + 2 =");
        assertTrue("Last input was a sign, intaker detected number instead!", !lastInputIsANumber);
        readLineFromProgram("2 * 2");
        assertTrue("Last input was a number, intaker detected sign instead!", lastInputIsANumber);
    }

    @Test
    public void testInputRecognition() {
        readLineFromProgram("2 +");
        assertEquals("Program was supposed to detect +, but did not!", lastOperator, Operator.PLUS);
        readLineFromProgram("2 -");
        assertEquals("Program was supposed to detect -, but did not!", lastOperator, Operator.MINUS);
        readLineFromProgram("2 *");
        assertEquals("Program was supposed to detect *, but did not!", lastOperator, Operator.MULTIPLY);
        readLineFromProgram("2 /");
        assertEquals("Program was supposed to detect /, but did not!", lastOperator, Operator.DIVIDE);
        readLineFromProgram("2 ^");
        assertEquals("Program was supposed to detect ^, but did not!", lastOperator, Operator.POWER);
        readLineFromProgram("2 =");
        assertEquals("Program was supposed to detect =, but did not!", lastOperator, Operator.EQUAL);
    }

}