package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** The main class of the program. */
public class App {
    /**
     * sorts an array using heapsort algorithm.
     *
     * @param arr - array of integers.
     */
    static void sort(int[] arr) {
        List<Integer> arrList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Heap<Integer> pq = new Heap<>(arrList);

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
