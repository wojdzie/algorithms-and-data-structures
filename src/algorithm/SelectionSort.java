package algorithm;

public class SelectionSort {

    public static void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min].compareTo(array[j]) > 0) {
                    min = j;
                }
            }
            Comparable temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }
}
