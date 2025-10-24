package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class AbstractGraphTest {
    @Test
    void testEmptyFile() {
        try {
            Graph<String> actual = AbstractGraph.fromFile("testempty.txt", AdjListGraph::new, Function.identity());
            Graph<String> expected = new AdjListGraph<>();
            assertEquals(expected, actual);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testTestFile() {
        try {
            Graph<String> actual = AbstractGraph.fromFile("testgraph.txt", AdjListGraph::new, Function.identity());
            Graph<String> expected = new AdjListGraph<>();
            expected.addEdge("A", "B");
            expected.addEdge("B", "C");
            expected.addEdge("B", "D");
            expected.addEdge("C", "A");
            expected.addVertex("E");

            assertEquals(expected, actual);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    void testNicheEquals() {
        Graph<String> g1 = new AdjListGraph<>();
        Graph<String> g2 = new AdjMatrixGraph<>();
        Graph<String> g3 = new IncMatrixGraph<>();

        assertEquals(g1, g1);
        assertEquals(g1, g2);
        assertEquals(g1, g3);
        assertEquals(g2, g3);

        assertNotEquals(g1, new String());
    }

    @Test
    void testAdequateEquals() {
        Graph<String> g1 = new AdjListGraph<>();
        g1.addEdge("A", "B");
        g1.addEdge("B", "C");
        g1.addEdge("B", "D");
        g1.addEdge("C", "A");
        g1.addVertex("E");
        Graph<String> g2 = new AdjMatrixGraph<>();
        g2.addEdge("A", "B");
        g2.addEdge("B", "C");
        g2.addEdge("B", "D");
        g2.addEdge("C", "A");
        g2.addVertex("E");
        Graph<String> g3 = new IncMatrixGraph<>();
        g3.addEdge("A", "B");
        g3.addEdge("B", "C");
        g3.addEdge("B", "D");
        g3.addEdge("C", "A");
        g3.addVertex("E");

        assertEquals(g1, g2);
        assertEquals(g1, g3);
        assertEquals(g2, g3);

    }

    @Test
    void testToString() {
        Graph<String> graph = new AdjListGraph<>();
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "A");
        graph.addVertex("E");

        StringBuilder sb = new StringBuilder();
        sb.append("Graph: AdjListGraph\n");
        sb.append("Vertices: [A, B, C, D, E]\n");
        sb.append("Edges: {\n");
        sb.append("  A -> B\n");
        sb.append("  B -> C\n");
        sb.append("  B -> D\n");
        sb.append("  C -> A\n");
        sb.append("}");

        assertEquals(sb.toString(), graph.toString());

    }
}
