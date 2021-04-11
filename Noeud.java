
// ============ CLASSE Noeud =============
//
// On associe des valeurs à un nœud en fonction de ses coordonnées et de son type de support (simple, double, encastré).
//
// =======================================


/*
ici on prends les coordonnées des nœuds
l'identification de chaque nœud
le type de support qu'on utilise sur chaque nœud
*/

public class Noeud {     //les variables
    Double abscissa;
    Double ordinate;
    int typeSupport;           // (1) appui simple (tangentiel ), (2) appui double (blocké en translation), (3) appui encastré 
    String identificationN;    // nom noeud

    Noeud (Double abs, Double ord, String idn, int support) {    //constructeur
        this.abscissa = abs;
        this.ordinate = ord;
        this.identificationN = idn;
        this.typeSupport = support;
    }
    Noeud () {                        
        this.abscissa = 0.0;
        this.ordinate = 0.0;
        this.identificationN = "Non_Identifié";
        this.typeSupport = 0;
    }
	public Double getAbscissa() {          //les get et set
		return abscissa;
	}
	public void setAbscissa(Double abscissa) {
		this.abscissa = abscissa;
	}
	public Double getOrdinate() {
		return ordinate;
	}
	public void setOrdinate(Double ordinate) {
		this.ordinate = ordinate;
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