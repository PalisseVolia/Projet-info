package Terrain;
// =========== CLASSE Triangle ============
//
// On définit les triangles délimitant le terrain
//
// ========================================
import java.lang.Math;

public class Triangle {
    private String identificationT;                                                                             //identifiant du triangle
    private Double abscisseT1;                                                                                  //abscisses de ses trois sommets
    private Double abscisseT2;
    private Double abscisseT3;
    private Double ordonneeT1;                                                                                  //ordonnées de ses trois sommets
    private Double ordonneeT2;
    private Double ordonneeT3;
    private Double angle123;
    private Double angle132;
    private Double angle213;

    Triangle (String id, Double abs1, Double abs2, Double abs3, Double ord1, Double ord2, Double ord3) {        //constructeur
        this.identificationT = id;
        this.abscisseT1 = abs1;
        this.abscisseT2 = abs2;
        this.abscisseT3 = abs3;
        this.ordonneeT1 = ord1;
        this.ordonneeT2 = ord2;
        this.ordonneeT3 = ord3;
        angles();
    }
    Triangle () {                                                                                               //constructeur par défaut
        this.identificationT = "Non_Identifié";
        this.abscisseT1 = 0.0;
        this.abscisseT2 = 0.0;
        this.abscisseT3 = 0.0;
        this.ordonneeT1 = 0.0;
        this.ordonneeT2 = 0.0;
        this.ordonneeT3 = 0.0;
        angles();
    }
    public Double getOrdonneeT3() {                                                                             //méthodes get et set
        return ordonneeT3;
    }
    public void setOrdonneeT3(Double ordonneeT3) {
        this.ordonneeT3 = ordonneeT3;
    }
    public Double getOrdonneeT2() {
        return ordonneeT2;
    }
    public void setOrdonneeT2(Double ordonneeT2) {
        this.ordonneeT2 = ordonneeT2;
    }
    public Double getOrdonneeT1() {
        return ordonneeT1;
    }
    public void setOrdonneeT1(Double ordonneeT1) {
        this.ordonneeT1 = ordonneeT1;
    }
    public Double getAbscisseT3() {
        return abscisseT3;
    }
    public void setAbscisseT3(Double abscisseT3) {
        this.abscisseT3 = abscisseT3;
    }
    public Double getAbscisseT2() {
        return abscisseT2;
    }
    public void setAbscisseT2(Double abscisseT2) {
        this.abscisseT2 = abscisseT2;
    }
    public Double getAbscisseT1() {
        return abscisseT1;
    }
    public void setAbscisseT1(Double abscisseT1) {
        this.abscisseT1 = abscisseT1;
    }
    public String getIdentificationT() {
        return identificationT;
    }
    public void setIdentificationT(String identificationT) {
        this.identificationT = identificationT;
    }
    public void setAngle123(Double angle123){
        this.angle123 = angle123;
    }
    public void setAngle132(Double angle132){
        this.angle132 = angle132;
    }
    public void setAngle213(Double angle213){
        this.angle213 = angle213;
    }



    public static double trouve_angle(double x1,double x2, double x3, double y1, double y2, double y3) {
        double p0c = Math.sqrt(Math.pow(x2-x1,2)+
                            Math.pow(y2-y1,2));   
        double p1c = Math.sqrt(Math.pow(x2-x3,2)+
                            Math.pow(y2-x3,2)); 
        double p0p1 = Math.sqrt(Math.pow(x3-x1,2)+
                             Math.pow(y3-y1,2));
        return Math.acos((p1c*p1c+p0c*p0c-p0p1*p0p1)/(2*p1c*p0c));
    }
    public void angles() {
        double x1 = getAbscisseT1();
        double x2 = getAbscisseT2();
        double x3 = getAbscisseT3();
        double y1 = getOrdonneeT1();
        double y2 = getOrdonneeT2();
        double y3 = getOrdonneeT3();

        setAngle123(trouve_angle(x1, x2, x3, y1, y2, y3));
        setAngle132(trouve_angle(x1, x3, x2, y1, y3, y2));
        setAngle213(trouve_angle(x2, x1, x3, y2, y1, y3));
    }
}