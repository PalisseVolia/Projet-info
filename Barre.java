/* cette classe barre est l'extension de la classe nœud. Dans cette classe, on créé une barre en prenant le nœud de début et de fin

On créé une barre sur la base des nœuds, on prends d'abord les valeurs des nœuds de l'utilisateur puis en fonction de ces valeurs que nous avons demandées à partir de laquelle chaque barre commence et se termine
*/

public class Barre extends Noeud{         // variable de classe de barre

    Noeud debut_noeud;
    Noeud fin_noeud;
    int materiel;
    String identificationB;
    
    
    Barre (Noeud noeud, Noeud noeuds, String idb, int mat) {     // constructeur avec des variables
        this.debut_noeud = noeud;
        this.fin_noeud = noeud;
        this.identificationB = idb;
        this.materiel = mat;
    }
    
    Barre() {                                     // constructeur simple
        this.debut_noeud = new Noeud ();
        this.fin_noeud = new Noeud ();
        this.identificationB = "Non_Identifié";
        this.materiel = 0;
    }
    
        
    public Noeud getDebut_noeud() {              //méthodes get et set
        return debut_noeud;
    }
    
    public void setDebut_noeud(Noeud debut_noeud) {
        this.debut_noeud = debut_noeud;
    }
    
    public Noeud getFin_noeud() {
        return fin_noeud;
    }
    
    public void setFin_noeud(Noeud fin_noeud) {
        this.fin_noeud = fin_noeud;
    }
    
    public int getMateriel() {
        return materiel;
    }
    
    public void setMateriel(int materiel) {
        this.materiel = materiel;
    }
    
    public String getIdentificationB() {
        return identificationB;
    }
    
    public void setIdentificationB(String identificationB) {            //méthode pour identifier les barres
        this.identificationB = identificationB;
    }
    
    
   /* TEST Print Noeud
    Double a = 3.0;
    String name = "test";
    int press = 1;
    Noeud test = new Noeud (a, a, name, support);
    System.out.println (test.identificationN + "coords: (" + test.abscisse + "," + test.ordinate + ") of type" + test.typeAppui);
    */
    }