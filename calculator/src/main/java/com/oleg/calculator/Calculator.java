package com.oleg.calculator;

public class Calculator {

    private double currentNumber;
    private boolean numberSet = false;

    /** 
     * Default constructor, starts calculation at 0
     * @return new Calculator
     */
    public Calculator() {
        currentNumber = 0;
    }

    
    /** 
     * Starts a new calculation from a specified number
     * @param firstNumber - initial value
     */
    public void startCalculation(double firstNumber) {
        currentNumber = firstNumber;
        numberSet = true;
    }

    
    /** 
     * Takes a command and performs an operation based on it.
     * The operation can be addition, subtraction, multiplication, division, power, or resetting the current number to a new one
     * @param operator - the type of the next operation
     * @param secondNumber - number upon which, in addition to the stored current number, the operation is performed
     */
    public void processCommand(Operator operator, double secondNumber) {
        switch(operator) {
            case PLUS:
                add(secondNumber);
                break;
            case MINUS:
                subtract(secondNumber);
                break;
            case MULTIPLY:
                multiply(secondNumber);
                break;
            case DIVIDE:
                divide(secondNumber);
                break;
            case POWER:
                power(secondNumber);
                break;
            case EQUAL:
                currentNumber = secondNumber;
                break;
        }
    }

    
    /** 
     * Returns the result of calculations up to this point
     * @return double
     */
    public double getCurrentNumber() {
        return currentNumber;
    }

    
    /** 
     * Tells whether a calculation has started or not
     * @return boolean
     */
    public boolean isNumberSet() {
        return numberSet;
    }

    private void add(double number) {
        currentNumber += number;
    }

    private void subtract(double number) {
        currentNumber -= number;
    }

    private void multiply(double number) {
        currentNumber *= number;
    }

    private void divide(double number) {
        currentNumber /= number;
    }

    private void power(double number) {
        currentNumber = Math.pow(currentNumber, number);
    }
    
}
