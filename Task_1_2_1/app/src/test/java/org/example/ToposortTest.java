package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ToposortTest {
    @Test
    void testTwo() {
        GraphAlgorithm<Integer, List<Integer>> toposort = new Toposort<>();

        AdjListGraph<Integer> graph = new AdjListGraph<>();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        List<Integer> expected = new LinkedList<>();
        expected.add(1);
        expected.add(2);
        expected.add(3);

        List<Integer> actual = toposort.execute(graph);

        assertEquals(expected, actual);

    }
}
