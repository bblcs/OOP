package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MulTest {
    @Test
    void testDerivative() {
        Expression e = new Mul(new Variable("x"), new Variable("y"));
        Expression actual = e.derivative("x");
        Expression expected = new Add(
                new Mul(new Number(1), new Variable("y")),
                new Mul(new Variable("x"), new Number(0)));

        assertEquals(expected, actual);
    }

    @Test
    void testEquals() {
        Expression e = new Mul(new Number(12), new Number(2));
        Expression e2 = new Mul(new Number(12), new Number(2));

        assertEquals(e, e);
        assertFalse(e.equals(null));
        assertEquals(e2, e);
        assertEquals(e2.hashCode(), e.hashCode());
    }
}
