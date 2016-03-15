package com.timeforprint.technotrack_dz1;

import java.util.Stack;

// Источник - https://mtaalamu.ru/blog/coding/2284.html

public class NumericParser {

    //TODO FIX DECADES

    private static enum Ranges {
        UNITS, DECADES, HUNDREDS, THOUSANDS, MILLIONS, BILLIONS
    };

    private static Stack<ThreeChar> threeChars;

    private static class ThreeChar {
        char h, d, u;
        Ranges range;
    }

    public static String digits2text(Integer d) {
        String s = d.toString();
        if (d < 0 || d > 1000000) {
            return null;
        }

        threeChars = new Stack<>();
        threeChars.push(new ThreeChar());
        threeChars.peek().range = Ranges.UNITS;
        StringBuilder sb = new StringBuilder(s).reverse();
        for (int i = 0; i < sb.length(); i++) {
            if (i > 0 && i % 3 == 0) {
                threeChars.push(new ThreeChar());
            }
            ThreeChar threeChar = threeChars.peek();
            switch (i) {
                case 0:
                    threeChar.u = sb.charAt(i);
                    break;
                case 3:
                    threeChar.range = Ranges.THOUSANDS;
                    threeChar.u = sb.charAt(i);
                    break;
                case 6:
                    threeChar.range = Ranges.MILLIONS;
                    threeChar.u = sb.charAt(i);
                    break;
                case 2:
                case 5:
                case 8:
                    threeChar.h = sb.charAt(i);
                    break;
                default:
                    threeChar.d = sb.charAt(i);
            }
        }
        StringBuilder result = new StringBuilder();
        while (!threeChars.isEmpty()) {
            ThreeChar thch = threeChars.pop();
            if(thch.h == '0' && thch.d == '0' && thch.u == '0' && !threeChars.isEmpty()) continue;
            if (thch.h > 0) {
                result.append(getHundreds(thch.h));
                result.append(' ');
            }
            if (thch.d > '0') {
                if (thch.d > '1' || (thch.d == '1' && thch.u == '0')) {
                    result.append(getDecades(thch.d));
                } else if (thch.d > '0') {
                    result.append(getTeens(thch.u));
                }
                result.append(' ');
            }
            if (thch.u > '0' && thch.d != '1') {
                result.append(getUnits(thch.u, thch.range == Ranges.THOUSANDS));
                result.append(' ');
            }
            switch (thch.range) {
                case MILLIONS:
                    if (thch.d == '1' || thch.u == '0') {
                        result.append("миллионов");
                    } else if (thch.u > '4') {
                        result.append("миллионов");
                    } else if (thch.u > '1') {
                        result.append("миллиона");
                    } else {
                        result.append("миллион");
                    }
                    break;
                case THOUSANDS:
                    if (thch.d == '1' || thch.u == '0') {
                        result.append("тысяч");
                    } else if (thch.u > '4') {
                        result.append("тысяч");
                    } else if (thch.u > '1') {
                        result.append("тысячи");
                    } else {
                        result.append("тысяча");
                    }
                    break;
            }
            result.append(' ');
        }
        char first = Character.toUpperCase(result.charAt(0));
        result.setCharAt(0, first);
        return result.toString().replaceAll("null", "");
    }

    private static String getHundreds(char dig) {
        switch (dig) {
            case '1':
                return "сто";
            case '2':
                return "двести";
            case '3':
                return "триста";
            case '4':
                return "четыреста";
            case '5':
                return "пятьсот";
            case '6':
                return "шестьсот";
            case '7':
                return "семьсот";
            case '8':
                return "восемьсот";
            case '9':
                return "девятьсот";
            default:
                return null;
        }
    }

    private static String getDecades(char dig) {
        switch (dig) {
            case '1':
                return "десять";
            case '2':
                return "двадцать";
            case '3':
                return "тридцать";
            case '4':
                return "сорок";
            case '5':
                return "пятьдесят";
            case '6':
                return "шестьдесят";
            case '7':
                return "семьдесят";
            case '8':
                return "восемьдесят";
            case '9':
                return "девяносто";
            default:
                return null;
        }
    }

    private static String getUnits(char dig, boolean female) {
        switch (dig) {
            case '1':
                return female ? "одна" : "один";
            case '2':
                return female ? "две" : "два";
            case '3':
                return "три";
            case '4':
                return "четыре";
            case '5':
                return "пять";
            case '6':
                return "шесть";
            case '7':
                return "семь";
            case '8':
                return "восемь";
            case '9':
                return "девять";
            default:
                return null;
        }
    }

    private static String getTeens(char digit) {
        String s = "";
        switch (digit) {
            case '1':
                s = "один";
                break;
            case '2':
                s = "две";
                break;
            case '3':
                s = "три";
                break;
            case '4':
                s = "четыр";
                break;
            case '5':
                s = "пят";
                break;
            case '6':
                s = "шест";
                break;
            case '7':
                s = "сем";
                break;
            case '8':
                s = "восем";
                break;
            case '9':
                s = "девят";
                break;
        }
        return s + "надцать";
    }
}
