
public class Edge {
	private Vertex ursprung;
	private Vertex ziel;
	private final int gewicht;
	
	public Edge(Vertex ursprung, Vertex ziel, int gewicht) {
		this.ursprung = ursprung;
		this.ziel = ziel;
		this.gewicht = gewicht;
	}

	public Vertex getUrsprung() {
		return ursprung;
	}

	public Vertex getZiel() {
		return ziel;
	}

	public int getGewicht() {
		return gewicht;
	}
	
	@Override
	public String toString() {
		return ursprung.getName() + " -> " + ziel.getName();
	}

}
