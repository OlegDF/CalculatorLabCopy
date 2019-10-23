package com.oleg.calculator;

public class CommandIntaker {

    protected Operator lastOperator;
    protected boolean lastInputIsANumber = false;
    private Calculator calc;

    public CommandIntaker(Calculator calc) {
        this.calc = calc;
    }

    /**
     * Reads a line from console, then continues to read them and send detected numbers and operators to the calculator until receiving a stop signal.
     */
    public void readLine() {
        String newLine = System.console().readLine();
        while(processLine(newLine)) {
            newLine = System.console().readLine();
        }
    }

    protected void readLineFromProgram(String line) {
        processLine(line);
    }

    /** 
     * Goes through a string word by word and identifies operators or numbers, sending them to the calculator
     * @param newLine
     * @return boolean
     */
    private boolean processLine(String newLine) {
        String[] words = newLine.split(" ");
        for(String word: words) {
            if(word.equals("stop")) {
                return false;
            } else if(word.equals("mem1")) {
                calc.mem1();
                lastInputIsANumber = true;
                continue;
            } else if(word.equals("mem2")) {
                calc.mem2();
                lastInputIsANumber = true;
                continue;
            } else if(word.equals("memclear")) {
                calc.memClear();
                continue;
            }
            Double parsedNumber = null;
            Operator newOperator = null;
            try {
                Double constant = detectConstant(word);
                if(constant != null) {
                    parsedNumber = constant;
                } else {
                    parsedNumber = Double.parseDouble(word);
                }
            } catch (NumberFormatException e) {
                newOperator = getOperator(word);
            }
            if(parsedNumber == null && newOperator == null) {
                System.out.println("Unrecognized input: " + word);
            } else {
                processCommand(word, parsedNumber, newOperator);
            }
        }
        return true;
    }

    private Double detectConstant(String word) {
        if(word.equals("pi")) {
            return Math.PI;
        } else if(word.equals("e")) {
            return Math.E;
        }
        return null;
    }

    private Operator getOperator(String word) {
        Operator newOperator = null;
        switch(word) {
            case "+":
                newOperator = Operator.PLUS;
                break;
            case "-":
                newOperator = Operator.MINUS;
                break;
            case "*":
                newOperator = Operator.MULTIPLY;
                break;
            case "/":
                newOperator = Operator.DIVIDE;
                break;
            case "^":
                newOperator = Operator.POWER;
                break;
            case "=":
                newOperator = Operator.EQUAL;
                break;
            case "%":
                newOperator = Operator.REMAINDER;
                break;
            case "log":
                newOperator = Operator.LOGARITHM;
                break;
        }
        return newOperator;
    }

    private void processCommand(String word, Double parsedNumber, Operator newOperator) {
        if(lastInputIsANumber) {
            if(newOperator == null) {
                System.out.println("Operator expected instead of: " + word);
            } else {
                if(newOperator == Operator.EQUAL) {
                    System.out.println("Result: " + calc.getCurrentNumber());
                }
                lastOperator = newOperator;
                lastInputIsANumber = false;
            }
        } else {
            if(parsedNumber == null) {
                System.out.println("Number expected instead of: " + word);
            } else {
                if(!calc.isNumberSet()) {
                    calc.startCalculation(parsedNumber);
                } else {
                    if(parsedNumber == 0 && lastOperator.equals(Operator.DIVIDE)) {
                        System.out.println("Division by 0 ignored.");
                    } else if(parsedNumber <= 0 && lastOperator.equals(Operator.LOGARITHM)) {
                        System.out.println("Logarithm with negative or 0 base ignored.");
                    } {
                        calc.processCommand(lastOperator, parsedNumber);
                    }
                }
                lastInputIsANumber = true;
            }
        }
    }

    protected double getCurrentNumber() {
        return calc.getCurrentNumber();
    }

}