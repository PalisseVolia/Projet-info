package Treillis;
// ============ CLASSE Noeud =============
//
// On associe des valeurs à un nœud en fonction de ses coordonnées et de son type de support (simple, double, encastré).
//
// =======================================

import java.util.*;

public class Noeud {
	
  	private Double abscisse;
  	private Double ordonnee;
	private int typeSupport;           	// (1) appui simple (tangentiel ), (2) appui double (bloqué en translation), (3) noeud simple, (4) appui, (5) impossible
  	private String identificationN;    	// nom noeud
	private int[] cotetriangle; // Si le noeud est un appui, définit sur quel coté de quel triangle il s'appuie: [0] = n° triangle   [1] = coté triangle (coté 1 = T1/T2, coté 2 = T2/T3, coté 3 = T3/T1)
	private ArrayList<String> tabbar = new ArrayList<String>(); 



	Noeud (Double abs, Double ord, String idn, int support, int triangle, int cotetri) {    	//constructeur

    	this.abscisse = abs;
    	this.ordonnee = ord;
    	this.identificationN = idn;
    	this.typeSupport = support;
		int[] tmp = {triangle, cotetri};
		this.cotetriangle = tmp;
  	}
  	Noeud () {                        														//constructeur par défaut
    	this.abscisse = 0.0;
    	this.ordonnee = 0.0;
    	this.identificationN = "Non_Identifié";
    	this.typeSupport = 0;
		int[] tmp = {0, 0};
		this.cotetriangle = tmp;
  	}

	//méthodes get et set
	public int gettriangleappui() {															
		return cotetriangle[0];
	}

	public int gettrianglecote() {
		return cotetriangle[1];
	}

	public void settriangleappui(int ntriangle) {
		this.cotetriangle[0] = ntriangle;
	}

	public void settrianglecote(int ctriangle) {
		this.cotetriangle[1] = ctriangle;
	}

	public Double getabscisse() {
		return abscisse;
	}

	public Double getordonnee() {
		return ordonnee;
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
}