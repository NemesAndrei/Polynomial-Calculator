package org.example.Controller;

import org.example.Model.Monomial;
import org.example.Model.Polynomial;

import java.util.ArrayList;
import java.util.List;

public class Operations {
    //addition method
    public static Polynomial addition(Polynomial p1, Polynomial p2) {//define two monomial lists tha contain the list of monomials of the two polynomials and a final list
        List<Monomial> listOne = new ArrayList<>(p1.getPoly());
        List<Monomial> listTwo = new ArrayList<>(p2.getPoly());
        List<Monomial> listFinal = new ArrayList<>();
        for(Monomial mono1 : p1.getPoly()) {//for each monomial in p1
            for(Monomial mono2 : p2.getPoly()) { //with each monomial in p2
                if(mono1.getPower().equals(mono2.getPower())) { // if their power is equal, create a monomial with the sum of coeff and the same power and add it to the final list
                    listFinal.add(new Monomial(mono1.getCoefficient() + mono2.getCoefficient(), mono1.getPower()));
                    listOne.remove(mono1);//remove these monomials from the two lists
                    listTwo.remove(mono2);
                }
            }
        }
        //for the monomials that didn't have a correspondent in the other list, add them all as they are to the final list
        listFinal.addAll(listOne);
        listFinal.addAll(listTwo);
        //sort the resulting list in descending order with regards to the power of the monomials
        listFinal.sort((o1, o2) -> o2.getPower().compareTo(o1.getPower()));
        return new Polynomial(listFinal);
    }
    //subtraction method
    public static Polynomial subtraction(Polynomial p1, Polynomial p2) {//define two monomial lists tha contain the list of monomials of the two polynomials, and a final list
        List<Monomial> listOne = new ArrayList<>(p1.getPoly());
        List<Monomial> listTwo = new ArrayList<>(p2.getPoly());
        List<Monomial> listFinal = new ArrayList<>();
        for(Monomial mono1 : p1.getPoly()) {//for each monomial in p1
            for(Monomial mono2 : p2.getPoly()) {//with each monomial in p2
                if(mono1.getPower().equals(mono2.getPower())) {// if their power is equal, create a monomial with the diff of coeff, coeff of mono1-coeff of mono2
                    // and the same power and add it to the final list
                    listFinal.add(new Monomial(mono1.getCoefficient() - mono2.getCoefficient(), mono1.getPower()));
                    listOne.remove(mono1);
                    listTwo.remove(mono2);
                }
            }
        }
        //for the monomials in the first list that didn't have correspondent in the second list, add them to the final list as they are
        listFinal.addAll(listOne);
        for(Monomial mono:listTwo)
        {//for the monomials in the second list that didn't have correspondent in the first list, add them with coefficient*(-1)
            listFinal.add(new Monomial(mono.getCoefficient()*(-1),mono.getPower()));
        }
        //sort the resulting list in descending order with regards to the power of the monomials
        listFinal.sort((o1, o2) -> o2.getPower().compareTo(o1.getPower()));
        return new Polynomial(listFinal);
    }
    //derivation method
    public static Polynomial derivation(Polynomial poly)
    {
        List<Monomial> polyFinal = new ArrayList<>();//create a finalList of monomials
        for(Monomial mono:poly.getPoly())
        {//for each monomial in the given polynomial check if its power is different than 0, meaning like 3x^2
            if(mono.getPower()!=0)
            {//create a new monomial with the coefficient from the monomial multiplied by the power, and with power equal to the original power-1 and add it to the final list
                Monomial monoAux=new Monomial(mono.getCoefficient()*mono.getPower(),mono.getPower()-1);
                polyFinal.add(monoAux);
            }
        }
        //sort the resulting list in descending order with regards to the power of the monomials
        polyFinal.sort((o1, o2) -> o2.getPower().compareTo(o1.getPower()));
        return new Polynomial(polyFinal);
    }
    //multiplication method
    public static Polynomial multiplication(Polynomial poly1, Polynomial poly2)
    {//create two lists, one that will be used when multiplying the monomials, and a final list that will be returned
        List<Monomial> multiplyList = new ArrayList<>();
        List<Monomial> finalList = new ArrayList<>();
        for(Monomial mono1 : poly1.getPoly()) {//for each monomial in the first polynomial
            for (Monomial mono2 : poly2.getPoly()) {//with each monomial in the second polynomial
                //add to the multiplying list a new monomial with coefficient equal to the product of the coefficients of the monomials and with power equal to the sum of powers
                multiplyList.add(new Monomial(mono1.getCoefficient() * mono2.getCoefficient(), mono1.getPower() + mono2.getPower()));
            }
        }//sort the resulting list in descending order with regards to the power of the monomials
        multiplyList.sort((o1, o2) -> o2.getPower().compareTo(o1.getPower()));
        Integer var = multiplyList.get(0).getPower();
        Integer counter = 0;
        //after the multiplying is done, the monomials have to be added to a finalList and because I have sorted them, the problem becomes just adding the coefficients of the polynomials
        //with the same power and adding them to the list
        for(Monomial m1 : multiplyList) {//this for works like this:while the monomials have the same power, the coefficient gets added to counter
            if(m1.getPower().equals(var)) {
                counter += m1.getCoefficient();
            } else {//when i have reached a monomial with different power, the previous monomial gets added to the list, and counter gets the value of the new polynomial
                finalList.add(new Monomial(counter, var));
                var = m1.getPower();
                counter = m1.getCoefficient();
            }
        }//after this for, the final monomial will not be added automatically, so we have to do that manually, then the resulting list is returned
        finalList.add(new Monomial(counter, var));
        return new Polynomial(finalList);
    }
}
