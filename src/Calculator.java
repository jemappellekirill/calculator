import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

    public class Calculator {
        public static void main(String[] args) {Scanner scanner = new Scanner(System.in);
            System.out.println("Введите выражение в формате a + b, a - b, a * b, a / b: ");
            String input = scanner.nextLine();
            List<String> listString =  splitString(input);
            int type = checkStringListType(listString);
            switch (type) {
                case 1:
                    try {
                        System.out.println(Calculate(input, 1));
                    }
                    catch(IllegalArgumentException ex) {
                        System.out.println("throws Exception");
                    }
                    break;
                case 2:
                    try {
                        int firstNumber = romanToArabic (listString.getFirst());
                        int secondNumber = romanToArabic (listString.getLast());
                        char sign = extractArithmeticSign(input);
                        String expresion = Integer.toString(firstNumber) +" "+  sign +" "+ Integer.toString(secondNumber);
                        System.out.println(arabicToRoman(Calculate(expresion, 2)));
                    }
                    catch(IllegalArgumentException ex) {
                        System.out.println("throws Exception");
                    }
                    break;
                case 3:
                    try {
                        throw new IllegalArgumentException();
                } catch (IllegalArgumentException ex) {
                        System.out.println("throws Exception");
                    }

                    break;
            }
        }
        public static String arabicToRoman(int number) {
            if (number < 1 || number > 100) {
                throw new IllegalArgumentException();
            }
            int[] arabicValues = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };
            String[] romanSymbols = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
            StringBuilder result = new StringBuilder();
            int i = 0;
            while (number > 0) {
                if (number - arabicValues[i] >= 0) {
                    result.append(romanSymbols[i]);
                    number -= arabicValues[i];
                } else {
                    i++;
                }
            }
            return result.toString();
        }
        public static char extractArithmeticSign(String input) {
            String regex = "[-+*/]";
            java.util.regex.Matcher matcher = java.util.regex.Pattern.compile(regex).matcher(input);
            if (matcher.find()) {
                return matcher.group().charAt(0);
            } else {

                throw new IllegalArgumentException();
            }
        }
        public static int romanToArabic(String romanNumber) {
            int[] values = { 100, 90, 50, 40, 10, 9, 5, 4, 1 };
            String[] numerals = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

            int result = 0;
            int i = 0;
            while (i < numerals.length) {
                if (romanNumber.startsWith(numerals[i])) {
                    result += values[i];
                    romanNumber = romanNumber.substring(numerals[i].length());
                } else {
                    i++;
                }
            }
            return result;
        }
        public static List<String> splitString(String input) {
            input = input.replaceAll("\\s", "");
            String[] operations = input.split("[-+*/]");
            List<String> resultList = new ArrayList<>();
            for (int i = 0; i < operations.length - 1; i++) {
                String operation = operations[i];

                resultList.add(operation);
            }

            resultList.add(operations[operations.length - 1]);
            return resultList;
        }
        public static int checkStringListType(List<String> inputList) {
            boolean isAllIntegers = true;
            boolean isAllRomanNumerals = true;
            for (String str : inputList) {
                if (!str.matches("^\\d+$")) {
                    isAllIntegers = false;
                }
                if (!str.matches("[IVXLCDM]+")) {
                    isAllRomanNumerals = false;
                }
            }

            if (isAllIntegers) {
                return 1;
            } else if (isAllRomanNumerals) {
                return 2;
            } else {
                return 3;
            }
        }
        public static int Calculate(String expresion, int type){

            String[] elements = expresion.split(" ");
            if (elements.length != 3) {
                throw new IllegalArgumentException();
            }

            int num1;
            int num2;
            String operator;
            num1 = Integer.parseInt(elements[0]);
            num2 = Integer.parseInt(elements[2]);
            operator = elements[1];

            int result;
            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    result = num1 / num2;
                    break;
                default:
                    throw new IllegalArgumentException();
            }

            if (type == 2){
                if (result < 0) {
                    throw new IllegalArgumentException();
                }
            }

            return result;
        }
    }

