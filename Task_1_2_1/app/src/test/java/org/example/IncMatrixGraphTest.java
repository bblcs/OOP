package org.example;

class IncMatrixGraphTest extends GraphTest {
    @Override
    protected Graph<String> createGraph() {
        return new IncMatrixGraph<>();
    }
}
