package org.example;

/**
 * Interface for algorithms that can be executed on a Graph.
 *
 * @param <V> The type of vertices in the graph.
 * @param <R> The type of the result produced by the algorithm.
 */
@FunctionalInterface
public interface GraphAlgorithm<V, R> {
    /**
     * Executes the algorithm on the given graph.
     *
     * @param graph The graph to process.
     * @return The result of the algorithm.
     */
    R execute(Graph<V> graph);
}
