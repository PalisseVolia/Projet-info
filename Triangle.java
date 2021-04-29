public class Triangle {
    private String identificationT;
    private Double abscisseT1;
    private Double abscisseT2;
    private Double abscisseT3;
    private Double ordonneeT1;
    private Double ordonneeT2;
    private Double ordonneeT3;

    Triangle (String id, Double abs1, Double abs2, Double abs3, Double ord1, Double ord2, Double ord3) {
        this.setIdentificationT(id);
        this.abscisseT1 = abs1;
        this.abscisseT2 = abs2;
        this.abscisseT3 = abs3;
        this.ordonneeT1 = ord1;
        this.ordonneeT2 = ord2;
        this.ordonneeT3 = ord3;
    }
    public String getIdentificationT() {
        return identificationT;
    }
    public void setIdentificationT(String identificationT) {
        this.identificationT = identificationT;
    }
    Triangle () {
        this.setIdentificationT("Non_Identifié");
        this.abscisseT1 = 0.0;
        this.abscisseT2 = 0.0;
        this.abscisseT3 = 0.0;
        this.ordonneeT1 = 0.0;
        this.ordonneeT2 = 0.0;
        this.ordonneeT3 = 0.0;
    }
}