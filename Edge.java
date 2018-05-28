public class Edge {
    private Vertex start;
    private Vertex target;
    private final int weight;

    public Edge(Vertex start, Vertex target, int weight) {
        this.start = start;
        this.target = target;
        this.weight = weight;
    }

    public Vertex getStart() {
        return start;
    }

    public Vertex getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return start.getName() + " -> " + target.getName();
    }

}
