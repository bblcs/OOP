package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An adjacency matrix graph representation.
 *
 * @param <V> the type of the vertices.
 */
public class AdjMatrixGraph<V> extends AbstractGraph<V> {
    private Map<V, Integer> vertexToIndex;
    private List<V> indexToVertex;
    private boolean[][] adjMatrix;
    private int vertexCount;

	private static final int DEFAULT_CAPACITY = 16;

	public AdjMatrixGraph() {
		this(DEFAULT_CAPACITY);
	}

	public AdjMatrixGraph(int initialCapacity) {
		int capacity = Math.max(1, initialCapacity);
		this.vertexToIndex = new HashMap<>();
		this.indexToVertex = new ArrayList<>();
		this.adjMatrix = new boolean[capacity][capacity];
		this.vertexCount = 0;
	}

	private void resize() {
		int oldCapacity = adjMatrix.length;
		int newCapacity = oldCapacity * 2;
		boolean[][] newMatrix = new boolean[newCapacity][newCapacity];

		for (int i = 0; i < oldCapacity; i++) {
			System.arraycopy(adjMatrix[i], 0, newMatrix[i], 0, oldCapacity);
		}

		adjMatrix = newMatrix;
	}

	@Override
	public void addVertex(V vertex) {
		if (!vertexToIndex.containsKey(vertex)) {
			if (vertexCount == adjMatrix.length) {
				resize();
			}
			int newIndex = vertexCount++;
			vertexToIndex.put(vertex, newIndex);
			indexToVertex.add(vertex);
		}
	}

	@Override
	public boolean removeVertex(V vertex) {
		Integer indexToRemove = vertexToIndex.get(vertex);
		if (indexToRemove == null) {
			return false;
		}

		int lastIndex = --vertexCount;
		V lastVertex = indexToVertex.get(lastIndex);

		if (indexToRemove < lastIndex) {
			System.arraycopy(adjMatrix[lastIndex], 0, adjMatrix[indexToRemove], 0, vertexCount + 1);

			for (int i = 0; i <= lastIndex; i++) {
				adjMatrix[i][indexToRemove] = adjMatrix[i][lastIndex];
			}

			indexToVertex.set(indexToRemove, lastVertex);
			vertexToIndex.put(lastVertex, indexToRemove);
		}

		vertexToIndex.remove(vertex);
		indexToVertex.remove(lastIndex);

		return true;
	}

	@Override
	public void addEdge(V src, V dest) {
		addVertex(src);
		addVertex(dest);
		int srcIdx = vertexToIndex.get(src);
		int destIdx = vertexToIndex.get(dest);
		adjMatrix[srcIdx][destIdx] = true;
	}

	@Override
	public boolean removeEdge(V src, V dest) {
		Integer srcIdx = vertexToIndex.get(src);
		Integer destIdx = vertexToIndex.get(dest);
		if (srcIdx == null || destIdx == null || !adjMatrix[srcIdx][destIdx]) {
			return false;
		}
		adjMatrix[srcIdx][destIdx] = false;
		return true;
	}

	@Override
	public Set<V> getVertices() {
		return Collections.unmodifiableSet(vertexToIndex.keySet());
	}

	@Override
	public Set<Edge<V>> getEdges() {
		Set<Edge<V>> edges = new HashSet<>();
		for (int i = 0; i < vertexCount; i++) {
			for (int j = 0; j < vertexCount; j++) {
				if (adjMatrix[i][j]) {
					edges.add(new Edge<>(indexToVertex.get(i), indexToVertex.get(j)));
				}
			}
		}
		return Collections.unmodifiableSet(edges);
	}

	@Override
	public Set<V> getNeighbors(V vertex) {
		Integer srcIndex = vertexToIndex.get(vertex);
		if (srcIndex == null) {
			return null;
		}
		Set<V> neighbors = new HashSet<>();
		for (int j = 0; j < vertexCount; j++) {
			if (adjMatrix[srcIndex][j]) {
				neighbors.add(indexToVertex.get(j));
			}
		}
		return neighbors;
	}
}
