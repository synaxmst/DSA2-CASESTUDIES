
import java.util.*;

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

public class SocialMediaNetwork {

    static int[] parent;

    static int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px == py)
            return false;

        parent[px] = py;
        return true;
    }

    public static void main(String[] args) {

        String[] community = {
            "A", "B", "C", "D", "E", "F"
        };

        Edge[] edges = {
            new Edge(0, 1, 4),
            new Edge(0, 2, 3),
            new Edge(1, 2, 2),
            new Edge(1, 3, 5),
            new Edge(2, 3, 7),
            new Edge(2, 4, 8),
            new Edge(3, 5, 6),
            new Edge(4, 5, 1)
        };

        Arrays.sort(edges, (a, b) -> a.weight - b.weight);

        parent = new int[6];
        for (int i = 0; i < 6; i++)
            parent[i] = i;

        int totalCost = 0;

        System.out.println("Social Media Friend Network");
        System.out.println("---------------------------");
        System.out.println("Selected Connections:\n");

        for (Edge e : edges) {

            if (union(e.src, e.dest)) {

                System.out.println(
                    "Community " + community[e.src] +
                    " - Community " + community[e.dest] +
                    " : " + e.weight
                );

                totalCost += e.weight;
            }
        }

        System.out.println("\nTotal Network Cost = " + totalCost);
    }
}
