package org.example;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * An adjacency list graph representation.
 *
 * @param <V> The type of the vertices.
 */
public class AdjListGraph<V> extends AbstractGraph<V> {
    private final Map<V, Set<V>> adjList = new HashMap<>();

    @Override
    public void addVertex(V vertex) {
        adjList.putIfAbsent(vertex, new LinkedHashSet<>());
    }

    @Override
    public boolean removeVertex(V vertex) {
        boolean vertexExistedAsKey = adjList.remove(vertex) != null;

        for (Set<V> neighbors : adjList.values()) {
            neighbors.remove(vertex);
        }

        return vertexExistedAsKey;
    }

    @Override
    public void addEdge(V src, V dest) {
        addVertex(src);
        addVertex(dest);
        adjList.get(src).add(dest);
    }

    @Override
    public boolean removeEdge(V src, V dest) {
        return adjList.get(src).remove(dest);
    }

    @Override
    public Set<V> getVertices() {
        return Collections.unmodifiableSet(adjList.keySet());
    }

    @Override
    public Set<Edge<V>> getEdges() {
        Set<Edge<V>> edges = new HashSet<>();
        for (Map.Entry<V, Set<V>> entry : adjList.entrySet()) {
            V source = entry.getKey();
            for (V destination : entry.getValue()) {
                edges.add(new Edge<>(source, destination));
            }
        }
        return Collections.unmodifiableSet(edges);
    }

    @Override
    public Set<V> getNeighbors(V vertex) {
        return adjList.containsKey(vertex) ? Collections.unmodifiableSet(adjList.get(vertex)) : null;
    }
}
