package Terrain;
// =========== CLASSE Triangle ============
//
// On définit les triangles délimitant le terrain
//
// ========================================

public class Triangle {
    private String identificationT;                                                                             //identifiant du triangle
    private Double abscisseT1;                                                                                  //abscisses de ses trois sommets
    private Double abscisseT2;
    private Double abscisseT3;
    private Double ordonneeT1;                                                                                  //ordonnées de ses trois sommets
    private Double ordonneeT2;
    private Double ordonneeT3;

    Triangle (String id, Double abs1, Double abs2, Double abs3, Double ord1, Double ord2, Double ord3) {        //constructeur
        this.identificationT = id;
        this.abscisseT1 = abs1;
        this.abscisseT2 = abs2;
        this.abscisseT3 = abs3;
        this.ordonneeT1 = ord1;
        this.ordonneeT2 = ord2;
        this.ordonneeT3 = ord3;
    }
    Triangle () {                                                                                               //constructeur par défaut
        this.identificationT = "Non_Identifié";
        this.abscisseT1 = 0.0;
        this.abscisseT2 = 0.0;
        this.abscisseT3 = 0.0;
        this.ordonneeT1 = 0.0;
        this.ordonneeT2 = 0.0;
        this.ordonneeT3 = 0.0;
    }

    //méthodes get
    public Double getOrdonneeT3() {
        return ordonneeT3;
    }

    public Double getOrdonneeT2() {
        return ordonneeT2;
    }

    public Double getOrdonneeT1() {
        return ordonneeT1;
    }

    public Double getAbscisseT3() {
        return abscisseT3;
    }
    
    public Double getAbscisseT2() {
        return abscisseT2;
    }

    public Double getAbscisseT1() {
        return abscisseT1;
    }

    public String getIdentificationT() {
        return identificationT;
    }
}