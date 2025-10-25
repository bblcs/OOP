package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * AbstractGraph abstract class implementing Graph for utility purposes.
 *
 * @param <V> vertexae type.
 */
public abstract class AbstractGraph<V> implements Graph<V> {
    /**
     * Creates a graph from a text file.
     *
     * @param filePath     Path to the graph file.
     * @param graphFactory Supplier for a graph.
     * @param vertexParser Function that converts String into V
     * @param <V>          Type of the vertices.
     * @return A new graph described in the filePath file.
     * @throws IOException              If can't read the file.
     * @throws IllegalArgumentException If file does not follow the format.
     */
    public static <V> Graph<V> fromFile(
            String filePath,
            Supplier<Graph<V>> graphFactory,
            Function<String, V> vertexParser) throws IOException {

        Graph<V> graph = graphFactory.get();

        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                String trimmedLine = line.trim();

                if (trimmedLine.isEmpty()) {
                    return;
                }

                if (trimmedLine.contains("->")) {
                    String[] parts = trimmedLine.split("->", 2);
                    if (parts.length != 2) {
                        throw new IllegalArgumentException("Malformed edge line: " + line);
                    }

                    String sourceStr = parts[0].trim();
                    String destStr = parts[1].trim();

                    if (sourceStr.isEmpty() || destStr.isEmpty()) {
                        throw new IllegalArgumentException("Empty vertex name: " + line);
                    }

                    V source = vertexParser.apply(sourceStr);
                    V destination = vertexParser.apply(destStr);
                    graph.addEdge(source, destination);

                } else {
                    V vertex = vertexParser.apply(trimmedLine);
                    graph.addVertex(vertex);
                }
            });
        }

        return graph;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Graph<?> that)) {
            return false;
        }

        return Objects.equals(this.getVertices(), that.getVertices())
                && Objects.equals(this.getEdges(), that.getEdges());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVertices(), getEdges());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph: ").append(this.getClass().getSimpleName()).append("\n");
        sb.append("Vertices: ").append(getVertices()).append("\n");
        sb.append("Edges: {\n");
        for (Edge<V> edge : getEdges()) {
            sb.append("  ").append(edge.src()).append(" -> ").append(edge.dest()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
