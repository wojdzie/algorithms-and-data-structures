package algorithm;

public class BinarySearch {

    private static class BinarySearchImplementation {

        public static Integer search(int[] sortedArray, int searchedValue) {
            return search(sortedArray, searchedValue, 0, sortedArray.length - 1);
        }

        private static Integer search(int[] sortedArray, int searchedValue, int lo, int hi) {
            int mid = lo + (hi - lo) / 2;

            if (lo <= hi) {
                if (sortedArray[mid] == searchedValue) {
                    return mid;

                } else if (searchedValue < sortedArray[mid]) {
                    return search(sortedArray, searchedValue, lo, mid - 1);

                } else {
                    return search(sortedArray, searchedValue, mid + 1, hi);
                }
            } else {
                return null;
            }
        }
    }

    public static void main(String[] args) {
        int[] sortedArray = {0, 1, 1, 3, 7, 11, 11, 23, 126, 350, 355};
        BinarySearchImplementation.search(sortedArray, 23);
        BinarySearchImplementation.search(sortedArray, 11);
        BinarySearchImplementation.search(sortedArray, 0);
        BinarySearchImplementation.search(sortedArray, -1);
        BinarySearchImplementation.search(sortedArray, 360);
    }
}
