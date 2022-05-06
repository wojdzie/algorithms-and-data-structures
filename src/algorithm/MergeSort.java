package algorithm;

public class MergeSort {

    private static class MergeSortImplementation {

        public static void sort(int[] array) {
            sort(array, new int[array.length], 0, array.length - 1);
        }

        private static void sort(int[] array, int[] temp, int lo, int hi) {
            if (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                sort(array, temp, lo, mid);
                sort(array, temp, mid + 1, hi);
                merge(array, temp, lo, hi);
            }
        }

        private static void merge(int[] array, int[] temp, int lo, int hi) {
            int leftStart = lo;
            int leftEnd = lo + (hi - lo) / 2;
            int rightStart = leftEnd + 1;
            int rightEnd = hi;
            int index = leftStart;
            // Merge elements
            while (leftStart <= leftEnd && rightStart <= rightEnd) {
                if (array[leftStart] <= array[rightStart]) {
                    temp[index] = array[leftStart];
                    leftStart++;
                } else {
                    temp[index] = array[rightStart];
                    rightStart++;
                }
                index++;
            }
            // Copy remaining elements from left array
            while (leftStart <= leftEnd) {
                temp[index] = array[leftStart];
                leftStart++;
                index++;
            }
            // Copy remaining elements from right array
            while (rightStart <= rightEnd) {
                temp[index] = array[rightStart];
                rightStart++;
                index++;
            }
            // Copy all elements to main array
            for (int i = lo; i <= hi; i++) {
                array[i] = temp[i];
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[] {9, 1, 2, 4, 5, 7, 8, 6, 3};
        MergeSort.MergeSortImplementation.sort(array);
    }
}
