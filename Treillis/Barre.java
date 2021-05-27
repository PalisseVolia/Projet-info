package Treillis;
// ============ CLASSE Barre =============
//
// On crée une barre avec deux noeuds (début et fin), un type (1 = Pleine, 2 = en I) et une identification
//
// =======================================

public class Barre {

    Noeud debut_noeud;                                          // premier noeud
    Noeud fin_noeud;                                            // second noeud
    int type;                                                   // (1) = Pleine (2) = En I
    String identificationB;

    Barre(Noeud noeud1, Noeud noeud2, String idb, int type) {   // constructeur principal
        this.debut_noeud = noeud1;
        this.fin_noeud = noeud2;
        this.identificationB = idb;
        this.type = type;
    }

    Barre() {                                                   // constructeur par défaut
        this.debut_noeud = new Noeud();
        this.fin_noeud = new Noeud();
        this.identificationB = "Non_Identifié";
        this.type = 0;
    }

    // méthodes get
    public Noeud getDebut_noeud() {
        return debut_noeud;
    }

    public Noeud getFin_noeud() {
        return fin_noeud;
    }

    public int gettype() {
        return type;
    }

    public String getIdentificationB() {
        return identificationB;
    }
}