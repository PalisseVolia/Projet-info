package Treillis;
// ============ CLASSE Barre =============
//
//Création de l'espace constructible du terrain, définis par une abscisse max, une ordonnée max, abscisse min et une ordonnée min
//
// =======================================

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Espace {
    private double xmax; // abscisse max
    private double xmin; // abscisse min
    private double ymax; // ordonnée max
    private double ymin; // ordonnée min

    public Espace() {                                                               // constructeur principal
        try {
            BufferedReader data = new BufferedReader(new FileReader("Data.txt"));   // récupération du fichier texte contenant les données
            String line;
            String[] mot;
            Boolean ok = false;
            while (((line = data.readLine()) != null) && (ok != true)) {            // pour chaque ligne
                mot = line.split(";");                                              // sépare la ligne en différents Strings
                if (mot[0].equals("ZoneConstructible")) {
                    this.xmax = Double.parseDouble(mot[1]);                         // donne les bonnes valeurs aux arguments
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

    public Espace(String line) {                                                    // constructeur permettant de récupérer les données depuis un string
        String[] mot;
        mot = line.split(";");
        if (mot[0].equals("ZoneConstructible")) {
            this.xmax = Double.parseDouble(mot[1]);                                 // donne les bonnes valeurs aux arguments
            this.xmin = Double.parseDouble(mot[2]);
            this.ymax = Double.parseDouble(mot[3]);
            this.ymin = Double.parseDouble(mot[4]);
        }
    }

    // méthodes get
    public double getXmax() {
        return xmax;
    }

    public double getXmin() {
        return xmin;
    }

    public double getYmax() {
        return ymax;
    }

    public double getYmin() {
        return ymin;
    }
}
