

import java.text.SimpleDateFormat;
import java.util.*;

class JavaTest {
    // E.g. Input: Automation, Output: noitamotuA
    public static String reverseStringWithoutUsingStringMethod(String s) {
        StringBuilder reversedStringBuilder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            reversedStringBuilder.append(s.substring(i, i + 1));
        }
        return reversedStringBuilder.toString();
    }

    // Sort the integer in ASC order without using the built-in method such as ArrayUtils.sort
    public static Integer[] sortIntegers(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    // Check if the given date is within the date range
    public static boolean isInDateRange(Date givenDate, Date startDate, Date endDate) {
        return !givenDate.before(startDate) && !givenDate.after(endDate);
    }

    // sort the given String in ASC order without using method like Arrays.sort
    public static char[] sortStringInAscOrder(String input) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            for (int j = 0; j < chars.length - i - 1; j++) {
                if (chars[j] > chars[j + 1]) {
                    char temp = chars[j];
                    chars[j] = chars[j + 1];
                    chars[j + 1] = temp;
                }
            }
        }
        return chars;
    }

    // Given a Alphanumeric, please return a character with the lowest occurrence
    // E.g. AbcdAbc123123, the answer is d as it only occurs once
    // If there is more than 1 char with the same lowest occurrence, return the first char in ASC order
    public static char lowestOccurrence(String input) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) + 1);
            } else {
                freqMap.put(c, 1);
            }

        }

        int minFreq = Integer.MAX_VALUE;
        List<Character> minChars = new ArrayList<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() < minFreq) {
                minFreq = entry.getValue();
                minChars.clear();
                minChars.add(entry.getKey());
            } else if (entry.getValue() == minFreq) {
                minChars.add(entry.getKey());
            }
        }
        Collections.sort(minChars);
        return minChars.get(0);
    }

    // Given an input, please apply the provided equations (+, -, x, /)
    // E.g. input: 1.5, equations: x*2, x+10/2, x*1.5-6
    // Answer: 1st equation: x*2 = 1.5*2 = 3
    //         2nd equation: x+10/2 = 3+10/2 = 8
    //         3rd equation: x*1.5-6 = 8*1.5-6 = 6
    //         return 6.0
    public static double solveEquations(double input, String[] equations) {
        double x = input;
        for (String equation : equations) {
            String expr = equation.replace("x", Double.toString(x));
            x = evaluate(expr);
        }
        return x;
    }

    private static double evaluate(String expr) {
        List<String> tokens = new ArrayList<>();
        StringBuilder num = new StringBuilder();
        boolean expectNumber = true;  // Flag to allow leading negative numbers

        for (char c : expr.toCharArray()) {
            if ("+-*/".indexOf(c) >= 0) {
                if (expectNumber && c == '-') {
                    // It's a negative number
                    num.append(c);
                    expectNumber = false;
                } else {
                    if (num.length() > 0) {
                        tokens.add(num.toString());
                    }
                    tokens.add(Character.toString(c));
                    num = new StringBuilder();
                    expectNumber = true;
                }
            } else {
                num.append(c);
                expectNumber = false;
            }
        }

        if (num.length() > 0) {
            tokens.add(num.toString());
        }

        double result = Double.parseDouble(tokens.get(0));
        for (int i = 1; i < tokens.size(); i += 2) {
            String op = tokens.get(i);
            double next = Double.parseDouble(tokens.get(i + 1));
            switch (op) {
                case "+" -> result += next;
                case "-" -> result -= next;
                case "*" -> result *= next;
                case "/" -> result /= next;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println("Test 1: " + reverseStringWithoutUsingStringMethod("Automation"));
        Integer[] intArray = new Integer[]{10, 12, 54, 1, 2, -9, 8};
        System.out.print("Test 2: ");
        Integer[] sortedIntArr = sortIntegers(intArray);
        for (Integer i : sortedIntArr) {
            System.out.print(i + ", ");
        }

        System.out.println();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date startDate = sdf.parse("2024-12-01 13:09:22");
            Date endDate = sdf.parse("2025-03-09 20:10:12");
            Date givenDate = sdf.parse("2025-02-02 00:11:22");
            System.out.println("Test 3: " + isInDateRange(givenDate, startDate, endDate));
        } catch (Exception e) {
            System.out.println(e);
        }

        char[] sorted = sortStringInAscOrder("testingNG311");
        System.out.print("Test 4 :");
        for (char c : sorted) {
            System.out.print(c + ", ");
        }
        System.out.println();
        System.out.println("Test 5: " + lowestOccurrence("Abc1dd23affbc1ee23u3278"));
        System.out.print("Test 6: ");
        double calculated = solveEquations(3.4, new String[]{"x*x", "x-10/2.2", "x+4-10", "x+5*8"});
        System.out.print("calculated = " + calculated);
    }
}

