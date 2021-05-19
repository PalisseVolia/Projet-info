package Treillis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Espace {
    private double xmax;
    private double xmin;
    private double ymax;
    private double ymin;

    public Espace() {
        try {
            BufferedReader data = new BufferedReader(new FileReader("Data.txt"));
            String line;
            String[] mot;
            Boolean ok = false;
            while (((line = data.readLine()) != null) && (ok != true)) {
                mot = line.split(";");
                if (mot[0].equals("ZoneConstructible")) {
                    this.xmax = Double.parseDouble(mot[1]);
                    this.xmin = Double.parseDouble(mot[2]);
                    this.ymax = Double.parseDouble(mot[3]);
                    this.ymin = Double.parseDouble(mot[4]);
                    ok = true;
                }
            }
            data.close();
            if (ok = false) {
                this.xmax = 0.0;
                this.xmin = 0.0;
                this.ymax = 0.0;
                this.ymin = 0.0;
            }
        } catch (IOException e) {
            System.out.println("error getting espace data");
        }
    }

    public Espace(String line) {
        String[] mot;
        mot = line.split(";");
        if (mot[0].equals("ZoneConstructible")) {
            this.xmax = Double.parseDouble(mot[1]);
            this.xmin = Double.parseDouble(mot[2]);
            this.ymax = Double.parseDouble(mot[3]);
            this.ymin = Double.parseDouble(mot[4]);
        }
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
