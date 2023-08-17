package DigraphRepresentation;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Digraph {
    public static void main(String[] args) throws IOException {

        String fileName = "graph-test-100.txt"; // Change to "insert file name"

        Scanner sc = new Scanner(new File(fileName));

        // ---Variables---
        int vertexAmt = sc.nextInt();
        int arcsAmt = sc.nextInt();
        int origin[] = new int[arcsAmt + 1];
        int destination[] = new int[arcsAmt + 1];

        // ---Fill origin & destination---
        int it = 1; // Always ignore position 1
        while (sc.hasNextInt()) {
            origin[it] = sc.nextInt();
            destination[it++] = sc.nextInt();
        }
        sc.close();

        // ---Foward-star---
        forwardStar(vertexAmt, arcsAmt, origin, destination);
    }

    public static void forwardStar(int vertexAmt, int arcsAmt, int origin[], int destination[]) {

        // ---Create arc_dest & pointer arrays---
        int arc_dest[] = destination;
        int pointer[] = new int[vertexAmt + 2];

        // ---Fill pointer array---
        int pos = 1;
        while (pos <= pointer.length) {
            if (pos == 1) {
                pointer[pos] = 1;
            } else if (pos < pointer.length) {
                pointer[pos] = getFirstInstance(pos, origin);
            } else if (pos == pointer.length) {
                pointer[pos - 1] = (arcsAmt + 1);
            }
            pos++;
        }

        // ---Get vertex with highest degree and it's sucessors---
        getVertex(pointer, origin, arc_dest);
    }

    /* This method prints the highest degree and to which vertex it belongs */
    public static void getVertex(int pointer[], int origin[], int destination[]) {
        int highestDegree = 0;
        int vertex = 0;

        for (int i = 1; i < pointer.length; i++) {
            if (i + 1 < pointer.length) {
                int curr = pointer[i];
                int next = pointer[i + 1];
                int diff = (next - curr);

                if (diff > highestDegree) {
                    vertex = i;
                    highestDegree = diff;
                }
            }
        }

        // ---Based on the vertex, get it's children---
        int sucessors[] = new int[highestDegree];
        int pos = pointer[vertex];

        for (int i = 0; i < highestDegree; i++) {
            sucessors[i] = destination[pos++];
        }

        System.out.println("------Forward-Star------");
        System.out.println("Vertice: " + vertex);
        System.out.println("Grau de saida: " + highestDegree);
        for (int s : sucessors) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("------------------------");
    }

    /*
     * This function returns the position in which the @@target parameter is first
     * found.
     */
    public static int getFirstInstance(int target, int origin[]) {
        int iterator = 1; // Always skip position 0.
        int value = 0;

        while (iterator < origin.length) {
            if (origin[iterator] == target) {
                value = iterator;
                iterator = origin.length; // break operation
            }
            iterator++;
        }
        return value;
    }

}
