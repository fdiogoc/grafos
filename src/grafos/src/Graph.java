
package grafos.src;

import java.util.*;

// https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
// https://www.geeksforgeeks.org/add-and-remove-edge-in-adjacency-list-representation-of-a-graph/
public class Graph {

    // No. of vertices
    private int V;

    // Adjacency List Represntation
    private LinkedList<Integer> adj[];

    // Constructor
    @SuppressWarnings("unchecked")
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList<Integer>();

    }

    // ADD EDGE
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);

    }

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

    void DFSUtil(int v, boolean[] visited) {
        // Mark the current node as visited and print it
        visited[v] = true;
        // System.out.print(v+" ");
        // Recur for all the vertices
        // adjacent to this vertex
        for (int x : adj[v]) {
            if (!visited[x])
                DFSUtil(x, visited);
        }

    }

    void connectedComponents() {
        // Mark all the vertices as not visited
        boolean[] visited = new boolean[V];
        int count = 0;
        for (int v = 1; v < V; ++v) {
            if (!visited[v]) {
                ++count;
                // print all reachable vertices
                // from v
                DFSUtil(v, visited);
            }

        }
        System.out.print(count);

    }

    // A recursive function that
    // uses visited[] and parent to detect
    // cycle in subgraph reachable
    // from vertex v.
    Boolean isCyclicUtil(int v, Boolean visited[], int parent) {
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
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, v))
                    return true;
            }

            // If an adjacent is visited
            // and not parent of current
            // vertex, then there is a cycle.
            else if (i != parent)
                return true;
        }
        return false;
    }

    // Returns true if the graph
    // contains a cycle, else false.
    Boolean isCyclic() {

        // Mark all the vertices as
        // not visited and not part of
        // recursion stack
        Boolean visited[] = new Boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Call the recursive helper
        // function to detect cycle in
        // different DFS trees
        for (int u = 0; u < V; u++) {

            // Don't recur for u if already visited
            if (!visited[u])
                if (isCyclicUtil(u, visited, -1)) {

                    return true;
                }
        }

        return false;
    }

    public static void main(String args[]) {
    }
}
