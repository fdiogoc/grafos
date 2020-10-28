package unifil;

import java.util.*;

/**
 * MATERIAL UTILIZZADO.
 * https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 * https://www.geeksforgeeks.org/add-and-remove-edge-in-adjacency-list-representation-of-a-graph/
 */

public class Graph {
    // No. of vertices
    private int vn;

    // Adjacency List Represntation
    private LinkedList<Integer>[] adj;

    /**
     * Constructor.
     */
    @SuppressWarnings("unchecked")
    Graph(int v) {
        vn = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList<>();
        }

    }

    /**
     * @param v
     * @param w
     */
    // ADD EDGE
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);

    }

    /**
     * @param v
     * @param w
     */
    // DELETE EDGE
    void delEdge(int v, int w) {
        // Traversing through the first vector list
        // and removing the second element from it
        for (int i = 1; i < adj[v].size(); i++) {
            if (adj[v].get(i) == w) {
                adj[v].remove(i);
                break;
            }
        }

        // Traversing through the second vector list
        // and removing the first element from it
        for (int i = 1; i < adj[w].size(); i++) {
            if (adj[w].get(i) == v) {
                adj[w].remove(i);
                break;
            }
        }

    }

    /**
     * DEEP FIRST
     * @param v
     * @param visited
     */
    void dfsUtil(int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;
        // System.out.print(v + " ");
        // Recur for all the vertices
        // adjacent to this vertex
        for (int x : adj[v]) {
            if (!visited[x]) {
                dfsUtil(x, visited);
            }

        }

    }

    /**
     * MESMO METODO COM OVERLOAD PARA VOLTAR TAMANHO UTILIZADO NO EXERCICIO 2
     *
     * @param v
     * @param visited
     * @param ans     - tamanho do atual component
     */
    int dfsUtil(int v, Boolean[] visited, int ans) {
        // Mark the current node as visited and print it
        visited[v] = true;
        // System.out.print(v + " ");
        ans++;
        // Recur for all the vertices
        // adjacent to this vertex
        for (int x : adj[v]) {
            if (!visited[x]) {
                ans = dfsUtil(x, visited, ans);
            }

        }
        return ans;

    }

    /**
     * @return int
     */
    int connectedComponents() {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[vn];
        int count = 0;
        for (int v = 1; v < vn; ++v) {
            if (!visited[v]) {
                ++count;
                // print all reachable vertices
                // from v
                dfsUtil(v, visited);
            }

        }
        return count;

    }

    /**
     * A recursive function that uses visited[] and parent to detect cycle in
     * subgraph reachable from vertex v.
     *
     * @param v
     * @param visited - visited list
     * @param parent
     * @return Boolean
     */

    Boolean isCyclicUtil(int v, Boolean[] visited, int parent) {
        // Mark the current node as visited
        visited[v] = true;
        Integer i;

        // Recur for all the vertices
        // adjacent to this vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();

            // If an adjacent is not
            // visited, then recur for that
            // adjacent
            // If an adjacent is visited
            // and not parent of current
            // vertex, then there is a cycle.
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v)) {
                    return true;
                }
            } else if (i != parent) {
                return true;
            }

        }
        return false;
    }

    /**
     * @return Boolean
     */
    // Returns true if the graph
    // contains a cycle, else false.
    Boolean isCyclic() {

        // Mark all the vertices as
        // not visited and not part of
        // recursion stack
        Boolean[] visited = new Boolean[vn];
        for (int i = 0; i < vn; i++) {
            visited[i] = false;
        }

        // Call the recursive helper
        // function to detect cycle in
        // different DFS trees
        for (int u = 0; u < vn; u++) {

            // Don't recur for u if already visited
            if (!visited[u]) {
                if (isCyclicUtil(u, visited, -1)) {

                    return true;
                }
            }

        }

        return false;
    }

    int biggerComponentSize() {
        int size = 0;
        // Mark all the vertices as
        // not visited and not part of
        // recursion stack
        Boolean[] visited = new Boolean[vn];
        for (int i = 0; i < vn; i++) {
            visited[i] = false;
        }

        for (int u = 0; u < vn; u++) {

            if (!visited[u]) {
                int s = 0;

                // DFS algorithm
                s = dfsUtil(u, visited, s);
                if (s > size) {
                    size = s;
                }
            }

        }
        return size;

    }

}
