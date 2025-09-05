package org.example;

import java.util.Arrays;

/** The main class of the program. */
public class App {
    /**
     * sorts an array using heapsort algorithm.
     *
     * @param arr - array of integers.
     */
    static void sort(int[] arr) {
        Heap<Integer> pq = new Heap<>();
        for (int i = 0; i < arr.length; i++) {
            pq.insert(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = pq.extractMin();
        }
    }

    /**
     * Sorts and prints an example array of integers.
     *
     * @param args commandline arguments, not used.
     */
    public static void main(String[] args) {
        int[] arr = { 5, 4, 3, 2, 1 };
        App.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
