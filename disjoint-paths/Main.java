import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        // String file_path = "";

        // try (Scanner scanner = new Scanner(System.in)) {
        // System.out.println("Enter the file path: ");
        // file_path = scanner.nextLine();
        // }
        // file_path = "./input/" + file_path + ".txt";

        for (int i = 0; i < 11; i++) {
            long startTime = System.currentTimeMillis();

            try (FileReader in = new FileReader("./input/cicle_100.txt")) {

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

            long endTIme = System.currentTimeMillis();

            System.out.println("Took: " + (endTIme - startTime) + "ms");
        }
    }

}
