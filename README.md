# String Calculator

This repository contains a Java implementation of the well known StringCalculator exercise in coding and refactoring using
Test Driven Development (TDD) methodology. However, the exercise has been modified with some changes in the requirements.
Credits to Roy Osherove for the original idea.

## Functionality

### Step 1: the simplest thing
The `int add(String input)` method can take up to two numbers, separated by commas, and will return their sum as a result.
- The input can be: “”, “1”, “1,2”. For an empty string, it will return 0.

### Step 2: handle an unknown amount of numbers
Allow the `add` method to handle an unknown number of arguments.

### Step 3: handle new lines between numbers
Allow the `add` method to handle newlines as separators, instead of commas.
- “1,2\n3” should return “6” “2,\n3” is invalid, but no need to clarify it with the program.

### Step 4: separator at the end
Add validation that does not allow a separator at the end.
- “1,2,” should return an error (or throw an exception).

### Step 5: support different delimiters
Allow the `add` method to handle different delimiters.
To change the delimiter, the beginning of the input will contain a separate line that looks like this: `//[delimiter]\n[numbers]`.
- “//;\n1;3” should return “4”.
- “//|\n1|2|3” should return “6”.
- “//sep\n2sep5” should return “7”.
- “//|\n1|2,3” is invalid and should return an error (or throw an exception) with the message “‘|’ expected but ‘,’ found at position 3.”.

### Step 6: negative numbers
Calling `add` with negative numbers will return the message “Negative number(s) not allowed: ”.
- “1,-2” is invalid and should return the message “Negative number(s) not allowed: -2”.
- “2,-4,-9” is invalid and should return the message “Negative number(s) not allowed: -4, -9”.

### Step 7: handle error messages
Calling `add` with multiple errors will return all error messages separated by newlines.
- “//|\n1|2,-3” is invalid and return the message “Negative number(s) not allowed: -3\n’|’ expected but ‘,’ found at position 3.”

### Step 8: ignore big numbers
Numbers bigger than 1000 should be ignored, so adding 2 + 1001 = 2.

## Usage

To use the String Calculator, simply include the provided Java code in your project and call the `add` method with the desired input string.

```java
int result = StringCalculator.add("//;\n1;3");
System.out.println(result);
```
