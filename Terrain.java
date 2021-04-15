// =========== CLASSE Terrain ============
//
// Ensemble des points délimitant le terrain
//
// =======================================


public class Terrain {

	Points[] PT;
	String TT;
	public Terrain() {    //constructeur auto
	}
	public Points[] getPT() {          //get et set
		return PT;
	}
	public void setPT(Points[] pT) {
		PT = pT;
	}
	public String getTT() {
		return TT;
	}
	public void setTT(String tT) {
		TT = tT;
	}
	
	
}
