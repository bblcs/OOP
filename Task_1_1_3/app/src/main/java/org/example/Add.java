package org.example;

import java.util.Map;
import java.util.Objects;

/**
 * Represents the addition of two expressions.
 */
public class Add extends Expression {
    private final Expression left;
    private final Expression right;

    public Add(Expression left, Expression right) {
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
            return new Number(leftVal + rightVal);
        }

        if (isConstant(simplifiedLeft, 0))
            return simplifiedRight;
        if (isConstant(simplifiedRight, 0))
            return simplifiedLeft;

        return new Add(simplifiedLeft, simplifiedRight);
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
        Add add = (Add) obj;
        return left.equals(add.left) && right.equals(add.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public int eval(Map<String, Integer> bindings) {
        return left.eval(bindings) + right.eval(bindings);
    }

    @Override
    public Expression derivative(String var) {
        return new Add(left.derivative(var), right.derivative(var));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "+" + right.toString() + ")";
    }
}
