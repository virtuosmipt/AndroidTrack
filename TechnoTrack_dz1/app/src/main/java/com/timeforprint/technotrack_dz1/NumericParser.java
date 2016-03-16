package com.timeforprint.technotrack_dz1;

import android.app.Activity;
import android.content.res.Resources;

import java.util.Stack;

// Источник - https://mtaalamu.ru/blog/coding/2284.html

public class NumericParser  {
    static Resources res;
    //TODO FIX DECADES

    public NumericParser (Resources res){
        this.res=res;
    }

    private static enum Ranges {
        UNITS, DECADES, HUNDREDS, THOUSANDS, MILLIONS, BILLIONS
    };

    private static Stack<ThreeChar> threeChars;

    private static class ThreeChar {

        char h, d, u;
        Ranges range;
    }

    public static String digits2text(Integer d) {

        String ss = new String();
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
                        result.append(res.getString(R.string.millionov));
                    } else if (thch.u > '4') {
                        result.append(res.getString(R.string.millionov) );
                    } else if (thch.u > '1') {
                        result.append(res.getString(R.string.milliona));
                    } else {
                        result.append(res.getString(R.string.million));
                    }
                    break;
                case THOUSANDS:
                    if (thch.d == '1' || thch.u == '0') {
                        result.append(res.getString(R.string.tisyach));
                    } else if (thch.u > '4') {
                        result.append(res.getString(R.string.tisyach));
                    } else if (thch.u > '1') {
                        result.append(res.getString(R.string.tisyachi));
                    } else {
                        result.append(res.getString(R.string.tisyacha));
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
                return  res.getString(R.string.sto);
            case '2':
                return  res.getString(R.string.dvesti);
            case '3':
                return  res.getString(R.string.trista);
            case '4':
                return  res.getString(R.string.chetiresta);
            case '5':
                return  res.getString(R.string.pyatsot);
            case '6':
                return  res.getString(R.string.shestsot);
            case '7':
                return  res.getString(R.string.semsot);
            case '8':
                return  res.getString(R.string.vosemsot);
            case '9':
                return  res.getString(R.string.devyatsot);
            default:
                return null;
        }
    }

    private static String getDecades(char dig) {
        switch (dig) {
            case '1':
                return res.getString(R.string.desyat);
            case '2':
                return res.getString(R.string.dvadsat);
            case '3':
                return res.getString(R.string.tridsat);
            case '4':
                return res.getString(R.string.sorok);
            case '5':
                return res.getString(R.string.pyatdesyat);
            case '6':
                return res.getString(R.string.shestdesyat);
            case '7':
                return res.getString(R.string.semdesyat);
            case '8':
                return res.getString(R.string.vosemdesyat);
            case '9':
                return res.getString(R.string.devyanosto);
            default:
                return null;
        }

    }

    private static String getUnits(char dig, boolean female) {
        switch (dig) {
            case '1':
                return female ? res.getString(R.string.odna) : res.getString(R.string.odin);
            case '2':
                return female ? res.getString(R.string.dve) : res.getString(R.string.dva);
            case '3':
                return res.getString(R.string.tri);
            case '4':
                return res.getString(R.string.chetire);
            case '5':
                return res.getString(R.string.pyat);
            case '6':
                return res.getString(R.string.shest);
            case '7':
                return res.getString(R.string.sem);
            case '8':
                return res.getString(R.string.vosem);
            case '9':
                return res.getString(R.string.devyat);
            default:
                return null;
        }
    }

    private static String getTeens(char digit) {
        String s = "";
        switch (digit) {
            case '1':
                s = res.getString(R.string.odin);
                break;
            case '2':
                s = res.getString(R.string.dve);
                break;
            case '3':
                s = res.getString(R.string.tri);
                break;
            case '4':
                s = res.getString(R.string.chetir);
                break;
            case '5':
                s = res.getString(R.string.pyatt);
                break;
            case '6':
                s = res.getString(R.string.shestt);
                break;
            case '7':
                s = res.getString(R.string.semm);
                break;
            case '8':
                s = res.getString(R.string.vosemm);
                break;
            case '9':
                s = res.getString(R.string.devyatt);
                break;
        }
        return s + res.getString(R.string.nadsat);
    }
}
