package org.example;

import java.util.Map;
import java.util.Objects;

/**
 * Represents the division of two expressions.
 */
public class Div extends Expression {
    private final Expression left;
    private final Expression right;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Expression simplify() {
        Expression simplifiedLeft = left.simplify();
        Expression simplifiedRight = right.simplify();

        if (simplifiedLeft instanceof Number && simplifiedRight instanceof Number) {
            int leftVal = ((Number) simplifiedLeft).getValue();
            int rightVal = ((Number) simplifiedRight).getValue();
            return new Number(leftVal / rightVal);
        }

        if (isConstant(simplifiedLeft, 0)) {
            return new Number(0);
        }
        if (isConstant(simplifiedRight, 1)) {
            return simplifiedLeft;
        }

        return new Div(simplifiedLeft, simplifiedRight);
    }

    private boolean isConstant(Expression expr, int value) {
        return expr instanceof Number && ((Number) expr).getValue() == value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Div div = (Div) obj;
        return left.equals(div.left) && right.equals(div.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public int eval(Map<String, Integer> bindings) {
        int divisor = right.eval(bindings);
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero.");
        }
        return left.eval(bindings) / divisor;
    }

    @Override
    public Expression derivative(String var) {
        return new Div(
                new Sub(
                        new Mul(left.derivative(var), right),
                        new Mul(left, right.derivative(var))),
                new Mul(right, right));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "/" + right.toString() + ")";
    }
}
