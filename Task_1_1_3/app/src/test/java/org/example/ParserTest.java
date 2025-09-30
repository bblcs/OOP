package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ParserTest {

    @Test
    void testSimpleAddition() {
        Expression e = Parser.parse("5 + 10");

        assertEquals("(5+10)", e.toString());
        assertEquals(15, e.eval(""));
    }

    @Test
    void testCorrectPrecedence() {
        Expression e = Parser.parse("3 + 2 * x");

        assertEquals("(3+(2*x))", e.toString());
        assertEquals(23, e.eval("x=10"));
    }

    @Test
    void testLeftAssociativity() {
        Expression e = Parser.parse("10 - 4 - 3");

        assertEquals("((10-4)-3)", e.toString());
        assertEquals(3, e.eval(""));
    }
    
    @Test
    void testParenthesesOverridePrecedence() {
        Expression e = Parser.parse("(3 + 2) * x");

        assertEquals("((3+2)*x)", e.toString());
        assertEquals(50, e.eval("x=10"));
    }

    @Test
    void testComplexExpression() {
        String input = "x*x + y*y - 1";
        Expression e = Parser.parse(input);

        assertEquals("(((x*x)+(y*y))-1)", e.toString());
        assertEquals(33, e.eval("x=5; y=3"));
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
