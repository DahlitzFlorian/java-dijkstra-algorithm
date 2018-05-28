import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    private final List<Vertex> vertex;
    private final List<Edge> edges;
    private Set<Vertex> finishedVertex;
    private Set<Vertex> unknownVertex;
    private Map<Vertex, Vertex> precursor;
    private Map<Vertex, Integer> distance;

    public Dijkstra(Graph graph) {
        this.vertex = new ArrayList<Vertex>(graph.getVertex());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    /**
     * Initialisiert die benötigten Maps und Sets.
     * Führt den eigentlichen Algorithmus aus.
     * Solange ungewisse Knoten existieren, wird die kürzeste Distanz vom
     * Startknoten zu allen anderen Knoten ermittelt.
     *
     * @param ursprung
     *            Startknoten
     */
    public void runDijkstraRun(Vertex start) {
        finishedVertex = new HashSet<Vertex>();
        unknownVertex = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        precursor = new HashMap<Vertex, Vertex>();
        distance.put(start, 0);
        unknownVertex.add(start);

        while (unknownVertex.size() > 0) {
            Vertex vertex = getSmallestVertex(unknownVertex);
            finishedVertex.add(vertex);
            unknownVertex.remove(vertex);
            findMinimalDistance(vertex);
        }
    }

    /**
     * Ermittelt den Pfad vom Zielknoten aus rückwärts, dabei wird die Vorgänger Map
     * ausgelsen, welche den Pfad enthält.
     *
     * @param ziel
     *            Der Zielknoten, der erreicht werden soll
     * @return den Pfad als LinkedList in korrekter Reihenfolge
     */
    public LinkedList<Vertex> findPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;

        if (precursor.get(step) == null) {
            return null;
        }
        path.add(step);
        while (precursor.get(step) != null) {
            step = precursor.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }

    /**
     * Ermittelt das Kantengewicht zwischen zwei Knoten und überprüft, ob die beiden
     * Knoten miteinander verbunden sind, indem Ursprung und Ziel verglichen werden.
     *
     * @param ursprung
     *            Knoten, von dem ausgegangen werden soll
     * @param ziel
     *            Knoten, der erreicht werden soll
     * @return Das Kantengewicht, also die Distanz, zwischen den beiden Knoten
     * @throws RuntimeException
     */
    private int getDistance(Vertex start, Vertex target) {
        for (Edge e : edges) {
            if (e.getStart().equals(start) && e.getTarget().equals(target)) {
                return e.getWeight();
            }
        }
        throw new RuntimeException("Da lief wohl etwas schief ¯\\_(ツ)_/¯ "+
                         "(Es gibt keine einzige Verbindung zwischen Urspung und Ziel)");
    }

    /**
     * Ermittelt die benachbarten Knoten desjenigen Knotens, der übergeben wird.
     * Dabei werden die bereits erledigten Knoten ignoriert
     *
     * @param node
     *            Der Knoten, dessen Nachbarn ermittelt werden sollen
     * @return Gibt Liste der benachbarten Knoten zurück
     */
    private List<Vertex> getNeighbor(Vertex vertex) {
        List<Vertex> neighbor = new ArrayList<Vertex>();
        for (Edge e : edges) {
            if (e.getStart().equals(vertex) && !visited(e.getTarget())) {
                neighbor.add(e.getTarget());
            }
        }
        return neighbor;
    }

    /**
     * Überprüft, ob ein Knoten besucht wurde, indem die Liste der erledigten Knoten
     * nach dem Parameter durchsucht wird
     *
     * @param node
     *            Der Knoten, der überprüft werden soll
     * @return true, falls der Knoten besucht wurde, sonst false
     */
    private boolean visited(Vertex vertex) {
        return finishedVertex.contains(vertex);
    }

    /**
     * Ermittelt die momentan kürzeste Distanz, bzw. die in der Map gespeicherte
     * Distanz, damit ein Knoten erreicht wird. Falls der Knoten noch nicht erreicht
     * wurde, wird der Wert auf MAX_VALUE gesetzt.
     *
     * @param ziel
     *            Der knoten, dessen kürzeste Distanz vom Startknoten aus ermittelt
     *            werden soll.
     * @return die Distanz, welche bis zum Knoten zurückgelegt werden muss.
     */
    private int getSmallestDistance(Vertex target) {
        Integer d = distance.get(target);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    /**
     * Ermittelt die minimale Distanz vom übergebenen Knoten zu den Nachbarknoten.
     * Dabei wird zunächst die Distanz zu den benachbarten Knoten ermittelt. Sind
     * die Nachbarknoten über den übergebenen Knoten effizienter zu erreichen, wird
     * diese Distanz übernommen, als Vorgänger vermerkt und der Nachbarknoten als
     * unbesucht gespeichert.
     *
     * @param node
     *            Der Knoten, der erreicht werden soll
     */
    private void findMinimalDistance(Vertex vertex) {
        List<Vertex> neighboredVertices = getNeighbor(vertex);
        for (Vertex target : neighboredVertices) {
            if (getSmallestDistance(target) > getSmallestDistance(vertex) + getDistance(vertex, target)) {
                distance.put(target, getSmallestDistance(vertex) + getDistance(vertex, target));
                precursor.put(target, vertex);
                unknownVertex.add(target);
            }
        }
    }

    /**
     * Ermittelt denjenigen Knoten mit der kürzesten Distanz vom Startknoten aus.
     * Dabei werden die Distanzen aller Knoten zum Startknoten verglichen.
     *
     * @param node
     *            Die Menge der Knoten, die sich im Graph befinden
     * @return den Knoten mit der geringsten Distanz vom Startknoten aus
     */
    private Vertex getSmallestVertex(Set<Vertex> vertices) {
        Vertex minimum = null;
        for (Vertex vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getSmallestDistance(vertex) < getSmallestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }
}
