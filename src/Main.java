import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Введите выражение. Аргументы и знак должны быть разделены пробелом");
            System.out.println("Программа принимает арабские и римские числа");
            System.out.println("Числа от 0 до 10 включительно и от I до X включительно");
            String inputLine = input.nextLine();
            System.out.println(calc(inputLine));
        }
    }
    public static String calc(String input){
            String result = "";
            int promResult = 0;
            boolean checkOperator = false;
            String[] subInput = input.split(" ");
            if (subInput.length == 1) {
                throw new IllegalArgumentException("строка не является математической операцией");
            }
            if (subInput.length > 3) {
                throw new IllegalArgumentException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
            if (subInput[1].equals("+") || subInput[1].equals("-") || subInput[1].equals("*") || subInput[1].equals("/")) {
                checkOperator = true;
            }
            if(!checkOperator){throw new IllegalArgumentException("оператор не соответствует условиям (+, -, /, *)");}

            String first = subInput[0], second = subInput[2];
            boolean rym = false;
            String operator;

            if (checkIfRym(first) && checkIfRym(second)) {
                rym = true;
            }

        if ((checkIfRym(first) && !checkIfRym(second)) || (!checkIfRym(first) && checkIfRym(second))) {
            throw new IllegalArgumentException("используются одновременно разные системы счисления");
            }
        if( checkIfRym(first) && Integer.parseInt(switchRymToArab(first)) > 10){throw new IllegalArgumentException("число должно быть от I до X включительно");}
        if( checkIfRym(second) && Integer.parseInt(switchRymToArab(second)) > 10){throw new IllegalArgumentException("число должно быть от I до X включительно");}

        if(rym) {
                first = switchRymToArab(subInput[0]);
                second = switchRymToArab(subInput[2]);
            }
            int a = Integer.parseInt(first);
            int b = Integer.parseInt(second);
            operator = subInput[1];

            if (a > 10 || a < 1 || b > 10 || b < 1) {
                throw new IllegalArgumentException(" число должно быть от 1 до 10 включительно");
            }
            if (operator.equals("+")) {
                promResult = a + b;
            } else if (operator.equals("-")) {
                promResult = a - b;
            } else if (operator.equals("*")) {
                promResult = a * b;
            } else if (operator.equals("/")) {
                promResult = a / b;
            }
            if (rym && promResult < 0) {
                throw new IllegalArgumentException("в римской системе нет отрицательных чисел");
            }
            if (rym && promResult > 0) return switchResultToRym(promResult);
            else
                return String.valueOf(promResult);
    }

    public static String switchRymToArab(String value) {
        String letter;
        switch (value) {
            case "I":       letter = "1";
                break;
            case "II":      letter = "2";
                break;
            case "III":     letter = "3";
                break;
            case "IV":      letter = "4";
                break;
            case "V":       letter = "5";
                break;
            case "VI":      letter = "6";
                break;
            case "VII":     letter = "7";
                break;
            case "VIII":    letter = "8";
                break;
            case "IX":      letter = "9";
                break;
            case "X":       letter = "10";
                break;
            default:        letter = "11";
        }
        return letter;
    }

    public static boolean checkIfRym(String value) {
        boolean Rym = false;
        char[] chars = value.toCharArray();
        for(int i = 0; i < chars.length; i ++){
            if(chars[i] == 'I' || chars[i] == 'V' || chars[i] == 'X' || chars[i] == 'C' || chars[i] == 'L'){
                Rym = true;
            }
        }
        return Rym;
    }

    public static String switchResultToRym(int promResult){
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < arab.length; i += 1) {
            while (promResult >= arab[i]){
                promResult -= arab[i];
                res.append(roman[i]);
            }
        }
        return res.toString();
    }
}
