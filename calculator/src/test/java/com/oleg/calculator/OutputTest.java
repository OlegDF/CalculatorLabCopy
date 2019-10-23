package com.oleg.calculator;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OutputTest extends CommandIntaker {

    public OutputTest() {
        super(new Calculator());
    }

    @Test
    public void testOutputCorrectness() {
        readLineFromProgram("2.5 * 3 + 8 - 2 * 5");
        assertEquals("Computation 2.5 * 3 + 8 - 2 * 5 incorrect: " + getCurrentNumber(), getCurrentNumber(), 67.5f, 0.00001f);
        readLineFromProgram("= 1.1 * 0.5 ^ 3");
        assertEquals("Computation 1.1 * 0.5 ^ 3 incorrect: " + getCurrentNumber(), getCurrentNumber(), 0.166375f, 0.00001f);
        readLineFromProgram("= 9.8 / 2 + 5 / 0");
        assertEquals("Computation 9.8 / 2 + 5 / 0 incorrect: " + getCurrentNumber(), getCurrentNumber(), 9.9f, 0.00001f);
    }

}