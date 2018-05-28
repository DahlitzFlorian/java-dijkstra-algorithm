import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

    private final List<Vertex> knoten;
    private final List<Edge> kanten;
    private Set<Vertex> erledigteKnoten;
    private Set<Vertex> ungewisseKnoten;
    private Map<Vertex, Vertex> vorgaenger;
    private Map<Vertex, Integer> distanz;

    public Dijkstra(Graph graph) {
        this.knoten = new ArrayList<Vertex>(graph.getKnoten());
        this.kanten = new ArrayList<Edge>(graph.getKanten());
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
    public void runDijkstraRun(Vertex ursprung) {
        erledigteKnoten = new HashSet<Vertex>();
        ungewisseKnoten = new HashSet<Vertex>();
        distanz = new HashMap<Vertex, Integer>();
        vorgaenger = new HashMap<Vertex, Vertex>();
        distanz.put(ursprung, 0);
        ungewisseKnoten.add(ursprung);

        while (ungewisseKnoten.size() > 0) {
            Vertex knoten = getKleinstenKnoten(ungewisseKnoten);
            erledigteKnoten.add(knoten);
            ungewisseKnoten.remove(knoten);
            findeMinimaleDistanz(knoten);
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
    public LinkedList<Vertex> findePfad(Vertex ziel) {
        LinkedList<Vertex> pfad = new LinkedList<Vertex>();
        Vertex step = ziel;
        
        if (vorgaenger.get(step) == null) {
            return null;
        }
        pfad.add(step);
        while (vorgaenger.get(step) != null) {
            step = vorgaenger.get(step);
            pfad.add(step);
        }

        Collections.reverse(pfad);
        return pfad;
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
    private int getDistanz(Vertex ursprung, Vertex ziel) {
        for (Edge e : kanten) {
            if (e.getUrsprung().equals(ursprung) && e.getZiel().equals(ziel)) {
                return e.getGewicht();
            }
        }
        throw new RuntimeException("Da lief wohl etwas schief ¯\\_(ツ)_/¯ "+
                         "(Es gibt keine einzige Verbindung zwischen Urspung und Ziel)");
    }

    /**
     * Ermittelt die benachbarten Knoten desjenigen Knotens, der übergeben wird.
     * Dabei werden die bereits erledigten Knoten ignoriert
     * 
     * @param knoten
     *            Der Knoten, dessen Nachbarn ermittelt werden sollen
     * @return Gibt Liste der benachbarten Knoten zurück
     */
    private List<Vertex> getNachbarn(Vertex knoten) {
        List<Vertex> nachbarn = new ArrayList<Vertex>();
        for (Edge e : kanten) {
            if (e.getUrsprung().equals(knoten) && !besucht(e.getZiel())) {
                nachbarn.add(e.getZiel());
            }
        }
        return nachbarn;
    }

    /**
     * Überprüft, ob ein Knoten besucht wurde, indem die Liste der erledigten Knoten
     * nach dem Parameter durchsucht wird
     * 
     * @param knoten
     *            Der Knoten, der überprüft werden soll
     * @return true, falls der Knoten besucht wurde, sonst false
     */
    private boolean besucht(Vertex knoten) {
        return erledigteKnoten.contains(knoten);
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
    private int getKuerzesteDistanz(Vertex ziel) {
        Integer d = distanz.get(ziel);
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
     * @param knoten
     *            Der Knoten, der erreicht werden soll
     */
    private void findeMinimaleDistanz(Vertex knoten) {
        List<Vertex> benachbarteKnoten = getNachbarn(knoten);
        for (Vertex ziel : benachbarteKnoten) {
            if (getKuerzesteDistanz(ziel) > getKuerzesteDistanz(knoten) + getDistanz(knoten, ziel)) {
                distanz.put(ziel, getKuerzesteDistanz(knoten) + getDistanz(knoten, ziel));
                vorgaenger.put(ziel, knoten);
                ungewisseKnoten.add(ziel);
            }
        }
    }

    /**
     * Ermittelt denjenigen Knoten mit der kürzesten Distanz vom Startknoten aus.
     * Dabei werden die Distanzen aller Knoten zum Startknoten verglichen.
     * 
     * @param knoten
     *            Die Menge der Knoten, die sich im Graph befinden
     * @return den Knoten mit der geringsten Distanz vom Startknoten aus
     */
    private Vertex getKleinstenKnoten(Set<Vertex> knoten) {
        Vertex minimum = null;
        for (Vertex v : knoten) {
            if (minimum == null) {
                minimum = v;
            } else {
                if (getKuerzesteDistanz(v) < getKuerzesteDistanz(minimum)) {
                    minimum = v;
                }
            }
        }
        return minimum;
    } 
}
