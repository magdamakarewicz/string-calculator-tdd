package org.example.stringcalculator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {

    private final String input;
    private String delimiter = "[,\n]";
    private final List<Integer> resultNumbers = new ArrayList<>();

    public Parser(String input) {
        this.input = input;
    }

    public List<Integer> parse() {
        checkLastCharacter();

        if (input.startsWith("//")) {
            parseWithCustomDelimiter();
        } else {
            parseDefault();
        }

        return resultNumbers;
    }

    private void checkLastCharacter() {
        char lastChar = input.charAt(input.length() - 1);
        if (lastChar == ',' || lastChar == '\n') {
            throw new IllegalArgumentException("Separator at the end is not allowed.");
        }
    }

    private void parseDefault() {
        processNumbers(input.split(delimiter));
    }

    private void processNumbers(String[] tokens) {
        for (String token : tokens) {
            if (!token.isEmpty()) {
                int number = Integer.parseInt(token.trim());
                resultNumbers.add(number);
            }
        }
    }

    private void parseWithCustomDelimiter() {
        int delimiterIndex = input.indexOf("\n");
        delimiter = input.substring(2, delimiterIndex);
        String[] tokens = input.substring(delimiterIndex + 1).split(Pattern.quote(delimiter));
        processNumbersWithCustomDelimiter(tokens);
    }

    private void processNumbersWithCustomDelimiter(String[] tokens) {
        for (String token : tokens) {
            if (!token.isEmpty()) {
                char nonNumericChar = findNonNumericChar(token);
                if (nonNumericChar != 0) {
                    processTokenWithIncorrectDelimiter(nonNumericChar);
                } else {
                    int number = Integer.parseInt(token.trim());
                    resultNumbers.add(number);
                }
            }
        }
    }

    private char findNonNumericChar(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return c;
            }
        }
        return 0;
    }

    private void processTokenWithIncorrectDelimiter(char nonNumericChar) {
        int position = input.indexOf(nonNumericChar) - input.indexOf("\n") - 1;
        throw new IllegalArgumentException("'" + delimiter + "' expected but '" + nonNumericChar +
                "' found at position " + position + ".");
    }

}