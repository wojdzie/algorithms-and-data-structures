package algorithm;

public class DijkstraShortestPath {

    private static class DijkstraShortestPathImplementation {

        private final int vertices;

        public DijkstraShortestPathImplementation(int vertices) {
            this.vertices = vertices;
        }

        private Integer minDistance(int[] distance, boolean[] shortestPathTree) {
            int min = Integer.MAX_VALUE;
            Integer min_index = null;
            for (int v = 0; v < vertices; v++) {
                if (!shortestPathTree[v] && distance[v] <= min) {
                    min = distance[v];
                    min_index = v;
                }
            }
            return min_index;
        }

        public void shortestPath(int[][] graph, int src) {
            int[] dist = new int[vertices];
            boolean[] shortestPathTree = new boolean[vertices];

            for (int i = 0; i < vertices; i++) {
                dist[i] = Integer.MAX_VALUE;
            }

            dist[src] = 0;

            for (int count = 0; count < vertices - 1; count++) {
                int u = minDistance(dist, shortestPathTree);
                shortestPathTree[u] = true;


                for (int v = 0; v < vertices; v++) {
                    if (!shortestPathTree[v]
                            && graph[u][v] != 0
                            && dist[u] != Integer.MAX_VALUE
                            && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
            print(dist);
        }

        void print(int[] dist) {
            System.out.println("Vertex | Distance from Source");
            for (int i = 0; i < vertices; i++)
                System.out.println(i + " | " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 14, 10, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}};
        DijkstraShortestPathImplementation dijkstra = new DijkstraShortestPathImplementation(graph.length);
        dijkstra.shortestPath(graph, 0);
    }
}