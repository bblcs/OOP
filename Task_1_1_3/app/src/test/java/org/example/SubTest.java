package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

class SubTest {
    @Test
    void testDerivative() {
        Expression e = new Sub(new Variable("x"), new Variable("y"));
        Expression actual = e.derivative("x");
        Expression expected = new Sub(new Number(1), new Number(0));

        assertEquals(expected, actual);
    }

    @Test
    void testEquals() {
        Expression e = new Sub(new Variable("x"), new Variable("y"));
        Expression e2 = new Sub(new Variable("x"), new Variable("y"));

        assertEquals(e, e);
        assertFalse(e.equals(null));
        assertEquals(e2, e);
        assertEquals(e2.hashCode(), e.hashCode());
    }

    @Test
    void testConstantFolding() {
        Expression e = new Sub(new Number(3), new Number(1));

        assertEquals(new Number(2), e.simplify());
    }

    @Test
    void testNoSimplificationPossible() {
        Expression e = new Sub(new Variable("x"), new Variable("y"));

        assertEquals(e, e.simplify());
    }
}

