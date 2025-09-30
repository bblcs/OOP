package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivTest {
    @Test
    void testConstantFolding() {
        Expression actual = new Div(new Number(12), new Number(6)).simplify();
        Expression expected = new Number(2);

        assertEquals(expected, actual);
    }

    @Test
    void testZeroNumerator() {
        Expression actual = new Div(new Number(0), new Variable("x")).simplify();
        Expression expected = new Number(0);

        assertEquals(expected, actual);
    }

    @Test
    void testOneDenominator() {
        Expression actual = new Div(new Variable("x"), new Number(1)).simplify();
        Expression expected = new Variable("x");

        assertEquals(expected, actual);
    }

    @Test
    void testNoSimplificaton() {
        Expression actual = new Div(new Variable("x"), new Variable("y"));
        Expression expected = new Div(new Variable("x"), new Variable("y"));

        assertEquals(expected, actual);
    }

    @Test
    void testEval() {
        Expression e = new Div(new Variable("x"), new Variable("y"));
        int actual = e.eval("x=12; y=6");
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    void testDerivative() {
        Expression e = new Div(new Variable("x"), new Variable("y"));
        Expression actual = e.derivative("x");
        Expression expected = new Div(
                new Sub(
                        new Mul(new Number(1), new Variable("y")),
                        new Mul(new Variable("x"), new Number(0))),
                new Mul(new Variable("y"), new Variable("y")));

        assertEquals(expected, actual);
    }

    @Test
    void testToString() {
        Expression e = new Div(new Variable("x"), new Variable("y"));
        String actual = e.toString();
        String expected = "(x/y)";

        assertEquals(expected, actual);
    }

    @Test
    void testEquals() {
        Expression e = new Div(new Variable("x"), new Variable("y"));
        Expression e2 = new Div(new Variable("x"), new Variable("y"));

        assertEquals(e, e);
        assertFalse(e.equals(null));
        assertEquals(e2, e);
        assertEquals(e2.hashCode(), e.hashCode());
    }
}
