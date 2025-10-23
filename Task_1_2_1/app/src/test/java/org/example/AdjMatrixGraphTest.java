package org.example;

class AdjMatrixGraphTest extends GraphTest {
    @Override
    protected Graph<String> createGraph() {
        return new AdjMatrixGraph<>();
    }
}
