/**
 * Project Euler: Minimal Network
 * UnionRank.java
 * Purpose: Implements the UnionRank datastructure. 
 *
 * @version 1.0 04/26/17
 * @author Abhay Singh
 */

package minimalNetworks;
class UnionRank {
    int[] setArray;

    /**
     * Create n sets for n vertices.
     *
     * @param n Number of vertices
     */

    public UnionRank(int n) {
            setArray = new int[n];


            for (int i = 0; i < n; i++) {
                setArray[i] = -1;
            }
        }
        /**
         * Finds the parent of given vertex.
         *
         * @param n Vertex whose parent we need to find.
         * @return n Parent of given vertex.
         */
    int find(int n) {
            if (this.setArray[n] == -1)
                return n;
            else return (find(this.setArray[n]));
        }
        /**
         * Union of given sets.
         *
         * @param set1 Set of vertices.
         * @param set2 Set of vertices.
         */
    void union(int set1, int set2) {
        int parent1 = this.find(set1);
        int parent2 = this.find(set2);

        if (parent1 != parent2) {
            this.setArray[parent1] = parent2;
        }

    }
}
