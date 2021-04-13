package algorithm;

public class ShellSort {

    public static void sort(Comparable[] array) {
        int h = 1;
        while (h < array.length / 3) {
            h = 3 * h + 1;
        }
        while (h >= 1) {
            for (int i = h; i < array.length; i++) {
                Comparable current = array[i];
                int j = i;
                while (j >= h && array[j - h].compareTo(current) > 0) {
                    array[j] = array[j - h];
                    j -= h;
                }
                array[j] = current;
            }
            h /= 3;
        }
    }
}
