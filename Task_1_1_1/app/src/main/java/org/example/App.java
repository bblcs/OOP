package org.example;

import java.util.Arrays;
import java.util.PriorityQueue;

/** The main class of the program. */
public class App {
    /**
     * sorts an array using heapsort algorithm.
     *
     * @param arr - array of integers.
     */
    static void sort(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = pq.poll();
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1};
        App.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
