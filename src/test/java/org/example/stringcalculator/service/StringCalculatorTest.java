package org.example.stringcalculator.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorTest {

    @Test
    void shouldReturn0WhenAddEmptyString() {
        //given
        String inputForTest = "";
        int expectedResult = 0;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturn5WhenAddSingleNumberEqualTo5() {
        //given
        String inputForTest = "5";
        int expectedResult = 5;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturn8WhenAddTwoNumbersEqualTo3And5SeparatedByComa() {
        //given
        String inputForTest = "3,5";
        int expectedResult = 8;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturn10WhenAddMultipleNumbersFrom1To4SeparatedByComa() {
        //given
        String inputForTest = "1,2,3,4";
        int expectedResult = 10;

        //when
        int result = StringCalculator.add(inputForTest);

        //then
        assertEquals(expectedResult, result);
    }

}