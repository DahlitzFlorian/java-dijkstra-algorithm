/**
 * Includes the Vertex-Class
 *
 * @author Johann Becker, Florian Dahlitz, Tim Leuschner
 */
package dijkstra;

/**
 * Represents a vertex in the graph theory/Dijkstra-Algorithm.
 */
public class Vertex {
    private String name;

    /**
     *Constructor of the Vertex-Class
     *
     * @param name Represents the name of the vertex
     */
    public Vertex(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the vertex.
     *
     * @return Name of the vertex
     */
    public String getName() {
        return this.name;
    }
}
