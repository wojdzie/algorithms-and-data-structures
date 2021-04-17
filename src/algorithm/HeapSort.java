package algorithm;

public class HeapSort {

    /*
        Heap sort is based on heap so position 0 should be empty
     */
    public static void sort(Comparable[] array) {
        int size = array.length - 1;
        for (int i = size / 2; i >= 1; i--) {
            sink(array, i, size);
        }
        while (size > 1) {
            Comparable temp = array[1];
            array[1] = array[size];
            array[size] = temp;
            size--;

            sink(array, 1, size);
        }
    }

    private static void sink(Comparable[] array, int position, int size) {
        while (position * 2 <= size) {
            int childPosition = position * 2;
            if (childPosition < size && array[childPosition + 1].compareTo(array[childPosition]) > 0) {
                childPosition++;
            }
            if (array[position].compareTo(array[childPosition]) > 0) {
                break;
            }

            Comparable temp = array[position];
            array[position] = array[childPosition];
            array[childPosition] = temp;

           position = childPosition;
        }
    }
}
