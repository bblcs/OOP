package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An incidence matrix graph representation.
 *
 * @param <V> the type of the vertices.
 */
public class IncMatrixGraph<V> extends AbstractGraph<V> {
    private final Map<V, Integer> vertexToIndex;
    private final List<V> indexToVertex;
    private final Map<Edge<V>, Integer> edgeToIndex;
    private final List<Edge<V>> indexToEdge;

    private final List<int[]> incMatrixColumns;

    public IncMatrixGraph() {
        this.vertexToIndex = new LinkedHashMap<>();
        this.indexToVertex = new ArrayList<>();
        this.edgeToIndex = new LinkedHashMap<>();
        this.indexToEdge = new ArrayList<>();
        this.incMatrixColumns = new ArrayList<>();
    }

    @Override
    public void addVertex(V vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            int newIndex = indexToVertex.size();
            vertexToIndex.put(vertex, newIndex);
            indexToVertex.add(vertex);
        }
    }

    @Override
    public boolean removeVertex(V vertex) {
        if (!vertexToIndex.containsKey(vertex)) {
            return false;
        }

        new ArrayList<>(getEdges()).stream()
                .filter(edge -> edge.src().equals(vertex) || edge.dest().equals(vertex))
                .forEach(edge -> removeEdge(edge.src(), edge.dest()));

        int removedIdx = vertexToIndex.get(vertex);
        vertexToIndex.remove(vertex);
        indexToVertex.remove(removedIdx);

        vertexToIndex.clear();
        for (int i = 0; i < indexToVertex.size(); i++) {
            vertexToIndex.put(indexToVertex.get(i), i);
        }

        return true;
    }

    @Override
    public void addEdge(V src, V dest) {
        addVertex(src);
        addVertex(dest);

        Edge<V> edge = new Edge<>(src, dest);
        if (edgeToIndex.containsKey(edge)) {
            return;
        }

        int srcIdx = vertexToIndex.get(src);
        int destIdx = vertexToIndex.get(dest);

        int[] newCol = new int[indexToVertex.size()];
        newCol[srcIdx] = 1;
        newCol[destIdx] = -1;

        int newEdgeIndex = indexToEdge.size();
        edgeToIndex.put(edge, newEdgeIndex);
        indexToEdge.add(edge);
        incMatrixColumns.add(newCol);
    }

    @Override
    public boolean removeEdge(V src, V dest) {
        Edge<V> edge = new Edge<>(src, dest);
        Integer edgeIndexToRemove = edgeToIndex.remove(edge);

        if (edgeIndexToRemove == null) {
            return false;
        }

        indexToEdge.remove((int) edgeIndexToRemove);
        incMatrixColumns.remove((int) edgeIndexToRemove);

        edgeToIndex.clear();
        for (int i = 0; i < indexToEdge.size(); i++) {
            edgeToIndex.put(indexToEdge.get(i), i);
        }
        return true;
    }

    @Override
    public Set<V> getVertices() {
        return Collections.unmodifiableSet(vertexToIndex.keySet());
    }

    @Override
    public Set<Edge<V>> getEdges() {
        return Collections.unmodifiableSet(edgeToIndex.keySet());
    }

    @Override
    public Set<V> getNeighbors(V vertex) {
        Integer srcIdx = vertexToIndex.get(vertex);
        if (srcIdx == null) {
            return null;
        }

        Set<V> neighbors = new HashSet<>();
        for (int j = 0; j < incMatrixColumns.size(); j++) {
            int[] column = incMatrixColumns.get(j);
            if (srcIdx < column.length && column[srcIdx] == 1) {
                for (int i = 0; i < column.length; i++) {
                    if (column[i] == -1) {
                        neighbors.add(indexToVertex.get(i));
                        break;
                    }
                }
            }
        }
        return neighbors;
    }
}
