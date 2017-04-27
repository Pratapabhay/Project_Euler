/**
 * Project Euler: Minimal Network
 * MST.java
 * Purpose: Finds out maximum saving which can be acheived.
 *
 * @version 1.0 04/26/17
 * @author Abhay Singh
 */

package minimalNetworks;
import java.io.*;
import java.util.*;
import java.math.*;

class MST {

    private static int verticesNumber = 40;
    static long total = 0;

    /**
     * Takes input from the given file and return a list of edges
     *
     * @param fileName Input file
     * @return edges An edge object of Edge class containing all the
     * edges in the graph.
     */
    public LinkedList input(String fileName) {
        Scanner scanner = null;
        try {
            File file = new File(fileName);
            scanner = new Scanner(file);
        } catch (Exception e) {
            System.err.println("Please check that the input file exixts in the current directory");
            System.exit(-1);
        }
        String temp;
        String[] vertices;
        LinkedList edges = new LinkedList < Edge > ();
        for (int i = 0; i < verticesNumber; i++) {
            temp = scanner.nextLine();
            vertices = temp.split(",");

            for (int j = 0; j < verticesNumber; j++) {
                if (!vertices[j].equals("-")) {
                    Edge current = new Edge(i, j, Long.parseLong(vertices[j]));
                    edges.add(current);
                    total = total + current.weight;

                }

            }
        }
        return edges;
    }

    /**
     * The main method begins execution of the tests.
     *
     * @param args input file.
     */

    public static void main(String[] args) throws Exception {
        MST object = new MST();
        if (args.length != 1) {
            System.err.println("Run the program via following command : \njava minimalNetworks/MST <input_file>");
            System.exit(1);
        }
        String fileName = args[0];
        LinkedList edges = object.input(fileName);


        /**
         * Sort the input edges according to their weights.
         *
         * @param edges Object of Edge Class
         * @param Comparator To compare the elements of Edge object
         */

        Collections.sort(edges, new Comparator < Edge > () {
            public int compare(Edge e1, Edge e2) {
                if (e1.weight >= e2.weight) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });


        /**
         * addedEdges will contain all the edges that are included
         * in the Minimum Spanning Tree
         */
        LinkedList addedEdges = new LinkedList < Edge > ();
        long sum = 0;
        UnionRank obj = new UnionRank(verticesNumber);
        Iterator it = edges.iterator();
        int addedCount = 0;

        while (it.hasNext() && addedCount <= (verticesNumber - 1)) {
            Edge current = (Edge) it.next();
            int parent1 = obj.find(current.start);
            int parent2 = obj.find(current.end);

            /**
             * Cycle detection, if parent1 == parent2, then there is
             * a cycle.
             */
            if (parent1 != parent2) {
                addedEdges.add(current);
                addedCount++;
                obj.union(parent1, parent2);
                sum = sum + current.weight;
            }
        }

        System.out.println("Maximum saving achieved:" + (total / 2 - sum));
    }
}
