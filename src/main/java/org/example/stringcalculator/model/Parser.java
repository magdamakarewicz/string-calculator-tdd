package org.example.stringcalculator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {

    private final String input;
    private String delimiter = "[,\n]";
    private final List<Integer> negatives = new ArrayList<>();
    private final List<Integer> resultNumbers = new ArrayList<>();
    private final List<String> errors = new ArrayList<>();

    public Parser(String input) {
        this.input = input;
    }

    public List<Integer> parse() {
        validateLastCharacter();
        if (input.startsWith("//")) {
            parseWithCustomDelimiter();
        } else {
            parseDefault();
        }
        checkErrors(errors);
        return resultNumbers;
    }

    private void validateLastCharacter() {
        char lastChar = input.charAt(input.length() - 1);
        if (lastChar == ',' || lastChar == '\n') {
            throw new IllegalArgumentException("Separator at the end is not allowed.");
        }
    }

    private void parseDefault() {
        processNumbers(input.split(delimiter));
    }

    private void parseWithCustomDelimiter() {
        int delimiterIndex = input.indexOf("\n");
        delimiter = input.substring(2, delimiterIndex);
        String[] tokens = input.substring(delimiterIndex + 1).split(Pattern.quote(delimiter));
        processNumbersWithCustomDelimiter(tokens);
    }

    private void processNumbers(String[] tokens) {
        for (String token : tokens) {
            if (!token.isEmpty()) {
                processSingleNumber(token.trim());
            }
        }
        if (!negatives.isEmpty()) {
            handleNegativeNumbers();
        }
    }

    private void processNumbersWithCustomDelimiter(String[] tokens) {
        for (String token : tokens) {
            if (!token.isEmpty()) {
                char nonNumericChar = findNonNumericChar(token);
                if (nonNumericChar != 0) {
                    processIncorrectDelimiter(token, nonNumericChar);
                } else {
                    processSingleNumber(token.trim());
                }
            }
        }
        if (!negatives.isEmpty()) {
            handleNegativeNumbers();
        }
    }

    private char findNonNumericChar(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c) && c != '-') {
                return c;
            }
        }
        return 0;
    }

    private void processSingleNumber(String token) {
        int number = Integer.parseInt(token);
        if (number < 0) {
            negatives.add(number);
        } else if (number <= 1000) {
            resultNumbers.add(number);
        }
    }

    private void processIncorrectDelimiter(String token, char nonNumericChar) {
        String[] splitTokens = token.split(String.valueOf(nonNumericChar));
        for (String num : splitTokens) {
            processSingleNumber(num);
        }
        int position = input.indexOf(nonNumericChar) - input.indexOf("\n") - 1;
        errors.add("'" + delimiter + "' expected but '" + nonNumericChar +
                "' found at position " + position + ".");
    }

    private void handleNegativeNumbers() {
        StringBuilder message = new StringBuilder("Negative number(s) not allowed: ");
        for (int i = 0; i < negatives.size(); i++) {
            if (i > 0) {
                message.append(", ");
            }
            message.append(negatives.get(i));
        }
        errors.add(0, message.toString());
    }

    private void checkErrors(List<String> errors) {
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("\n", errors));
        }
    }

}