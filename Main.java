import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String[] input = getInput();

        String inputNum1AsString = input[0].trim();
        String inputNum2AsString = input[1].trim();

        int num1 = getInt(inputNum1AsString);
        int num2 = getInt(inputNum2AsString);
        if (!((isRomanNum(inputNum1AsString) && isRomanNum(inputNum2AsString))
                || (isInteger(inputNum1AsString) && isInteger(inputNum2AsString)))){
            throw new NullPointerException("Оба числа должны быть либо римскими, либо арабскими!");
        }
        if (num1 <= 0 || num1 >= 11 || num2 <= 0 || num2 >= 11) {
            throw new NullPointerException("Минимальное число для ввода 1, максимальное 10!");
        }

        String operation = input[2];
        String result = calc(num1, num2 , operation, !isRomanNum(inputNum1AsString) && !isRomanNum(inputNum2AsString));

        System.out.println("Результат " + inputNum1AsString + " " + operation + " " + inputNum2AsString + " = " + result);
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
        String result = " ";
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
        } else result = romanToArabic(num);
        return result;
    }
    public static char getOperation() {
        Scanner scanner = new Scanner(System.in);
        char operation;
        if(scanner.hasNext()) {
            operation = scanner.next().charAt(0);
        } else {
            throw new NullPointerException("Ввели недопустимые значения");
        }
        return operation;
    }

    public static String calc(int num1, int num2, String operation, boolean isInteger) {
        int result;
        switch (operation) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> result = num1 / num2 % 10;
            default -> throw new NullPointerException("Формат математической операции не удовлетворяет заданию " +
                    "- два операунда и один оператор");

        }
        if(isInteger) return String.valueOf(result);
        else return arabicToRoman(result);
    }
    public static int convert(String a) {
        int result;
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
            case "XL" -> result = 40;
            case "L" -> result = 50;
            case "XC" -> result = 90;
            case "C" -> result = 100;
            case "CD" -> result = 400;
            case "D" -> result = 500;
            case "CM" -> result = 900;
            case "M" -> result = 1000;
            default -> throw new NullPointerException("Число: \"" + a + "\" не является римским числом.");
        }
        return result;
    }
    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException("Так как в римской системе исчисления нет отрицательных чисел");
        }
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }
    public static int romanToArabic(String num) {
        String [] numArray = num.split("");
        int result = 0;

        for (String c: numArray){
            result += convert(c);
        }
        return result;
    }
    public static boolean isRomanNum(String num) {
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        String [] numArray = num.split("");
        int count = 0;

        for (String c: numArray){
            for (RomanNumeral romanNum: romanNumerals){
                if(romanNum.name().equals(c)) count++;
            }
        }
        return count == numArray.length;
    }
    private static boolean isInteger(String num) throws NumberFormatException {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}



enum RomanNumeral {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100),
    CD(400), D(500), CM(900), M(1000);

    private int value;

    RomanNumeral(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNumeral> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                .collect(Collectors.toList());
    }

}