package org.example.stringcalculator.model;

import java.util.ArrayList;
import java.util.List;

public class Parser {

    private final String input;
    private final List<Integer> resultNumbers = new ArrayList<>();

    public Parser(String input) {
        this.input = input;
    }

    public List<Integer> parse() {
        processNumbers(input.split(","));
        return resultNumbers;
    }

    private void processNumbers(String[] tokens) {
        if (tokens.length > 2) {
            throw new IllegalArgumentException("Invalid input. Calculator can take up to two numbers");
        }
        for (String token : tokens) {
            if (!token.isEmpty()) {
                int number = Integer.parseInt(token.trim());
                resultNumbers.add(number);
            }
        }
    }

}

