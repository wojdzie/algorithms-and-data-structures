package algorithm;

import datastructure.PriorityQueue;

public class HeapSort<E extends Comparable<E>> {

    public static <E> void sort(Comparable[] array) {
        PriorityQueue priorityQueue = new PriorityQueue<>(array.length);
        for (Comparable item : array) {
            priorityQueue.insert(item);
        }

        for (int i = array.length - 1; i >= 0; i--) {
            array[i] = priorityQueue.delMax();
        }
    }
}
