import java.util.ArrayList;

public class Graphs {

    public static class Edge {
        int v, w;

        Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    public static void addEdge(ArrayList<Edge>[] graph, int u, int v, int w) {
        graph[u].add(new Edge(v, w));
        graph[v].add(new Edge(v, w));
    }

    public static void display(ArrayList<Edge>[] graph, int V) {

        for (int i = 0; i < V; i++) {
            System.out.print(i + "->");
            for (Edge e : graph[i]) {
                System.out.print("(" + e.v + "," + e.w + ")");
            }
            System.out.println();
        }

    }

    public static int findEdge(ArrayList<Edge>[] graph, int u, int v) {

        for (int i = 0; i < graph[u].size(); i++) {
            Edge e = graph[u].get(i);
            if (e.v == v)
                return i;
        }

        return -1;

    }

    // DFS Algorithm

    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {

        if (src == dest) {
            return true;
        }

        boolean res = false;

        visited[src] = true;
        for (Edge e : graph[src]) {
            if (!visited[e.v]) {
                res = res || hasPath(graph, e.v, dest, visited);
            }
        }

        return res;
    }

    public static int allPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited, String psf) {

        if (src == dest) {
            System.out.print(psf + dest);
            return 1;
        }

        int count = 0;
        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.v]) {
                count += allPath(graph, e.v, dest, visited, psf + src);
            }
        }

        return count;
    }

    public static void hamlitonianPathAndCycle(ArrayList<Edge>[] graph, int src, int osrc, int edgeCount,
            boolean[] visited, String psf, ArrayList<String> ans) {

        if (edgeCount == graph.length - 1) {
            psf += src;
            int idx = findEdge(graph, src, osrc);
            if (idx != -1) {
                psf += "*";
            }

            ans.add(psf);
            return;
        }

        visited[src] = true;
        for (Edge e : graph[src]) {
            if (!visited[e.v]) {
                hamlitonianPathAndCycle(graph, e.v, osrc, edgeCount + 1, visited, psf + src, ans);
            }
        }
        visited[src] = false;

    }

    // get connected components

    public static void dfs_component(ArrayList<Edge>[] graph, boolean[] visited, int src) {

        visited[src] = true;

        for (Edge e : graph[src]) {
            if (!visited[e.v]) {
                dfs_component(graph, visited, e.v);
            }
        }

    }

    public static void gcc(ArrayList<Edge>[] graph) {

        int components = 0;
        int V = graph.length;
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                components++;
                dfs_component(graph, visited, i);
            }
        }

        System.out.println(components);

    }

    public static ArrayList<Edge>[] constructGraph() {
        int V = 9;

        ArrayList<Edge> graph[] = new ArrayList[V];
        for (int i = 0; i < V; i++)
            graph[i] = new ArrayList<>();

        addEdge(graph, 0, 1, 10);
        addEdge(graph, 0, 3, 10);
        addEdge(graph, 1, 2, 10);
        addEdge(graph, 2, 3, 40);

        addEdge(graph, 2, 7, 2);
        addEdge(graph, 2, 8, 4);
        addEdge(graph, 7, 8, 3);

        // addEdge(graph, 3, 4, 2);
        addEdge(graph, 4, 5, 2);
        addEdge(graph, 4, 6, 8);
        addEdge(graph, 5, 6, 3);

        return graph;
    }

    public static void main(String[] args) {

        ArrayList<Edge>[] graph = constructGraph();
        gcc(graph);
    }
}
