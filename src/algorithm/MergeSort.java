package algorithm;

public class MergeSort {


    public static void sort(Comparable[] array) {
        sort(array, 0, array.length - 1);
    }

    private static void sort(Comparable[] array, int lo, int hi) {
        if (lo < hi) {
            int mid = (lo + hi) / 2;
            sort(array, lo, mid);
            sort(array, mid + 1, hi);
            merge(array, lo, mid, hi);
        }
    }

    private static void merge(Comparable[] array, int lo, int mid, int hi) {
        Comparable[] temp = new Comparable[array.length];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = array[i];
        }

        int i = lo;
        int j = mid + 1;
        int current = lo;
        while (i <= mid && j <= hi) {
            if (temp[i].compareTo(temp[j]) < 0) {
                array[current++] = temp[i++];
            } else {
                array[current++] = temp[j++];
            }
        }
        if (i <= mid && j > hi) {
            while (i <= mid) {
                array[current++] = temp[i++];
            }
        }

        if (i > mid && j <= hi) {
            while (j <= hi) {
                array[current++] = temp[j++];
            }
        }
    }
}
