package org.example;

import java.util.Map;
import java.util.Objects;

/**
 * Represents a variable.
 */
public class Variable extends Expression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int eval(Map<String, Integer> bindings) {
        if (!bindings.containsKey(name)) {
            throw new IllegalArgumentException("Variable '" + name + "' not found in bindings.");
        }
        return bindings.get(name);
    }

    @Override
    public Expression derivative(String var) {
        if (this.name.equals(var)) {
            return new Number(1);
        } else {
            return new Number(0);
        }
    }

    @Override
    public Expression simplify() {
        return new Variable(this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Variable variable = (Variable) obj;
        return name.equals(variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
