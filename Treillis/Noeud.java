package Treillis;
// ============ CLASSE Noeud =============
//
// On associe des valeurs à un nœud en fonction de ses coordonnées et de son type de support (simple, double, encastré).
//
// =======================================


public class Noeud {
	
    private Double abscisse;
    private Double ordonnee;
    private int typeSupport;           // (1) appui simple (tangentiel ), (2) appui double (bloqué en translation), (3) noeud simple, (4) appui, (5) impossible
    private String identificationN;    // nom noeud

    Noeud (Double abs, Double ord, String idn, int support) {    	//constructeur
        this.abscisse = abs;
        this.ordonnee = ord;
        this.identificationN = idn;
        this.typeSupport = support;
    }
    Noeud () {                        								//constructeur par défaut
        this.abscisse = 0.0;
        this.ordonnee = 0.0;
        this.identificationN = "Non_Identifié";
        this.typeSupport = 0;
    }
	public Double getabscisse() {          							//méthodes get et set
		return abscisse;
	}
	public void setabscisse(Double abscisse) {
		this.abscisse = abscisse;
	}
	public Double getordonnee() {
		return ordonnee;
	}
	public void setordonnee(Double ordonnee) {
		this.ordonnee = ordonnee;
	}
	public int getTypeSupport() {
		return typeSupport;
	}
	public void setTypeSupport(int typeSupport) {
		this.typeSupport = typeSupport;
	}
	public String getIdentificationN() {
		return identificationN;
	}
	public void setIdentificationN(String identificationN) {
		this.identificationN = identificationN;
	}
}