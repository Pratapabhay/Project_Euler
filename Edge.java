/**
 * Project Euler: Minimal Network
 * Edge.java
 * Purpose: Implements the Edge datastructure. 
 *
 * @version 1.0 04/26/17
 * @author Abhay Singh
 */

package minimalNetworks;

class Edge {
    int start, end;
    long weight;

    /**
     * Assigns values to an Edge object.
     *
     * @param start Starting vertex of the edge.
     * @param end Ending vertex of the edge.
     * @param weight Weight of the edge.
     */
    public Edge(int start, int end, long weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
