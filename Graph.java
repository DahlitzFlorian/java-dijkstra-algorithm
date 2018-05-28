import java.util.List;

public class Graph {
    private List<Vertex> knoten;
    private List<Edge> kanten;
    
    public Graph(List<Vertex> knoten, List<Edge> kanten) {
        this.knoten = knoten;
        this.kanten = kanten; 
    }

    public List<Vertex> getKnoten() {
        return knoten;
    }

    public List<Edge> getKanten() {
        return kanten;
    }
    
    
}
