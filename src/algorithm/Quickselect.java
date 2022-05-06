package algorithm;

public class Quickselect {

    private static class QuickselectImplementation {

        public static Integer kthSmallestElement(int[] array, int lo, int hi, int k) {
            int pivot = partition(array, lo, hi);
            if (k - 1 < pivot) {
                return kthSmallestElement(array, lo, pivot - 1, k);
            } else if (k - 1 > pivot) {
                return kthSmallestElement(array, pivot + 1, hi, k);
            } else {
                return array[pivot];
            }
        }

        private static int partition(int[] array, int lo, int hi) {
            int pivot = array[hi];
            int i = lo;
            for (int j = lo; j < hi; j++) {
                if (array[j] <= pivot) {
                    swap(array, j, i);
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
        int[] array = new int[] { 10, 4, 5, 8, 6, 11, 26 };
        QuickselectImplementation.kthSmallestElement(array, 0, array.length - 1, 3);
    }
}
