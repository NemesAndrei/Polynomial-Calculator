package org.example.Controller;

import org.example.Model.Monomial;
import org.example.Model.Polynomial;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonoUtils {
    //function for parsing the given string into a polynomial(list of monomials)
    //I have used regular expressions for splitting the given string in order to be able to distinguish the monomials
    public Polynomial stringToPoly(String string) {
            List<Monomial> monomialList = new ArrayList<>();
        Pattern pattern = Pattern.compile("([+-]?[^-+]+)");
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) { //while we can find groups that match the pattern given before
            String str2 = matcher.group(1);
            Monomial mono1;
            String[] stringList = str2.split("x"); // the string is split with respect to x
            if (str2.matches("^[-+]?[0-9]+x\\^-?[0-9]+$")) { //monomial has power and coefficient, like 3x^2
                mono1 = new Monomial(Integer.parseInt(stringList[0]), Integer.parseInt(stringList[1].substring(1)));
            } else if (str2.matches("^[-+]?x\\^-?[0-9]+")) { //monomial has no coefficient, like x^2
                if ('-' == str2.charAt(0)) { //distinguish between x^2 and -x^2 by the sign
                    mono1 = new Monomial(-1, Integer.parseInt(stringList[1].substring(1)));
                } else {
                    mono1 = new Monomial(1, Integer.parseInt(stringList[1].substring(1)));
                }
            } else if (str2.matches("^[-+]?[0-9]+x$")) { //monomial has no power, like 3x
                mono1 = new Monomial(Integer.parseInt(stringList[0]), 1);
            } else if (str2.matches("^[-+]?x$")) { //monomial has no power and no coefficient, meaning either x or -x
                if ('-' == str2.charAt(0)) { //distinguish between x and -x, by the sign
                    mono1 = new Monomial(-1, 1);
                } else {
                    mono1 = new Monomial(1, 1);
                }
            } else { // monomial is a number, meaning a constant like 6
                mono1 = new Monomial(Integer.parseInt(str2), 0);
            }
            monomialList.add(mono1);
        }
        //the list of monomials is sorted in descending order with respect to the power of the monomials
        monomialList.sort((o1, o2) -> o2.getPower().compareTo(o1.getPower()));
        return new Polynomial(monomialList);
    }
}
