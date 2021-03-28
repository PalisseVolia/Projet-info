
//  ============ CLASSE NOEUD ============
//
//  Associe des valeurs à un noeud en fonction de ses coordonnées et de son type d'appui (représenté par un int (1)simple, (2)double, (3)sans appui).
//
//  ======================================



public class Noeud extends Barre {
    Double abscisse;                        
    Double ordonnée;
    int typeAppui;                                                          //(1)simple (tranlation tangentielle), (2)double (bloqué en translation), (3)sans appui
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