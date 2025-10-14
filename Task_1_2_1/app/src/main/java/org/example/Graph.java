package org.example;

import java.util.Set;

/**
 * Interface for a directed graphs.
 *
 * @param <V> the type of the vertices.
 */
public interface Graph<V> {

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to add.
     */
    void addVertex(V vertex);

    /**
     * Removes a vertex from the graph.
     *
     * @param vertex the vertex to remove.
     * @return true if the vertex was found and removed, false otherwise.
     */
    boolean removeVertex(V vertex);

    /**
     * Adds a directed edge between two vertices.
     * If the vertices do not exist, they are added.
     *
     * @param source      the source vertex.
     * @param destination the destination vertex.
     */
    void addEdge(V source, V destination);

    /**
     * Removes a directed edge between two vertices.
     *
     * @param source      the source vertex.
     * @param destination the destination vertex.
     * @return true if the edge was found and removed, false otherwise.
     */
    boolean removeEdge(V source, V destination);

    /**
     * Retrieves a set of all vertices in the graph.
     *
     * @return an unmodifiable set of vertices.
     */
    Set<V> getVertices();

    /**
     * Retrieves a set of all edges in the graph.
     *
     * @return an unmodifiable set of edges.
     */
    Set<Edge<V>> getEdges();

    /**
     * Retrieves a set of closest neighbors for a given vertex.
     * A neighbor is a vertex that can be reached by a direct edge from the given
     * vertex.
     *
     * @param vertex the vertex whose neighbors to find.
     * @return a set of neighboring vertices, or null if the vertex is not in the
     *         graph.
     */
    Set<V> getNeighbors(V vertex);
}
