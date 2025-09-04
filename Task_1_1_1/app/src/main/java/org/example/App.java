package org.example;

import java.util.PriorityQueue;

public class App {
    /**
     * sorts an array using heapsort algorithm
     *
     * @param arr - array of integers
     */
    static void sort(int arr[]) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = pq.poll();
        }
    }

    static void printArr(int arr[]) {
        boolean first = true;
        for (int i = 0; i < arr.length; i++) {
            if (!first) {
                System.out.print(" ");
            } else {
                first = false;
            }
            System.out.print(arr[i]);
        }
    }

    public static void main(String[] args) {
        int arr[] = {5, 4, 3, 2, 1};
        App.sort(arr);
        App.printArr(arr);
    }
}
