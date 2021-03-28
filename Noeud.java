public class Noeud extends Barre {
    Double abscisse;                        
    Double ordonnée;
    int typeAppui;                                                       //simple (tranlation tangentielle), double (bloqué en translation), sans appui => peut etre leur donner un identifiant numérique(1,2,3)
    String identificationN;                                                 //nom noeud

    Noeud (Double abs, Double ord, String id, int appui) {
        this.abscisse = abs;
        this.ordonnée = ord;
        this.identificationN = id;
        this.typeAppui = appui;
    }
    Noeud () {
        this.abscisse = 0.0;
        this.ordonnée = 0.0;
        this.identificationN = "Unidentified";
        this.typeAppui = 0;
    }
}