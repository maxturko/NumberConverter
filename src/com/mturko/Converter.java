package com.mturko;

import java.util.Scanner;

public class Converter {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Enter source number radix");
                int sourceRadix = scanner.nextInt();
                System.out.println("Enter source number");
                String s =  scanner.next();
                System.out.println("Enter target number radix");
                int targetRadix = scanner.nextInt();
                if (sourceRadix < 1 || sourceRadix > 36 || targetRadix < 1 || targetRadix > 36) {
                    System.out.println("Error");
                } else {
                    String[] number = s.split("\\.");
                    String sConverted = convertIntegerPart(number[0], sourceRadix, targetRadix);
                    if (number.length == 2) {
                        sConverted += "." + convertDecimalPart(number[1], sourceRadix, targetRadix);
                    }
                    System.out.println("The number in target radix system is " + sConverted);
                }
            } catch (Throwable t) {
                System.out.println("Error");
            }



        }

        public static String convertDecimalPart (String decimalNumberString1, int sourceRadix, int targetRadix) {
            Double decimalNumber = 0.0;
            for (int i = 0; i < decimalNumberString1.length(); i++) {
                Double d = Integer.parseInt(Character.toString(decimalNumberString1.charAt(i)), sourceRadix) / Math.pow(sourceRadix, i + 1);
                decimalNumber += d;
            }
            String decimalNumberString = Double.toString(decimalNumber);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < decimalNumberString.length(); i++) {
                decimalNumber = decimalNumber * targetRadix;
                int integerPart = decimalNumber.intValue();
                decimalNumber = decimalNumber - integerPart;
                sb.append(Character.forDigit(integerPart, targetRadix));
            }
            if (sb.length() >= 5) {
                return sb.toString().substring(0, 5);
            } else {
                while (sb.length() != 5) {
                    sb.append("0");
                }
                return sb.toString();
            }
        }

        public static String convertIntegerPart (String integerNumberString, int sourceRadix, int targetRadix) {
            if (sourceRadix == 1 || targetRadix == 1) {
                if (sourceRadix == 1 && targetRadix == 1) {
                    return integerNumberString;
                } else if (sourceRadix == 1) {
                    return Integer.toString(integerNumberString.length(), targetRadix);
                } else if (targetRadix == 1) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < Integer.parseInt(integerNumberString); i++){
                        sb.append("1");
                    }
                    return sb.toString();
                }
            } else {
                int sourceNumber = Integer.parseInt(integerNumberString, sourceRadix);
                return Integer.toString(sourceNumber, targetRadix);
            }
            return "";
        }
}
