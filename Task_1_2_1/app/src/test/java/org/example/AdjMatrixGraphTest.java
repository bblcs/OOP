package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AdjMatrixGraphTest extends GraphTest {
    @Override
    protected Graph<String> createGraph() {
        return new AdjMatrixGraph<>();
    }

    @Test
    void testResize() {
        for (int i = 0; i < 99; i++) {
            graph.addEdge(String.valueOf(i), String.valueOf(i + 1));
            ;
        }

        for (int i = 0; i < 99; i++) {
            assertTrue(graph.removeEdge(String.valueOf(i), String.valueOf(i + 1)));
        }

        assertTrue(graph.getEdges().isEmpty());
    }

}
