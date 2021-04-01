
//  ============ CLASSE BARRE ============
//
//  Associe des valeurs à une barre en fonction de deux noeuds et de son materiau (représenté par un int).
//
//  ======================================



public class Barre extends Noeud {
    Noeud noeuddepart;
    Noeud noeudfin;
    int materiau;
    String identificationB;
    
    Barre (Noeud noeudd, Noeud noeudf, String idb, int mat) {
        this.noeuddepart = noeudd;
        this.noeudfin = noeudf;
        this.identificationB = idb;
        this.materiau = mat;
    }
    
    Barre () {
        this.noeuddepart = new Noeud();
        this.noeudfin = new Noeud();
        this.identificationB = "Unidentified";
        this.materiau = 0;
    }

    public static String idb(int numb) {
        String idb = "N" + String.valueOf(numb);
        return(idb);
    }

    /* TEST     Print Noeud
    Double a = 3.0;
    String nom = "test";
    int appui = 1;
    Noeud test = new Noeud(a,a,nom,appui);
    System.out.println(test.identificationN + " coords: (" + test.abscisse + "," + test.ordonnée + ") de type " + test.typeAppui);
    */
}
