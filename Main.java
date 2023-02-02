import java.io.IOException;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {
        String[] input = getInput();

        int num1 = getInt(input[0].trim());
        int num2 = getInt(input[1].trim());
        if (num1 >= 11 | num1 >= 11) {
            throw new NullPointerException("Максимальное число для ввода 10!");
        }
        String operation = input[2];

        int result = calc(num1, num2 , operation);
        System.out.println("Результат " + num1 + " " + operation + " " + num2 + " = " + result);
    }
    public static String[] getInput() {
        System.out.println("Введите выражение:");
        Scanner scanner = new Scanner(System.in);
        String input = "";

        if (scanner.hasNextLine()) {
            input = scanner.nextLine();
        } else {
            throw new NullPointerException("Строка не является математической операцией!");
        }

        String operation = getOperation(input);
        if (operation.equals(" ")) {
           throw new NullPointerException("Не верный знак математической операции");
        } else {
            String[] expression = input.split("[-+*/]");
           return new String[]{expression[0], expression[1], operation};
        }
    }

    public static String getOperation(String operation) {
        String result = "";
        if (operation.matches("\\w+\\s*\\+\\s*\\w*")) {
            result = "+";
        } else if (operation.matches("\\w+\\s*-\\s*\\w*")) {
            result = "-";
        } else if (operation.matches("\\w+\\s*/\\s*\\w*")) {
            result = "/";
        }else if(operation.matches("\\w+\\s*\\*\\s*\\w*")) {
            result = "*";
        }
        return result;
    }


    public static int getInt(String num) {
        int result = 0;
        if (num.matches("\\d*")) {
            result = Integer.parseInt(num);
        } else result = convert(num);
        return result;
    }
    public static char getOperation() {
        Scanner scanner = new Scanner(System.in);
        char operation;
        if(scanner.hasNext()) {
            operation = scanner.next().charAt(0);
        } else {
           throw new NullPointerException("Ввели непопустиммые значения");


        }
        return operation;
    }

    public static int calc(int num1, int num2, String operation) {
        int result;

        switch (operation) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> result = num1 / num2 % 10;
            default -> {
                throw new NullPointerException("Формат математической операции не удовлетворяет заданию - два операунда и один оператор");

            }

        }return result;

    }
    public static int convert(String a) {
        int result = 0;
        switch (a) {
            case "I" -> result = 1;
            case "II" -> result = 2;
            case "III" -> result = 3;
            case "IV" -> result = 4;
            case "V" -> result = 5;
            case "VI" -> result = 6;
            case "VII" -> result = 7;
            case "VIII" -> result = 8;
            case "IX" -> result = 9;
            case "X" -> result = 10;
        }
        return result;
    }}