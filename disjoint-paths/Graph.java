import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/*
 * Represents a directed graph
 */
public class Graph {
    int origin;
    int destination;
    int vertex_amt;
    int[] level;
    List<Edge>[] adj;
    Set<Edge> visitedEdges = new HashSet<>();

    public Graph(int v, int orig, int dest) {
        vertex_amt = v;
        level = new int[v + 1];
        adj = new List[v + 1];

        // initialize array list
        for (int i = 1; i <= v; i++) {
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

        adj[v].add(default_edge);
    }

    /*
     * Breadth-First Search algorithm
     * returns true if level from terminal is greater than zero
     *
     */
    public boolean breadth_first_search(int source, int terminal) {
        level[0] = 0;

        // Start with all vertices with level = -1
        for (int i = 1; i <= this.vertex_amt; i++) {
            level[i] = -1;
        }

        // Setting source level to zero
        level[source] = 0;

        // creating a queue for visited vertices
        LinkedList<Integer> visited = new LinkedList<Integer>();
        visited.add(source);

        ListIterator<Edge> i;
        while (visited.size() != 0) {
            int v = visited.poll();

            for (i = adj[v].listIterator(); i.hasNext();) {
                Edge curr = i.next();

                if (level[curr.dest] < 0 && curr.flow < curr.cap) {
                    level[curr.dest] = level[v] + 1;
                    visited.add(curr.dest);
                }
            }

        }
        return level[terminal] < 0 ? false : true;
    }

    /*
     * Given origin and destination, get disjoint paths
     */
    public void get_disjoint_paths() {
        List<List<Edge>> disjoint_paths = new ArrayList<>();

        while (breadth_first_search(origin, destination)) {
            List<Edge> path = new ArrayList<>();
            int currentVertex = origin;

            while (currentVertex != destination) {
                Edge currentEdge = find_next_edge(currentVertex);

                if (currentEdge == null) {
                    // No valid edge found, remove the last added edge
                    if (!path.isEmpty()) {
                        path.remove(path.size() - 1);
                        currentVertex = path.isEmpty() ? origin : path.get(path.size() - 1).dest;
                    } else {
                        // Break if no edge in the path
                        break;
                    }
                } else {
                    // Check if the edge is already visited
                    if (!visitedEdges.contains(currentEdge)) {
                        visitedEdges.add(currentEdge);
                        path.add(currentEdge);
                        currentVertex = currentEdge.dest;
                    }
                }
            }

            // Update residual capacities on the graph
            augment_path(path);

            if (currentVertex == destination) {
                // Path reached the destination, add it to the list
                disjoint_paths.add(path);
            }

            // Reset visited flag for the next BFS iteration
            reset_visited_flags();
        }

        System.out.println("-----------------------Disjoint Paths-----------------------\n\n");
        System.out.println(disjoint_paths.size() + " Paths from " + origin + " to " + destination + "\n");
        for (List<Edge> path : disjoint_paths) {
            System.out.print(origin + " -");

            for (Edge edge : path) {
                System.out.print("-" + edge.dest + "");
            }
            System.out.println("\n");
        }

    }

    /*
     * finds next edge on the augmenting path based on the levels.
     */
    private Edge find_next_edge(int v) {
        ListIterator<Edge> iterator = adj[v].listIterator();
        while (iterator.hasNext()) {
            Edge edge = iterator.next();
            if (level[edge.dest] == level[v] + 1 && edge.flow < edge.cap && !visitedEdges.contains(edge)) {
                return edge;
            }
        }
        // No valid edge found
        return null;
    }

    /*
     * Reset the visited flag for all edges in the graph
     */
    /*
     * Reset the visited flag for all edges in the graph
     */
    private void reset_visited_flags() {
        visitedEdges.clear(); // Clear the set to reset visited flags
    }

    /*
     * augments the flow along the given path
     */
    private void augment_path(List<Edge> path) {
        for (Edge edge : path) {
            edge.flow = 1;
        }
    }

}
