package DigraphRepresentation;

/*
 * Eduardo Lemos Paschoalini.
 * @autor EduLemos0 on GitHub.
 * It takes a while to run the 50.000 one.
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Digraph {
    public static void main(String[] args) throws IOException {

        String fileName = "INSERT FILE NAME HERE"; // ...

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
        // ---Reverse-star---
        reverseStar(vertexAmt, arcsAmt, origin, destination);
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

        /*  */
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
        int it = 1; // Always skip position 0.
        int value = 0;

        while (it < origin.length) {
            if (origin[it] == target) {
                value = it;
                it = origin.length; // break operation
            }
            it++;
        }
        return value;
    }

    /*
     * Reverse-Star Algorithim
     * I used selection-sort to sort the destination array.
     */
    public static void reverseStar(int vertexAmt, int arcsAmt, int[] origin, int[] destination) {
        // @@TODO

        // ---Sort destination array---
        sort(origin, destination);

        int arc_src[] = origin;
        int rev_pointer[] = new int[vertexAmt + 2];

        // ---Fill pointer array---
        int pos = 1;
        while (pos <= rev_pointer.length) {
            if (pos == 1) {
                rev_pointer[pos] = 1;
            } else if (pos < rev_pointer.length) {
                rev_pointer[pos] = getFirstInstance(pos, destination);
            } else if (pos == rev_pointer.length) {
                rev_pointer[pos - 1] = (arcsAmt + 1);
            }
            pos++;
        }

        getVertexPredecessor(rev_pointer, destination, origin);
    }

    /* Method that gets the entry degree & the predecessors */
    public static void getVertexPredecessor(int[] rev_pointer, int[] destination, int[] origin) {
        int highestDegree = 0;
        int vertex = 0;

        // Look for highest entry degree among vertexes
        for (int i = 1; i < rev_pointer.length; i++) {
            if (i + 1 < rev_pointer.length) {
                int curr = rev_pointer[i];
                int next = rev_pointer[i + 1];
                int diff = next - curr;

                if (diff > highestDegree) {
                    highestDegree = diff;
                    vertex = i;
                }
            }
        }

        // Get predecessors list
        int predecessors[] = new int[highestDegree];
        int pos = rev_pointer[vertex];

        for (int i = 0; i < predecessors.length; i++) {
            predecessors[i] = origin[pos++];
        }

        // Printing out data
        System.out.println("------Reverse-Star------");
        System.out.println("Vertice: " + vertex);
        System.out.println("Grau de saida: " + highestDegree);
        for (int s : predecessors) {
            System.out.print(s + " ");
        }
        System.out.println();

    }

    /* QuickSort algorithim */
    public static void sort(int[] origin, int[] destination) {
        int n = destination.length;

        // One by one move boundary of unsorted subarray
        for (int i = 1; i < n; i++) {
            // Find minimum element in unsorted array
            int min_idx = i;

            for (int j = i + 1; j < n; j++) {
                if (destination[j] < destination[min_idx]) {
                    min_idx = j;
                }
            }

            // Swap position on destination
            int temp = destination[min_idx];
            destination[min_idx] = destination[i];
            destination[i] = temp;

            // Swap position on origin
            temp = origin[min_idx];
            origin[min_idx] = origin[i];
            origin[i] = temp;
        }
    }

}
