package org.example;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a constant integer number.
 */
public class Number extends Expression {
    private final int value;

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public Expression simplify() {
        return new Number(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Number number = (Number) obj;
        return value == number.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int eval(Map<String, Integer> bindings) {
        return value;
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
