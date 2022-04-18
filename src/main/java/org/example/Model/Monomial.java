package org.example.Model;

public class Monomial {
    private Integer coefficient;
    private Integer power;

    public Monomial() {
    }
    //constructor for monomial with 2 parameters, the coefficient and the power
    public Monomial(Integer coefficient, Integer power) {
        this.coefficient = coefficient;
        this.power = power;
    }
    //getter for the coefficient
    public Integer getCoefficient() {
        return coefficient;
    }
    //setter for the coefficient
    public void setCoefficient(Integer coefficient) {
        this.coefficient = coefficient;
    }
    //getter for the power
    public Integer getPower() {
        return power;
    }
    //setter for the power
    public void setPower(Integer power) {
        this.power = power;
    }
    //function displaying the monomial on the screen
    @Override
    public String toString() {
        String string = ""; //start with an empty string

        if (coefficient != 0) { //case when the monomial has a coefficient
            if (power == 0) { // if the monomial has power 0, meaning it is a constant
                string = Integer.toString(coefficient);
            } else if (power == 1) { //if the monomial has power 1, meaning it is either ax or -ax
                if (coefficient == 1) { //if the coefficient is 1 the monomial is x
                    string = "x";
                } else { //if the coefficient is a number different than 1 it can be either -1 or a different integer
                    if(coefficient==-1) //if the coefficient is -1 the string concatenates -x
                    {
                        string="-x";
                    }else{ //else it concatenates the coefficient and x, like ax
                        string = coefficient + "x";
                    }
                }
            } else { // if the power is not 1
                if (coefficient == 1) { //if the coeff is 1, the string concatenates x and the power, like x^a
                    string = "x^" + power;
                } else {
                    if(coefficient==-1) //if the coeff is -1 the string concatenates -x and the power, like -x^a
                    {
                        string="-x^"+power;
                    }else{ //else it concatenates the coefficient, x and the power, like ax^b
                        string = coefficient + "x^" + power;
                    }
                }
            }
        }
        return string;
    }
}
