package datastructure;

public class DisjointSet {

    private static class DisjointSetImplementation {

        private final int[] parents;
        private final int[] ranks;

        public DisjointSetImplementation(int capacity) {
            parents = new int[capacity];
            ranks = new int[capacity];
            for (int i = 0; i < capacity; i++) {
                parents[i] = i;
            }
        }

        public void union(int firstIndex, int secondIndex) {
            int firstParent = find(firstIndex);
            int secondParent = find(secondIndex);

            if (firstParent == secondParent) {
                return;
            }

            int firstParentRank = ranks[firstParent];
            int secondParentRank = ranks[secondParent];

            if (firstParentRank < secondParentRank) {
                parents[firstParent] = secondParent;
            } else if (secondParentRank < firstParentRank) {
                parents[secondParent] = firstParent;
            } else {
                parents[firstParent] = secondParent;
                ranks[secondParent]++;
            }

            parents[firstParent] = secondParent;
        }

        public int find(int index) {
            if (parents[index] == index) {
                return index;
            }
            int result = find(parents[index]);
            parents[index] = result;
            return result;
        }
    }

    public static void main(String[] args) {
        DisjointSetImplementation disjointSet = new DisjointSetImplementation(6);
        disjointSet.union(3, 4);
        disjointSet.union(0, 1);
        disjointSet.union(1, 3);
        disjointSet.union(2, 5);
    }
}
