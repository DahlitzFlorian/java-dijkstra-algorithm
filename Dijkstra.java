/**
 * Includes the Dijkstra-Class
 *
 * @author Johann Becker, Florian Dahlitz, Tim Leuschner
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Provides the Dijkstra-Algorithm.
 */
public class Dijkstra {
    private final List<Vertex> vertices;
    private final List<Edge> edges;
    private Set<Vertex> finishedVertices;
    private Set<Vertex> unknownVertices;
    private Map<Vertex, Vertex> precursor;
    private Map<Vertex, Integer> distance;

    /**
     * Constructor of the Dijkstra-Class
     *
     * @param graph Current graph used by the Dijkstra-Algorithm
     */
    public Dijkstra(Graph graph) {
        this.vertices = new ArrayList<Vertex>(graph.getVertices());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

    /**
     * Initialised the needed Lists ans Sets.
     * It is used to run the main part of the algorithm.
     * As long as unknown vertices exist, the minimal distance from the
     * start vertex to all other vertices is calculated.
     *
     * @param start Represents the origin of the graph
     */
    public void runDijkstraRun(Vertex start) {
        finishedVertices = new HashSet<Vertex>();
        unknownVertices = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        precursor = new HashMap<Vertex, Vertex>();
        distance.put(start, 0);
        unknownVertices.add(start);

        while (unknownVertices.size() > 0) {
            Vertex vertex = getSmallestVertex(unknownVertices);
            finishedVertices.add(vertex);
            unknownVertices.remove(vertex);
            findMinimalDistance(vertex);
        }
    }

    /**
     * Searches for the path and uses the target as origin.
     * The precursor-Map is used containing the path.
     *
     * @param target Representing the paths target.
     * @return Path as a LinkedList.
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
     * Determines the weight of the edge between two vertices and checks, whether
     * they are linked with each other. Therefore, start and target of the edge are
     * compared.
     *
     * @param start Representing the origin of it
     * @param target Representing the target of it
     *
     * @return Weight of the edge between two vertices (distance)
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
     * Determines the neighbored vertex of the given one. Already finished
     * vertices are ignored.
     *
     * @param vertex Representing the vertix the neighbor is been searched for
     * @return List of neighboring vertices
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
     * Checks, whether a vertex was already visited.
     *
     * @param vertex Representing the to be checked vertex
     * @return true, if the vertex was already visited, false instead
     */
    private boolean visited(Vertex vertex) {
        return finishedVertices.contains(vertex);
    }

    /**
     * Determines the smallest distance to a given vertex based on the distances
     * in the Map. If the vertex was not reached yet, the value is set to MAX_VALUE.
     *
     * @param target Representing the vertex the smallest distance to has to be calculated
     * @return distance
     */
    private int getSmallestDistance(Vertex target) {
        Integer d = distance.get(target);
        if (d == null) {
            return Integer.MAX_VALUE;
        }
        else {
            return d;
        }
    }

    /**
     * Searches for the smallest distance between a given vertex and all other vertices.
     *
     * @param vertex Representing the vertex, which has to be reached
     */
    private void findMinimalDistance(Vertex vertex) {
        List<Vertex> neighboredVertices = getNeighbor(vertex);

        for (Vertex target : neighboredVertices) {
            if (getSmallestDistance(target) > getSmallestDistance(vertex) + getDistance(vertex, target)) {
                distance.put(target, getSmallestDistance(vertex) + getDistance(vertex, target));
                precursor.put(target, vertex);
                unknownVertices.add(target);
            }
        }
    }

    /**
     * Searches for the vertex with the smallest distance between the origin and
     * the given vertices.
     *
     * @param vertex Set of vertices being part of the Map
     * @return vertex with the smallest distance to the origin
     */
    private Vertex getSmallestVertex(Set<Vertex> vertices) {
        Vertex minimum = null;
        for (Vertex vertex : vertices) {
            if (minimum == null) {
                minimum = vertex;
            }
            else {
                if (getSmallestDistance(vertex) < getSmallestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }

        return minimum;
    }
}
