package org.example;

class AdjListGraphTest extends GraphTest {
    @Override
    protected Graph<String> createGraph() {
        return new AdjListGraph<>();
    }
}
