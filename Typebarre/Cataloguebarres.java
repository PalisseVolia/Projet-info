package Typebarre;

public class Cataloguebarres {
    private Double cout;
    private Double longueurmax;
    private Double longueurmin;
    private Double resimaxtens;
    private Double resimaxcomp;

    Cataloguebarres(int i) {
        if (i == 1) { // pleine
            cout = 55.0;
            longueurmax = 100.0;
            longueurmin = 0.0;
            resimaxtens = 350.0; // poutre pleine Acier carbone trempé
            resimaxcomp = 235.0;
        }
        if (i == 2) {
            cout = 100.0;
            longueurmax = 200.0;
            longueurmin = 0.0;
            resimaxtens = 600.0; // poutre pleine Acier carbone trempé
            resimaxcomp = 400.0;
        }
    }

    public Double getCout() {
        return cout;
    }

    public Double getLongueurmax() {
        return longueurmax;
    }

    public Double getLongueurmin() {
        return longueurmin;
    }

    public Double getResimaxcomp() {
        return resimaxcomp;
    }

    public Double getResimaxtens() {
        return resimaxtens;
    }
}
