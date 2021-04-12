package algorithm;

public class BubbleSort {

    public static void sort(Comparable[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length; j++) {
                if (array[j - 1].compareTo(array[j]) > 0) {
                    Comparable temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
