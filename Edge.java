/**
 * Includes the Edge-Class
 *
 * @author Johann Becker, Florian Dahlitz, Tim Leuschner
 */

/**
 * Represents an edge in the graph theory/Dijkstra-Algorithm
 */
public class Edge {
    private Vertex origin;
    private Vertex target;
    private final int weight;

    /**
     * Constructor of the Edge-Class
     *
     * @param origin Representing the origin of the edge
     * @param target Representing the target of the edge
     * @param weight Representing the weight of the edge
     */
    public Edge(Vertex origin, Vertex target, int weight) {
        this.origin = origin;
        this.target = target;
        this.weight = weight;
    }

    /**
     * Returns the origin of the edge.
     *
     * @return origin of edge
     */
    public Vertex getOrigin() {
        return origin;
    }

    /**
     * Returns the target of the edge.
     *
     * @return target of edge
     */
    public Vertex getTarget() {
        return target;
    }

    /**
     * Returns the weight of the edge.
     *
     * @return weight of edge
     */
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return origin.getName() + " -> " + target.getName();
    }
}
