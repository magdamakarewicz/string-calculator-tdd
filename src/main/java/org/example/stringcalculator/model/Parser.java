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
        processNumbers(input.split(",|\n"));
        return resultNumbers;
    }

    private void processNumbers(String[] tokens) {
        for (String token : tokens) {
            if (!token.isEmpty()) {
                int number = Integer.parseInt(token.trim());
                resultNumbers.add(number);
            }
        }
    }

}

