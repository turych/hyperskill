package tictactoe;

import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < strings.length; i++) {
            result.append(strings[i]);
        }

        return result.toString().replaceAll("[0-9]", "");

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}