import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/*
 * Represents a directed graph
 */
public class Graph {
    int origin;
    int destination;
    int vertex_amt;
    int[] level;
    List<Edge>[] adj;

    public Graph(int v, int orig, int dest) {
        vertex_amt = v;
        level = new int[v];
        adj = new List[v];

        // initialize array list
        for (int i = 0; i < v; i++) {
            adj[i] = new ArrayList<Edge>();
        }
        origin = orig;
        destination = dest;
    }

    /*
     * Creates two new edges the original and reverse, and adds them to the
     * adjacency list.
     */
    public void insert_edge(int v, int w) {

        // for the disjoint-path problem, the capacity will always be 1.
        Edge default_edge = new Edge(w, 0, 1, adj[w].size());

        Edge rev_edge = new Edge(v, 0, 1, adj[v].size());

        adj[v].add(default_edge);
        adj[w].add(rev_edge);
    }

    /*
     * Breadth-First Search algorithm
     * returns true if level from terminal is greater than zero
     *
     */
    public boolean breadth_first_search(int source, int terminal) {

        // Start with all vertices with level = -1
        for (int i = 0; i < this.vertex_amt; i++) {
            level[i] = -1;
        }

        // Setting source level to zero
        level[source] = 0;

        LinkedList<Integer> visited = new LinkedList<Integer>();
        visited.push(source);

        ListIterator<Edge> i;
        while (visited.size() != 0) {
            int w = visited.poll();

            for (i = adj[w].listIterator(); i.hasNext();) {

            }

        }

    }

    /*
     * Given origin and destination, get disjoint paths
     */
    public void disjoint_paths() {
        ArrayList paths = new ArrayList<String>();

    }

}
