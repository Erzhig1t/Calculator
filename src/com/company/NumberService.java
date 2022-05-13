package com.company;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static java.lang.System.out;

public class NumberService {
    private final static TreeMap < Integer, String > romanString = new TreeMap<>();

    static {
        romanString.put(1, "I");
        romanString.put(4, "IV");
        romanString.put(5, "V");
        romanString.put(9, "IX");
        romanString.put(10, "X");
        romanString.put(40, "XL");
        romanString.put(50, "L");
        romanString.put(90, "XC");
        romanString.put(100, "C");
        romanString.put(150, "CL");
        romanString.put(200, "CC");
        romanString.put(250, "CCL");
        romanString.put(300, "CCC");
        romanString.put(350, "CCCL");
        romanString.put(400, "CD");
        romanString.put(450, "CDL");
        romanString.put(500, "D");
        romanString.put(550, "DL");
        romanString.put(600, "DC");
        romanString.put(650, "DCL");
        romanString.put(700, "DCC");
        romanString.put(750, "DCCL");
        romanString.put(800, "DCCC");
        romanString.put(850, "DCCCL");
        romanString.put(900, "CM");
        romanString.put(950, "CML");
        romanString.put(1000, "M");
    }

    static Number parseAndValidate(String symbol) throws Exception {

        int value;
        NumberType type;

        try {
            value = Integer.parseInt(symbol);
            type = NumberType.ARABIC;
        }catch (NumberFormatException e) {
            value = toRegularNumber(symbol);
            type = NumberType.ROMAN;
        }

        if (value < 1 || value > 1000) {
            throw new Exception("Wrong number value(s), use numbers from 1 to 10 inclusive");
        }

        return new Number(value, type);
    }

    static Number parseAndValidate(String symbol, NumberType type) throws Exception {

        Number number = parseAndValidate(symbol);
        if (number.getType() != type) {
            throw new Exception("Numbers of different types, use one type of input values");
        }

        return number;
    }

    private static int letterToNumber(char letter) {

        int result = -1;

        for (Map.Entry < Integer, String > entry: romanString.entrySet()) {
            if (entry.getValue().equals(String.valueOf(letter))) result = entry.getKey();
        }
        return result;
    }

    static String toRomanNumber(int number) {

        int i = romanString.floorKey(number);

        if (number == i) {
            return romanString.get(number);
        }
        return romanString.get(i) + toRomanNumber(number - i);
    }

    static int toRegularNumber(String roman) throws Exception {
        int result = 0;

        int i = 0;
        while (i < roman.length()) {
            char letter = roman.charAt(i);
            int num = letterToNumber(letter);

            if (num < 0) throw new Exception("Incorrect roman number");

            i++;
            if (i == roman.length()) {
                result += num;
            }else {
                int nextNum = letterToNumber(roman.charAt(i));
                if(nextNum > num) {
                    result += (nextNum - num);
                    i++;
                }
                else result += num;
            }
        }
        return result;
    }
    public void simpleCalc(String expression) throws Exception{
       System.out.println("Result: ");
        String[] array = expression.split(" ");
        int num1 = Integer.parseInt(array[0]);
        String operation = array[1];
        int num2 = Integer.parseInt(array[2]);

        switch (operation){
            case "+":
                System.out.println(num1+num2);
                break;
            case "-":
                System.out.println(num1-num2);
                break;
            case "/":
                System.out.println(num1/num2);
                break;
            case "*":
                System.out.println(num1*num2);
                break;
            case "exit":
                System.out.println("See you soon");
                break;
            default:
                System.out.println("You entered not correctly operation, please try again");
        }
    }

}
