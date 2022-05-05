package algorithm;

public class CountingSort {

    private static class CountingSortImplementation {

        public static void sort(int[] array) {
            // Find maximum value
            int max = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] > max) max = array[i];
            }

            // Count each element
            int[] temp = new int[max + 1];
            for (int i = 0; i < array.length; i++) {
                temp[array[i]]++;
            }

            // Sort results
            int i = 0;
            int index = 0;
            while (i < temp.length) {
                for (int j = 0; j < temp[i]; j++) {
                    array[index++] = i;
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        CountingSortImplementation.sort(new int[] {10, 1, 11, 12, 3, 1, 5, 6, 7, 8});
    }
}
