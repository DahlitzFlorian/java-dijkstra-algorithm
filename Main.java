import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    private static List<Vertex> vertices = new ArrayList<Vertex>();
    private static List<Edge> edges = new ArrayList<Edge>();

    /**
     * Fügt der knoten Liste einen neuen Knoten hinzu
     *
     * @param name des Knotens
     */
    private static void addVertex(String name) {
        Vertex vertex = new Vertex(name);
        vertices.add(vertex);
    }

    /**
     * Erzeugt aus zwei Knoten eine Kante und fügt sie der edges Liste hinzu
     *
     * @param ursprung Startknoten
     * @param ziel Endknoten
     * @param gewicht der Kante
     */
    private static void addEdge(Vertex start, Vertex target, int weight) {
        Edge edge = new Edge(start, target, weight);
        edges.add(edge);
    }

    /**
     * Durchsucht eine Liste von Knoten nach einem bestimmten Knoten und gibt
     * diesen zurück
     *
     * @param name des Knoten der gefunden werden soll
     * @param vertex Liste der Knoten die durchsucht werden soll
     * @return den gefundenen Knoten
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
     * @param args Kommandozeilen input
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
