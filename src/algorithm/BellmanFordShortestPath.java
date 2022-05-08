package algorithm;

public class BellmanFordShortestPath {

    private static class BellmanFordShortestPathImplementation {

        private final int V;
        private final int E;
        private final Edge[] edges;

        private BellmanFordShortestPathImplementation(int V, int E, Edge[] edges) {
            this.V = V;
            this.E = E;
            this.edges = edges;
        }

        private static class Edge {

            private final int src;
            private final int dest;
            private final int weight;

            private Edge(int src, int dest, int weight) {
                this.src = src;
                this.dest = dest;
                this.weight = weight;
            }
        }

        public void shortestPath(int src) {
            int[] dist = new int[V];
            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
            }

            dist[src] = 0;

            for (int i = 1; i < V; i++) {
                for (int j = 0; j < E; j++) {
                    int u = edges[j].src;
                    int v = edges[j].dest;
                    int weight = edges[j].weight;
                    if (dist[u] != Integer.MAX_VALUE
                    && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                    }
                }
            }

            for (int j = 0; j < E; j++) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    System.out.println("Negative weight cycle!");
                    return;
                }
            }
            print(dist, V);
        }

        void print(int[] dist, int V)
        {
            System.out.println("Vertex | Distance from Source");
            for (int i = 0; i < V; ++i)
                System.out.println(i + " | " + dist[i]);
        }
    }

    public static void main(String[] args) {
        BellmanFordShortestPathImplementation.Edge[] edges = {
          new BellmanFordShortestPathImplementation.Edge(0, 1, -1),
          new BellmanFordShortestPathImplementation.Edge(0, 2, 4),
          new BellmanFordShortestPathImplementation.Edge(1, 2, 3),
          new BellmanFordShortestPathImplementation.Edge(1, 3, 2),
          new BellmanFordShortestPathImplementation.Edge(1, 4, 2),
          new BellmanFordShortestPathImplementation.Edge(3, 2, 5),
          new BellmanFordShortestPathImplementation.Edge(3, 1, 1),
          new BellmanFordShortestPathImplementation.Edge(4, 3, -3)};
        BellmanFordShortestPathImplementation bellmanFord = new BellmanFordShortestPathImplementation(5, 8, edges);
        bellmanFord.shortestPath(0);
    }
}
