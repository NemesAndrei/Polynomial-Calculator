package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.example.Controller.MonoUtils;
import org.example.Controller.Operations;
import org.example.Model.Monomial;
import org.example.Model.Polynomial;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testToString()
    {
        Monomial mono1=new Monomial(3,2);
        assertEquals("3x^2",mono1.toString());
        Monomial mono2=new Monomial(5,3);
        assertEquals("5x^3",mono2.toString());
        Monomial mono3=new Monomial(4,5);
        assertEquals("4x^5",mono3.toString());
        Monomial mono4=new Monomial(1,1);
        assertEquals("x",mono4.toString());
        MonoUtils utils=new MonoUtils();
        Polynomial poly1=utils.stringToPoly("3x^2+3x+2");
        assertEquals("3x^2+3x+2",poly1.toString());
        Polynomial poly2=utils.stringToPoly("5x^4+3x^3+2x^2+3x+2");
        assertEquals("5x^4+3x^3+2x^2+3x+2",poly2.toString());
        //even if the polynomial is not given in descending order of the powers, the program will sort them as such
        Polynomial poly3=utils.stringToPoly("5x^4+3x^2+2x^3+3x^5+2x^6");
        assertEquals("2x^6+3x^5+5x^4+2x^3+3x^2",poly3.toString());
    }
    @Test
    public void testOperation()
    {
        //Normal Operations

        //addition
        MonoUtils utils=new MonoUtils();
        Polynomial poly1=utils.stringToPoly("3x^2+3x+2");
        Polynomial poly2=utils.stringToPoly("5x^4+3x^3+2x^2+3x+2");
        assertEquals("5x^4+3x^3+5x^2+6x+4", Operations.addition(poly1,poly2).toString());
        //subtraction
        assertEquals("-5x^4-3x^3+x^2",Operations.subtraction(poly1,poly2).toString());
        //multiplication
        assertEquals("15x^6+24x^5+25x^4+21x^3+19x^2+12x+4",Operations.multiplication(poly1,poly2).toString());
        //derivation first polynomial
        assertEquals("6x+3",Operations.derivation(poly1).toString());
        //derivation second polynomial
        assertEquals("20x^3+9x^2+4x+3",Operations.derivation(poly2).toString());

        //Tests for special cases for the operations

        //Case when one polynomial is 0
        poly1=utils.stringToPoly("0");
        poly2=utils.stringToPoly("5x^4+3x^3+2x^2+3x+2");
        assertEquals("5x^4+3x^3+2x^2+3x+2", Operations.addition(poly1,poly2).toString());
        //subtraction
        assertEquals("-5x^4-3x^3-2x^2-3x-2",Operations.subtraction(poly1,poly2).toString());
        //multiplication
        assertEquals("0",Operations.multiplication(poly1,poly2).toString());
        //derivation first polynomial
        assertEquals("0",Operations.derivation(poly1).toString());
        //derivation second polynomial
        assertEquals("20x^3+9x^2+4x+3",Operations.derivation(poly2).toString());

        //addition where the result is 0
        poly1=utils.stringToPoly("x+1");
        poly2=utils.stringToPoly("-x-1");
        assertEquals("0",Operations.addition(poly1,poly2).toString());

        //subtraction where the result is 0
        poly1=utils.stringToPoly("x+1");
        poly2=utils.stringToPoly("x+1");
        assertEquals("0",Operations.subtraction(poly1,poly2).toString());

        //derivation where the polynomial is a constant(including 0) must return 0
        poly1=utils.stringToPoly("6");
        assertEquals("0",Operations.derivation(poly1).toString());
        poly1=utils.stringToPoly("0");
        assertEquals("0",Operations.derivation(poly1).toString());

    }
}
