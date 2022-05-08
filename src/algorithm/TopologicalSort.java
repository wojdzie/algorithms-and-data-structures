package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

    private static class TopologicalSortImplementation {

        private final int vertices;
        private final List<List<Integer>> adj;

        private TopologicalSortImplementation(int vertices) {
            this.vertices = vertices;
            this.adj = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                adj.add(new ArrayList<>());
            }
        }

        private void addEdge(int v, int w) {
            adj.get(v).add(w);
        }

        private void topologicalSortUtil(int v, boolean[] visited, Stack<Integer> stack) {
            visited[v] = true;
            for (Integer vertex : adj.get(v)) {
                if (!visited[vertex]) {
                    topologicalSortUtil(vertex, visited, stack);
                }
            }
            stack.push(v);
        }

        public void topologicalSort() {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[vertices];

            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    topologicalSortUtil(i, visited, stack);
                }
            }

            while (!stack.empty()) {
                System.out.print(stack.pop() + " ");
            }
        }
    }

    public static void main(String[] args) {
        TopologicalSortImplementation topologicalSort = new TopologicalSortImplementation(6);
        topologicalSort.addEdge(5, 2);
        topologicalSort.addEdge(5, 0);
        topologicalSort.addEdge(4, 0);
        topologicalSort.addEdge(4, 1);
        topologicalSort.addEdge(2, 3);
        topologicalSort.addEdge(3, 1);
        topologicalSort.topologicalSort();

    }
}
