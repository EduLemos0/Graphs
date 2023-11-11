import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // Make sure you pass the file path correctly
        String file_path = "./input/testing.txt";

        try (FileReader in = new FileReader(file_path)) {

            try (Scanner sc = new Scanner(in)) {

                int origin = sc.nextInt();
                int destination = sc.nextInt();
                int vertex_amt = sc.nextInt();
                int edge_amt = sc.nextInt();

                Graph graph = new Graph(vertex_amt, origin, destination);

                while (sc.hasNextInt()) {
                    int v = sc.nextInt();
                    int w = sc.nextInt();

                    graph.insert_edge(v, w);
                }

                graph.get_disjoint_paths();
            }
        }

    }
}
