package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberTest {
    @Test
    void testDerivative() {
        Expression e = new Number(1);
        
        assertEquals(new Number(0), e.derivative("x"));
    }

    @Test
    void testEquals() {
        Expression e = new Number(1);
        Expression e2 = new Number(1);

        assertEquals(e, e);
        assertFalse(e.equals(null));
        assertEquals(e2, e);
        assertEquals(e2.hashCode(), e.hashCode());
    }
}
