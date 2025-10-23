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
        Graph<String> graph = new AdjListGraph<>();

        assertEquals(graph, graph);
        assertNotEquals(graph, new String());
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
