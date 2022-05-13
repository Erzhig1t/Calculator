package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose type of calculator: ");
        String choose = scanner.nextLine();
        while (true)
        if(choose.equals("R")){
            romanCalculator();
        }else if(choose.equals("S")){
            simpleCalculator();
        }else {
            System.out.println("Please choose type of calculator! Try again");
            break;
        }
    }

    public static void simpleCalculator() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            NumberService numberService = new NumberService();
            System.out.println("Welcome to Simple Calculator");
            System.out.println("Here you need to write the expression with a space:");
            System.out.println("Input: ");
            String input = scanner.nextLine();

            try {
                numberService.simpleCalc(input);
            } catch(Exception e){
                System.out.println(e.getMessage());
            }
            break;
        }
    }

    public static void romanCalculator() {

        Scanner scanner = new Scanner(System.in);
        startCalc();

        while (true) {
            System.out.println("Please write with a space.");
            System.out.println("Input: ");
            String line = scanner.nextLine();

            if(line.equals("exit")){
                exitCalc();
                break;
            }

            try {
                String[] symbols = line.split(" ");
                if (symbols.length != 3) throw new Exception("Something is wrong, please try again");

                Number firstNumber = NumberService.parseAndValidate(symbols[0]);
                Number secondNumber = NumberService.parseAndValidate(symbols[2], firstNumber.getType());
                String result = ActionService.calculate(firstNumber, secondNumber, symbols[1]);
                System.out.println("Output: \n" + result);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                exitCalc();
                break;
            }
        }

        scanner.close();
    }

    private static void startCalc() {
        System.out.println("Welcome to Roman Calculator");
        System.out.println("If you want to leave the program, enter 'exit'");
    }

    private static void exitCalc() {

        System.out.println("See you soon!");
    }
}




