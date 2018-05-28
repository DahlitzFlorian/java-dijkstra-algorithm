import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    
    private static List<Vertex> node = new ArrayList<Vertex>();
    private static List<Edge> kanten = new ArrayList<Edge>();
    
    /**
     * Fügt der knoten Liste einen neuen Knoten hinzu
     * 
     * @param name des Knotens
     */
    private static void addKnoten(String name) {
        node.add(new Vertex(name));
    }
    
    /**
     * Erzeugt aus zwei Knoten eine Kante und fügt sie der kanten Liste hinzu
     * 
     * @param ursprung Startknoten
     * @param ziel Endknoten
     * @param gewicht der Kante
     */
    private static void addKante(Vertex ursprung, Vertex ziel, int gewicht) {
        kanten.add(new Edge(ursprung, ziel, gewicht));
    }
    
    /**
     * Durchsucht eine Liste von Knoten nach einem bestimmten Knoten und gibt
     * diesen zurück 
     * 
     * @param name des Knoten der gefunden werden soll
     * @param node Liste der Knoten die durchsucht werden soll
     * @return den gefundenen Knoten
     */
    private static Vertex findByName(String name, List<Vertex> node) {
        for (Vertex k : node) {
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
        addKnoten("Frankfurt");
        addKnoten("Mannheim");
        addKnoten("Karlsruhe");
        addKnoten("Augsburg");
        addKnoten("Muenchen");
        addKnoten("Wuerzburg");
        addKnoten("Erfurt");
        addKnoten("Nuernberg");
        addKnoten("Stuttgart");
        addKnoten("Kassel");
        
        addKante(findByName("Frankfurt", node), findByName("Mannheim", node), 85);
        addKante(findByName("Mannheim", node), findByName("Karlsruhe", node), 80);
        addKante(findByName("Karlsruhe", node), findByName("Augsburg", node), 250);
        addKante(findByName("Augsburg", node), findByName("Muenchen", node), 84);
        addKante(findByName("Frankfurt", node), findByName("Wuerzburg", node), 217);
        addKante(findByName("Wuerzburg", node), findByName("Erfurt", node), 186);
        addKante(findByName("Wuerzburg", node), findByName("Nuernberg", node), 103);
        addKante(findByName("Stuttgart", node), findByName("Nuernberg", node), 183);
        addKante(findByName("Nuernberg", node), findByName("Muenchen", node), 167);
        addKante(findByName("Frankfurt", node), findByName("Kassel", node), 173);
        addKante(findByName("Kassel", node), findByName("Muenchen", node), 502);
        
        
        Graph graph = new Graph(node, kanten);
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.runDijkstraRun(findByName("Frankfurt", node));
        LinkedList<Vertex> pfad = dijkstra.findePfad(findByName("Muenchen", node));
    
        for (Vertex vertex : pfad) {
            System.out.println(vertex.getName());
        }
        
        
    }

}
