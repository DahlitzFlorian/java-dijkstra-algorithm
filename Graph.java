import java.util.List;

public class Graph {
    private List<Vertex> vertex;
    private List<Edge> edges;

    public Graph(List<Vertex> vertex, List<Edge> edges) {
        this.vertex = vertex;
        this.edges = edges;
    }

    public List<Vertex> getVertex() {
        return vertex;
    }

    public List<Edge> getEdges() {
        return edges;
    }


}
