package org.example;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class App {
    public static void main(String[] args) {
        Graph<Integer> graph = new AdjListGraph<>();

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(1, 6);
        graph.addEdge(7, 3);
        graph.addEdge(7, 8);
        graph.addEdge(8, 4);

        graph.addVertex(9);

        System.out.println(graph);

        GraphAlgorithm<Integer, List<Integer>> topoSortAlgorithm = new Toposort<>();

        List<Integer> sortedVertices = topoSortAlgorithm.execute(graph);
        System.out.println("toposort:");
        System.out.println(sortedVertices);

        System.out.println("reading from a file:");

        try {
            Graph<String> stringGraph = AbstractGraph.fromFile(
                    "testgraph.txt",
                    AdjMatrixGraph::new,
                    Function.identity()
            );

            System.out.println(stringGraph);
        } catch (IOException e) {
            System.err.println("Error reading graph file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error parsing graph file: " + e.getMessage());
        }

    }
}
