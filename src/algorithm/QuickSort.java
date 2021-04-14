package algorithm;

public class QuickSort {

    public static void sort(Comparable[] array) {
        sort(array, 0, array.length - 1);
    }

    public static void sort(Comparable[] array, int lo, int hi) {
        if (lo < hi) {
            int pivot = partition(array, lo, hi);
            sort(array, lo, pivot - 1);
            sort(array, pivot + 1, hi);
        }
    }

    private static int partition(Comparable[] array, int lo, int hi) {
        int pivot = (hi + lo) / 2;
        Comparable pivotValue = array[pivot];

        int fromLeft = lo;
        int fromRight = hi;

        while (fromLeft < fromRight) {
            while (fromLeft < hi && array[fromLeft].compareTo(pivotValue) < 0) {
                fromLeft++;
            }
            while (fromRight > lo && array[fromRight].compareTo(pivotValue) > 0) {
                fromRight--;
            }
            if (fromLeft >= fromRight || array[fromLeft].compareTo(array[fromRight]) == 0) {
                break;
            }

            Comparable temp = array[fromLeft];
            array[fromLeft] = array[fromRight];
            array[fromRight] = temp;
        }

        return fromLeft;
    }
}
