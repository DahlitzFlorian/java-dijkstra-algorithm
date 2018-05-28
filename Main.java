import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static List<Vertex> vertex = new ArrayList<Vertex>();
    private static List<Edge> edges = new ArrayList<Edge>();

    /**
     * Fügt der knoten Liste einen neuen Knoten hinzu
     *
     * @param name des Knotens
     */
    private static void addVertex(String name) {
        vertex.add(new Vertex(name));
    }

    /**
     * Erzeugt aus zwei Knoten eine Kante und fügt sie der edges Liste hinzu
     *
     * @param ursprung Startknoten
     * @param ziel Endknoten
     * @param gewicht der Kante
     */
    private static void addEdge(Vertex start, Vertex target, int weight) {
        edges.add(new Edge(start, target, weight));
    }

    /**
     * Durchsucht eine Liste von Knoten nach einem bestimmten Knoten und gibt
     * diesen zurück
     *
     * @param name des Knoten der gefunden werden soll
     * @param vertex Liste der Knoten die durchsucht werden soll
     * @return den gefundenen Knoten
     */
    private static Vertex findByName(String name, List<Vertex> vertex) {
        for (Vertex k : vertex) {
            if (k.getName().equals(name)) {
                return k;
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

        addEdge(findByName("Frankfurt", vertex), findByName("Mannheim", vertex), 85);
        addEdge(findByName("Mannheim", vertex), findByName("Karlsruhe", vertex), 80);
        addEdge(findByName("Karlsruhe", vertex), findByName("Augsburg", vertex), 250);
        addEdge(findByName("Augsburg", vertex), findByName("Muenchen", vertex), 84);
        addEdge(findByName("Frankfurt", vertex), findByName("Wuerzburg", vertex), 217);
        addEdge(findByName("Wuerzburg", vertex), findByName("Erfurt", vertex), 186);
        addEdge(findByName("Wuerzburg", vertex), findByName("Nuernberg", vertex), 103);
        addEdge(findByName("Stuttgart", vertex), findByName("Nuernberg", vertex), 183);
        addEdge(findByName("Nuernberg", vertex), findByName("Muenchen", vertex), 167);
        addEdge(findByName("Frankfurt", vertex), findByName("Kassel", vertex), 173);
        addEdge(findByName("Kassel", vertex), findByName("Muenchen", vertex), 502);


        Graph graph = new Graph(vertex, edges);
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.runDijkstraRun(findByName("Frankfurt", vertex));
        LinkedList<Vertex> path = dijkstra.findPath(findByName("Muenchen", vertex));

        for (Vertex vertex : path) {
            System.out.println(vertex.getName());
        }


    }

}
