import java.util.Arrays;

class ServerEdge {
    int source, destination, weight;

    ServerEdge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class GameServerNetwork {

    public static void main(String[] args) {

        String[] servers = {
                "LS", "MM", "CS", "IS", "BS", "RS"
        };

        ServerEdge[] edges = {
                new ServerEdge(0, 1, 4),   // LS -> MM
                new ServerEdge(0, 2, 5),   // LS -> CS
                new ServerEdge(1, 3, 3),   // MM -> IS
                new ServerEdge(2, 3, 2),   // CS -> IS
                new ServerEdge(3, 4, 4),   // IS -> BS
                new ServerEdge(4, 5, -2)   // BS -> RS (negative edge)
        };

        int V = 6;
        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0; // Source = Login Server

        // Bellman-Ford Algorithm
        for (int i = 1; i < V; i++) {

            for (ServerEdge e : edges) {

                if (dist[e.source] != Integer.MAX_VALUE &&
                        dist[e.source] + e.weight < dist[e.destination]) {

                    dist[e.destination] =
                            dist[e.source] + e.weight;
                }
            }
        }

        // Negative Cycle Check
        boolean negativeCycle = false;

        for (ServerEdge e : edges) {

            if (dist[e.source] != Integer.MAX_VALUE &&
                    dist[e.source] + e.weight < dist[e.destination]) {

                negativeCycle = true;
                break;
            }
        }

        System.out.println("Online Game Server Network");
        System.out.println("--------------------------");

        if (negativeCycle) {

            System.out.println("Negative Cycle Detected!");

        } else {

            System.out.println("Shortest Communication Time:\n");

            for (int i = 0; i < V; i++) {

                System.out.println(
                        servers[i] + " : " +
                                dist[i] + " ms");
            }
        }
    }
}