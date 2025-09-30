package org.example;

import java.util.Map;
import java.util.Objects;

/**
 * Represents the subtraction of two expressions.
 */
public class Sub extends Expression {
    private final Expression left;
    private final Expression right;

    public Sub(Expression left, Expression right) {
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
            return new Number(leftVal - rightVal);
        }

        if (simplifiedLeft.equals(simplifiedRight)) {
            return new Number(0);
        }

        return new Sub(simplifiedLeft, simplifiedRight);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Sub sub = (Sub) obj;
        return left.equals(sub.left) && right.equals(sub.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }

    @Override
    public int eval(Map<String, Integer> bindings) {
        return left.eval(bindings) - right.eval(bindings);
    }

    @Override
    public Expression derivative(String var) {
        return new Sub(left.derivative(var), right.derivative(var));
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "-" + right.toString() + ")";
    }
}
