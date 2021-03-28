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

    public static void main(String[] args) {

    }

    
    
    /* TEST     Print Noeud
    Double a = 3.0;
    String nom = "test";
    int appui = 1;
    Noeud test = new Noeud(a,a,nom,appui);
    System.out.println(test.identificationN + " coords: (" + test.abscisse + "," + test.ordonnée + ") de type " + test.typeAppui);
    */
}
