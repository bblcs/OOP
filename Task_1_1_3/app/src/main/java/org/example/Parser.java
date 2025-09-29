package org.example;

/**
 * Recursive descent parses, grammar is in grammar.txt.
 */
public class Parser {
    private final String input;
    private int pos = 0;

    private Parser(String input) {
        this.input = input.replaceAll("\\s+", "");
    }

    public static Expression parse(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty.");
        }
        return new Parser(input).parse();
    }

    private Expression parse() {
        Expression expr = parseExpression();
        if (!isAtEnd()) {
            throw new IllegalStateException("Unexpected characters at the end of expression.");
        }
        return expr;
    }

    // expression -> term
    private Expression parseExpression() {
        return parseTerm();
    }

    // term -> factor ( ( "+" | "-" ) factor )*
    private Expression parseTerm() {
        Expression expr = parseFactor();

        while (match('+', '-')) {
            char operator = previous();
            Expression right = parseFactor();
            if (operator == '+') {
                expr = new Add(expr, right);
            } else {
                expr = new Sub(expr, right);
            }
        }
        return expr;
    }

    // factor -> primary ( ( "*" | "/" ) primary )*
    private Expression parseFactor() {
        Expression expr = parsePrimary();

        while (match('*', '/')) {
            char operator = previous();
            Expression right = parsePrimary();
            if (operator == '*') {
                expr = new Mul(expr, right);
            } else {
                expr = new Div(expr, right);
            }
        }
        return expr;
    }

    // primary -> NUMBER | VARIABLE | "(" expression ")"
    private Expression parsePrimary() {
        if (Character.isDigit(peek())) {
            return parseNumber();
        }

        if (Character.isLetter(peek())) {
            return parseVariable();
        }

        if (match('(')) {
            Expression expr = parseExpression();
            if (peek() != ')') {
                throw new IllegalStateException("Expected ')' after expression.");
            }
            advance();
            return expr;
        }

        throw new IllegalStateException("Unexpected character: " + peek());
    }

    private Number parseNumber() {
        StringBuilder sb = new StringBuilder();
        while (!isAtEnd() && Character.isDigit(peek())) {
            sb.append(advance());
        }
        return new Number(Integer.parseInt(sb.toString()));
    }

    private Variable parseVariable() {
        StringBuilder sb = new StringBuilder();
        while (!isAtEnd() && Character.isLetter(peek())) {
            sb.append(advance());
        }
        return new Variable(sb.toString());
    }

    private boolean match(char... chars) {
        for (char c : chars) {
            if (!isAtEnd() && peek() == c) {
                advance();
                return true;
            }
        }
        return false;
    }
    
    private char advance() {
        return input.charAt(pos++);
    }
    
    private char peek() {
        if (isAtEnd()) return '\0';
        return input.charAt(pos);
    }

    private char previous() {
        return input.charAt(pos - 1);
    }
    
    private boolean isAtEnd() {
        return pos >= input.length();
    }
}
