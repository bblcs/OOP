package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void testSimpleAddition() {
        Expression actual = Parser.parse("5 + 10");
        Expression expected = new Add(new Number(5), new Number(10));

        assertEquals(expected, actual);
        assertEquals(15, actual.eval(""));
    }

    @Test
    void testCorrectPrecedence() {
        Expression actual = Parser.parse("3 + 2 * x");
        Expression expected = new Add(new Number(3), new Mul(new Number(2), new Variable("x")));

        assertEquals(expected, actual);
        assertEquals(23, actual.eval("x=10"));
    }

    @Test
    void testLeftAssociativity() {
        Expression actual = Parser.parse("10 - 4 - 3");
        Expression expected = new Sub(new Sub(new Number(10), new Number(4)), new Number(3));

        assertEquals(expected, actual);
        assertEquals(3, actual.eval(""));
    }

    @Test
    void testParenthesesOverridePrecedence() {
        Expression actual = Parser.parse("(3 + 2) * x");
        Expression expected = new Mul(new Add(new Number(3), new Number(2)), new Variable("x"));

        assertEquals(expected, actual);
        assertEquals(50, actual.eval("x=10"));
    }

    @Test
    void testComplexExpression() {
        String input = "x*x + y*y - 1";
        Expression actual = Parser.parse(input);
        Expression expected = new Sub(
                new Add(new Mul(new Variable("x"), new Variable("x")), new Mul(new Variable("y"), new Variable("y"))),
                new Number(1));

        assertEquals(expected, actual);
        assertEquals(33, actual.eval("x=5; y=3"));
    }

    @Test
    void testThrowsOnUnbalancedParens() {
        assertThrows(IllegalStateException.class, () -> Parser.parse("(3 + 2 * x"));
    }

    @Test
    void testThrowsOnUnexpectedCharacter() {
        assertThrows(IllegalStateException.class, () -> Parser.parse("3 + ? * x"));
    }
}
