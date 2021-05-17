package Treillis;
//TODO: ajouter une création d'espace de constru pour pouvoir call les extremums
public class Espace {
    private double xmax;
    private double xmin;
    private double ymax;
    private double ymin;

    Espace(Double xM, Double xm, Double yM, Double ym) {
        this.xmax = xM;
        this.xmin = xm;
        this.ymax = yM;
        this.ymin = ym;
    }
    Espace() {
        this.xmax = 0.0;
        this.xmin = 0.0;
        this.ymax = 0.0;
        this.ymin = 0.0;
    }

    public double getXmax() {
        return xmax;
    }
    public void setXmax(double xmax) {
        this.xmax = xmax;
    }
    public double getXmin() {
        return xmin;
    }
    public void setXmin(double xmin) {
        this.xmin = xmin;
    }
    public double getYmax() {
        return ymax;
    }
    public void setYmax(double ymax) {
        this.ymax = ymax;
    }
    public double getYmin() {
        return ymin;
    }
    public void setYmin(double ymin) {
        this.ymin = ymin;
    }
}
