package org.example.stringcalculator.service;

import org.example.stringcalculator.model.Parser;

import java.util.List;

public class StringCalculator {

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        Parser parser = new Parser(numbers);
        List<Integer> parsedNumbers = parser.parse();

        return parsedNumbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

}
