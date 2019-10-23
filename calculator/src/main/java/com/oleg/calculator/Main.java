package com.oleg.calculator;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        CommandIntaker intaker = new CommandIntaker(calc);
        intaker.readLine();
    }

}
