public class Edge {

    int dest; // vertex that this edge is directed to
    int flow; // current flow
    int cap; // maximum capacity
    int reverse; // index of reverse edge in list

    public Edge(int dest, int flow, int cap, int rev) {
        this.dest = dest;
        this.flow = flow;
        this.cap = cap;
        this.reverse = rev;
    };

}
