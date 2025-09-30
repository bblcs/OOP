package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimplificationTest {
    @Test
    void testRuleA_MultiplicationByZero() {
        Expression e = new Mul(new Variable("x"), new Number(0));
        Expression simplified = e.simplify();
        Expression expected = new Number(0);

        assertEquals(expected, simplified);
        assertTrue(simplified instanceof Number);
        assertEquals(0, ((Number) simplified).getValue());
    }

    @Test
    void testRuleB_MultiplicationByOne() {
        Expression e = new Mul(new Variable("x"), new Number(1));
        Expression simplified = e.simplify();
        Expression expected = new Variable("x");

        assertEquals(expected, simplified);
        assertTrue(simplified instanceof Variable);
    }

    @Test
    void testRuleC_SubtractionOfIdentical() {
        Expression e = new Sub(new Variable("y"), new Variable("y"));
        Expression simplified = e.simplify();
        Expression expected = new Number(0);

        assertEquals(expected, simplified);
    }

    @Test
    void testRuleD_ConstantFolding() {
        Expression e = new Mul(new Add(new Number(5), new Number(3)), new Number(10));
        Expression simplified = e.simplify();
        Expression expected = new Number(80);

        assertEquals(expected, simplified);
        assertTrue(simplified instanceof Number);
        assertEquals(80, ((Number) simplified).getValue());
    }

    @Test
    void testRecursiveSimplification() {
        Expression e = new Add(
                new Mul(new Variable("x"), new Number(1)),
                new Sub(new Variable("y"), new Variable("y")));
        Expression expected = new Variable("x");

        Expression simplified = e.simplify();
        assertEquals(expected, simplified);
    }

    @Test
    void testNoSimplificationPossible() {
        Expression e = new Add(new Variable("x"), new Mul(new Variable("y"), new Number(2)));
        Expression simplified = e.simplify();

        assertEquals(e, simplified);
    }

    @Test
    void testOriginalExpressionIsUnchanged() {
        Expression original = new Add(new Variable("x"), new Number(0));
        Expression e = new Add(new Variable("x"), new Number(0));
        Expression expected = new Variable("x");
        Expression simplified = e.simplify();

        assertEquals(expected, simplified);
        assertEquals(original, e);
    }
}
