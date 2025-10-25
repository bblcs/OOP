package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Performs a topological sort on a DAG.
 *
 * @param <V> The type of vertices in the graph.
 */
public class Toposort<V> implements GraphAlgorithm<V, List<V>> {

    @Override
    public List<V> execute(Graph<V> graph) {
        Map<V, Integer> inDegree = new HashMap<>();
        Queue<V> queue = new LinkedList<>();
        List<V> sortedOrder = new ArrayList<>();

        for (V vertex : graph.getVertices()) {
            inDegree.put(vertex, 0);
        }
        for (var edge : graph.getEdges()) {
            inDegree.compute(edge.dest(), (v, degree) -> degree + 1);
        }

        for (Map.Entry<V, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            V u = queue.poll();
            sortedOrder.add(u);

            Set<V> neighbors = graph.getNeighbors(u);
            if (neighbors != null) {
                for (V v : neighbors) {
                    inDegree.put(v, inDegree.get(v) - 1);
                    if (inDegree.get(v) == 0) {
                        queue.add(v);
                    }
                }
            }
        }

        if (sortedOrder.size() != graph.getVertices().size()) {
            throw new IllegalStateException("Graph has a cycle.");
        }

        return sortedOrder;
    }
}
