/**
 * Includes the Graph-Class
 *
 * @author Johann Becker, Florian Dahlitz, Tim Leuschner
 */
package dijkstra;

import java.util.List;

/**
 * Represents a graph used by the Dijkstra-Algorithm.
 */
public class Graph {
    private List<Vertex> vertices;
    private List<Edge> edges;

    /**
     * Constructor of the Graph-Class
     *
     * @param vertices List of the graphs vertices
     * @param edges List of the graphs edges
     */
    public Graph(List<Vertex> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }

    /**
     * Returns the vertices of the graph.
     *
     * @return List of the graphs vertices
     */
    public List<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Returns the edges of the graph.
     *
     * @return List of the graphs edges
     */
    public List<Edge> getEdges() {
        return edges;
    }
}
