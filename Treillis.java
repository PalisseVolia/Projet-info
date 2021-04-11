/*
c'est la classe où est placé le triangle
le treillis est fait par la classe noeud et barre
les nœuds se combinent pour former une barre
*/

public class Treillis {      //variables

	Barre barre_data[];
	Noeud noeud_data[];
	
	public Treillis() {     //constructeur auto 
				
	}

	public Barre[] getBarre_data() {         //get et set
		return barre_data;
	}

	public void setBarre_data(Barre[] barre_data) {
		this.barre_data = barre_data;
	}

	public Noeud[] getNoeud_data() {
		return noeud_data;
	}

	public void setNoeud_data(Noeud[] noeud_Data1) {
		this.noeud_data = noeud_Data1;
	}
	
	
	
	
}
