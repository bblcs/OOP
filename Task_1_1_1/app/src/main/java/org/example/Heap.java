package org.example;

import java.util.ArrayList;
import static java.util.Collections.swap;

/**
 * A binary min-heap.
 * 
 * @param <T> Comparable elements.
 */
public class Heap<T extends Comparable<T>> {
    private static int left(int i) {
        return 2 * i + 1;
    }

    private static int right(int i) {
        return 2 * i + 2;
    }

    private static int parent(int i) {
        return (i - 1) / 2;
    }

    /**
     * The main 'array' of the binary heap.
     */
    private ArrayList<T> data = new ArrayList<>();

    /**
     * A basic constructor.
     */
    public Heap() {
        data = new ArrayList<>();
    }

    /**
     * Makes a heap from a given ArrayList of elements.
     * 
     * @param arr array of elements that form the heap.
     */
    public Heap(ArrayList<T> arr) {
        data = new ArrayList<>(arr);
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * Inserts element e into the heap
     * whilst retaining the min-heap invariant.
     * 
     * @param e element to insert.
     */
    public void insert(T e) {
        data.add(e);
        siftUp(size() - 1);
    }

    /**
     * Extracts the minimal element of the heap
     * whilst retaining min-heap invariant.
     * 
     * @return minimal element in the heap.
     */
    public T extractMin() {
        int last = size() - 1;
        T min = data.get(0);
        swap(data, 0, last);
        data.remove(last);
        siftDown(0);
        return min;
    }

    /**
     * Returns number of elements in the heap
     * 
     * @return number of elements in the heap.
     */
    public int size() {
        return data.size();
    }

    private void siftDown(int i) {
        int smallest = i;
        int l = left(i);
        int r = right(i);

        if (l < size() && data.get(left(i)).compareTo(data.get(smallest)) < 0) {
            smallest = left(i);
        }

        if (r < size() && data.get(right(i)).compareTo(data.get(smallest)) < 0) {
            smallest = right(i);
        }

        if (smallest != i) {
            swap(data, i, smallest);
            siftDown(smallest);
        }
    }

    private void siftUp(int i) {
        if (i > 0 && data.get(i).compareTo(data.get(parent(i))) < 0) {
            swap(data, i, parent(i));
            siftUp(parent(i));
        }
    }
}
