package algorithm;

public class QuickSort {

    private static class QuickSortImplementation {

        public static void sort(int[] array) {
            sort(array, 0, array.length - 1);
        }

        private static void sort(int[] array, int lo, int hi) {
            if (lo < hi) {
                int pivot = partition(array, lo, hi);
                sort(array, lo, pivot - 1);
                sort(array, pivot + 1, hi);
            }
        }

        private static int partition(int[] array, int lo, int hi) {
            int pivot = array[hi];
            int i = lo;
            for (int j = lo; j < hi; j++) {
                if (array[j] <= pivot) {
                    swap(array, i, j);
                    i++;
                }
            }
            swap(array, i, hi);
            return i;
        }

        private static void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {9, 1, 2, 4, 5, 7, 8, 6, 3};
        QuickSortImplementation.sort(array);
    }
}
