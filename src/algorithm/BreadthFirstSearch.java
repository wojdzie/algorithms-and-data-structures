package algorithm;


import java.util.LinkedList;

public class BreadthFirstSearch {

    private static class BreadthFirstSearchImplementation {

        private final int vertices;
        private final LinkedList<Integer>[] adj;

        public BreadthFirstSearchImplementation(int vertices) {
            this.vertices = vertices;
            adj = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
        }

        public void BFS(int start) {
            boolean visited[] = new boolean[vertices];
            LinkedList<Integer> queue = new LinkedList<>();

            visited[start] = true;
            queue.add(start);

            while (!queue.isEmpty()) {
                Integer current = queue.poll();
                System.out.println(current);
                for (Integer i : adj[current]) {
                    if (!visited[i]) {
                        visited[i] = true;
                        queue.add(i);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        BreadthFirstSearchImplementation bfs = new BreadthFirstSearchImplementation(4);
        bfs.addEdge(0, 1);
        bfs.addEdge(0, 2);
        bfs.addEdge(1, 2);
        bfs.addEdge(2, 0);
        bfs.addEdge(2, 3);
        bfs.addEdge(3, 3);

        System.out.println("Following is Breadth First Traversal (starting from vertex 2)");
        bfs.BFS(2);
    }
}