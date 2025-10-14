package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AdjListGraphTest {

    private Graph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjListGraph<>();
    }

    @Test
    void testAddAndGetVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertEquals(Set.of("A", "B"), graph.getVertices());
        graph.addVertex("A");
        assertEquals(2, graph.getVertices().size());
    }

    @Test
    void testAddAndGetEdges() {
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        Set<Edge<String>> expectedEdges = Set.of(new Edge<>("A", "B"), new Edge<>("B", "C"));
        assertEquals(expectedEdges, graph.getEdges());
        assertEquals(Set.of("A", "B", "C"), graph.getVertices());
    }

    @Test
    void testGetNeighbors() {
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addVertex("D");
        assertEquals(Set.of("B", "C"), graph.getNeighbors("A"));
        assertTrue(graph.getNeighbors("B").isEmpty());
        assertNull(graph.getNeighbors("X"));
    }

    @Test
    void testRemoveEdge() {
        graph.addEdge("A", "B");
        assertTrue(graph.removeEdge("A", "B"));
        assertFalse(graph.getEdges().contains(new Edge<>("A", "B")));
        assertFalse(graph.removeEdge("A", "C"));
    }

    @Test
    void testRemoveVertex() {
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");

        assertTrue(graph.removeVertex("B"));
        assertFalse(graph.getVertices().contains("B"));
        assertEquals(Set.of("A", "C"), graph.getVertices());
        assertEquals(Set.of(new Edge<>("C", "A")), graph.getEdges());
        assertFalse(graph.removeVertex("X"));
    }
}
