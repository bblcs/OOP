package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

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
