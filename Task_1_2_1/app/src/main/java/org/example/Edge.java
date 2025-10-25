package org.example;

/**
 * Represents a directed edge in a graph.
 *
 * @param <V>  the type of vertices.
 * @param src  source vertex.
 * @param dest destination vertex.
 */
public record Edge<V>(V src, V dest) {
}
