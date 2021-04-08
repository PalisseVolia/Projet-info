public class Barre extends Noeud{

    Noeud debut_noeud;
    Noeud fin_noeud;
    int materiel;
    String identificationB;
    
    
    Barre (Noeud noeud, Noeud noeuds, String idb, int mat) {
        this.debut_noeud = noeud;
        this.fin_noeud = noeud;
        this.identificationB = idb;
        this.materiel = mat;
    }
    
    Barre() {
        this.debut_noeud = new Noeud ();
        this.fin_noeud = new Noeud ();
        this.identificationB = "Non_Identifié";
        this.materiel = 0;
    }
    
    public static String idb (int numb) {
        String idb = "N" + String.valueOf (numb);
        return (idb);
    }
    
    public Noeud getDebut_noeud() {
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
    
    public void setIdentificationB(String identificationB) {
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