package algorithm;

public class InsertionSort {

    public static void sort(Comparable[] array) {
        for (int i = 1; i < array.length; i++) {
            Comparable current = array[i];
            int j = i;
            while (j > 0 && array[j - 1].compareTo(current) > 0) {
                    array[j] = array[j - 1];
                    j--;
            }
            array[j] = current;
        }
    }
}
