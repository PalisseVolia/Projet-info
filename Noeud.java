public class Noeud {
    String identificationN;                 //nom noeud
    Double abscisse;                        
    Double ordonnée;
    Double typeAppui;                          //simple (tranlation tangentielle), double (bloqué en translation), sans appui => peut etre leur donner un identifiant numérique(1,2,3)

    Noeud (Double abs, Double ord, Double appui) {
        this.abscisse = abs;
        this.ordonnée = ord;
        this.typeAppui = appui;
    }

    public static void name() {
        
    }
        
}