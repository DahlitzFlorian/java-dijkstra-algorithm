import java.util.List;

public class Graph {
    private List<Vertex> node;
    private List<Edge> kanten;
    
    public Graph(List<Vertex> node, List<Edge> kanten) {
        this.node = node;
        this.kanten = kanten; 
    }

    public List<Vertex> getKnoten() {
        return node;
    }

    public List<Edge> getKanten() {
        return kanten;
    }
    
    
}
