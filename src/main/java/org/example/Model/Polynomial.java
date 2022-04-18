package org.example.Model;

import java.util.List;

public class Polynomial {
    private final List<Monomial> poly;
    //constructor for the polynomial that gets as a parameter a list of monomials and assigns it to the poly variable
    public Polynomial(List<Monomial> poly) {
        this.poly = poly;
    }
    //getter for the polynomial
    public List<Monomial> getPoly() {
        return poly;
    }
    //the polynomial function has no setter because I have defined the field poly as final
    //function for displaying the list of monomials on the screen
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        //for each monomial in the list of the polynomial poly, the string builder puts a + in front of the coefficient if it is positive
        for(Monomial mono : poly) {
            if(mono.getCoefficient() > 0) {
                builder.append("+").append(mono.toString());
            } else if(mono.getCoefficient() < 0) { //or puts just the monomial, if the coefficient is smaller than 0, meaning it is negative
                builder.append(mono.toString());
            }//monomials with coefficient 0 are not considered an are just ignored
        }
        String stringToReturn = builder.toString();
        if(stringToReturn.equals("")) // if the polynomial is empty the string becomes 0
            return "0";
        else
        {// if the coefficient of first monomial of the polynomial is positive, the + is ignored, and the string becomes the substring beginning from the position 1
            if('+' == stringToReturn.charAt(0)) {
                stringToReturn = stringToReturn.substring(1);
            }
            return stringToReturn;
        }

    }
}
