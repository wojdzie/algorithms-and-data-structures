package algorithm;

import java.util.LinkedList;

public class DepthFirstSearch {

    private static class DepthFirstSearchImplementation {

        private final int vertices;
        private final LinkedList<Integer>[] adj;

        private DepthFirstSearchImplementation(int vertices) {
            this.vertices = vertices;
            adj = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
        }

        public void DFS() {
            boolean visited[] = new boolean[vertices];
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    DFS(i, visited);
                }
            }
        }

        private void DFS(int start, boolean[] visited) {
            visited[start] = true;
            System.out.println(start);

            for (Integer i : adj[start]) {
                if (!visited[i]) {
                    DFS(i, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        DepthFirstSearchImplementation dfs = new DepthFirstSearchImplementation(13);

        dfs.addEdge(1, 2);
        dfs.addEdge(1, 7);
        dfs.addEdge(1, 8);
        dfs.addEdge(2, 3);
        dfs.addEdge(2, 6);
        dfs.addEdge(3, 4);
        dfs.addEdge(3, 5);
        dfs.addEdge(8, 9);
        dfs.addEdge(8, 12);
        dfs.addEdge(9, 10);
        dfs.addEdge(9, 11);

        System.out.println("Following is Depth First Traversal");
        dfs.DFS();
    }
}