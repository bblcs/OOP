package org.example;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract base class for all mathematical expressions in the AST.
 */
public abstract class Expression {

    /**
     * Parses variable string into a map.
     */
    private static Map<String, Integer> parseAssignments(String assignments) {
        Map<String, Integer> bindings = new HashMap<>();
        if (assignments == null || assignments.trim().isEmpty()) {
            return bindings;
        }

        String[] pairs = assignments.split(";");
        for (String pair : pairs) {
            String[] parts = pair.split("=");
            if (parts.length == 2) {
                String varName = parts[0].trim();
                int value = Integer.parseInt(parts[1].trim());
                bindings.put(varName, value);
            }
        }
        return bindings;
    }

    /**
     * Evaluates the expression given a String of variables.
     *
     * @param bindings name:int.
     * @return result of the evaluation.
     * @throws IllegalArgumentException if bindings contain not all
     *                                  needed variables.
     */
    public abstract int eval(Map<String, Integer> bindings);

    /**
     * String eval.
     *
     * @param assignments variables.
     * @return result.
     * @throws IllegalArgumentException if bindings contain not all needed
     *                                  variables.
     */
    public int eval(String assignments) {
        Map<String, Integer> bindings = parseAssignments(assignments);
        return this.eval(bindings);
    }

    /**
     * Computes the derivative.
     *
     * @param var The name of the variable to differentiate with respect to.
     * @return A new Expression object representing the derivative.
     */
    public abstract Expression derivative(String var);

    /**
     * Returns a string representation.
     *
     * @return The string representation.
     */
    @Override
    public abstract String toString();

    /**
     * A convenience method to print the expression to the console.
     */
    public void print() {
        System.out.println(this.toString());
    }

    /**
     * Creates a simplified expresion.
     *
     * @return Simplified Expression.
     */
    public abstract Expression simplify();

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();
}
