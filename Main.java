import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    
    private static List<Vertex> knoten = new ArrayList<Vertex>();
    private static List<Edge> kanten = new ArrayList<Edge>();
    
    /**
     * Fügt der knoten Liste einen neuen Knoten hinzu
     * 
     * @param name des Knotens
     */
    private static void addKnoten(String name) {
        knoten.add(new Vertex(name));
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
     * @param knoten Liste der Knoten die durchsucht werden soll
     * @return den gefundenen Knoten
     */
    private static Vertex findByName(String name, List<Vertex> knoten) {
        for (Vertex k : knoten) {
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
        
        addKante(findByName("Frankfurt", knoten), findByName("Mannheim", knoten), 85);
        addKante(findByName("Mannheim", knoten), findByName("Karlsruhe", knoten), 80);
        addKante(findByName("Karlsruhe", knoten), findByName("Augsburg", knoten), 250);
        addKante(findByName("Augsburg", knoten), findByName("Muenchen", knoten), 84);
        addKante(findByName("Frankfurt", knoten), findByName("Wuerzburg", knoten), 217);
        addKante(findByName("Wuerzburg", knoten), findByName("Erfurt", knoten), 186);
        addKante(findByName("Wuerzburg", knoten), findByName("Nuernberg", knoten), 103);
        addKante(findByName("Stuttgart", knoten), findByName("Nuernberg", knoten), 183);
        addKante(findByName("Nuernberg", knoten), findByName("Muenchen", knoten), 167);
        addKante(findByName("Frankfurt", knoten), findByName("Kassel", knoten), 173);
        addKante(findByName("Kassel", knoten), findByName("Muenchen", knoten), 502);
        
        
        Graph graph = new Graph(knoten, kanten);
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.runDijkstraRun(findByName("Frankfurt", knoten));
        LinkedList<Vertex> pfad = dijkstra.findePfad(findByName("Muenchen", knoten));
    
        for (Vertex vertex : pfad) {
            System.out.println(vertex.getName());
        }
        
        
    }

}
