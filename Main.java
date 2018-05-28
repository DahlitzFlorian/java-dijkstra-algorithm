/**
 * Includes the Main-Class
 *
 * @author Johann Becker, Florian Dahlitz, Tim Leuschner
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import dijkstra.*;

/**
 * Class handling the application flow.
 * It is used for demonstration purposes.
 */
public class Main {
    private static List<Vertex> vertices = new ArrayList<Vertex>();
    private static List<Edge> edges = new ArrayList<Edge>();

    /**
     * Adds a vertex to the list of vertices.
     *
     * @param name Name of the vertex
     */
    private static void addVertex(String name) {
        Vertex vertex = new Vertex(name);
        vertices.add(vertex);
    }

    /**
     * Creates an edge based on two given vertices and adds it to the list
     * of edges.
     *
     * @param origin Representing the edges origin
     * @param target Representing the edges target
     * @param weight Representing the edges weight
     */
    private static void addEdge(Vertex origin, Vertex target, int weight) {
        Edge edge = new Edge(origin, target, weight);
        edges.add(edge);
    }

    /**
     * Searches for a specific vertex in a given list of vertices and returns it.
     *
     * @param name Name of the vertex searched for
     * @param vertices List of vertices sifted through
     * @return vertex object searched for
     */
    private static Vertex findByName(String name, List<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            if (vertex.getName().equals(name)) {
                return vertex;
            }
        }

        throw new RuntimeException("Du Idiot. Kannst nicht mal einen richtigen Namen eingeben.");
    }

    /**
     * Main-method called from the command line.
     *
     * @param args Command line input
     **/
    public static void main(String... args) {
        addVertex("Frankfurt");
        addVertex("Mannheim");
        addVertex("Karlsruhe");
        addVertex("Augsburg");
        addVertex("Muenchen");
        addVertex("Wuerzburg");
        addVertex("Erfurt");
        addVertex("Nuernberg");
        addVertex("Stuttgart");
        addVertex("Kassel");

        addEdge(findByName("Frankfurt", vertices), findByName("Mannheim", vertices), 85);
        addEdge(findByName("Mannheim", vertices), findByName("Karlsruhe", vertices), 80);
        addEdge(findByName("Karlsruhe", vertices), findByName("Augsburg", vertices), 250);
        addEdge(findByName("Augsburg", vertices), findByName("Muenchen", vertices), 84);
        addEdge(findByName("Frankfurt", vertices), findByName("Wuerzburg", vertices), 217);
        addEdge(findByName("Wuerzburg", vertices), findByName("Erfurt", vertices), 186);
        addEdge(findByName("Wuerzburg", vertices), findByName("Nuernberg", vertices), 103);
        addEdge(findByName("Stuttgart", vertices), findByName("Nuernberg", vertices), 183);
        addEdge(findByName("Nuernberg", vertices), findByName("Muenchen", vertices), 167);
        addEdge(findByName("Frankfurt", vertices), findByName("Kassel", vertices), 173);
        addEdge(findByName("Kassel", vertices), findByName("Muenchen", vertices), 502);


        Graph graph = new Graph(vertices, edges);
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.runDijkstraRun(findByName("Frankfurt", vertices));
        LinkedList<Vertex> path = dijkstra.findPath(findByName("Muenchen", vertices));

        for (Vertex vertex : path) {
            System.out.println(vertex.getName());
        }
    }
}
