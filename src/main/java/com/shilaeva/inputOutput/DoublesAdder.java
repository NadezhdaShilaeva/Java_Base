package com.shilaeva.inputOutput;

import java.util.Scanner;

public class DoublesAdder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double result = 0;

        while (scanner.hasNext()) {
            String token = scanner.next();
            try {
                double number = Double.parseDouble(token);
                result += number;
            } catch (NumberFormatException ignored) {
            }
        }

        System.out.printf("%.6f", result);
    }
}
