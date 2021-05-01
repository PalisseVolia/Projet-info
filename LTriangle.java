// ========== CLASSE LTriangle ===========
//
// Récupère les valeurs des triangles dans le fichier de donnée et les transforme en triangles placés dans un tableau
//
// =======================================


import java.io.BufferedReader;
import java.io.FileReader;

public class LTriangle {
    private Triangle[] listetriangles;                                                                                      //argument -> tableau de tous les noeuds du treillis
    private int nbtriangles;                                                                                                //argument -> nombre de noeuds dabs le treillis

    public LTriangle() {
        try {
            BufferedReader data = new BufferedReader(new FileReader("Data.txt"));                                           //récupération du fichier de données
            Triangle[] lTriangle = new Triangle[500];                                                                                       
            String line;
            String[] mot,coord1, coord2, coord3;
            Boolean dogetdata = false;
            int k = 0;
            while ((line=data.readLine()) != null) {                                                                        //tant qu'il n'est pas arrivé à la fin du fichier, récupère la ligne
                if (line.equals("FINTRIANGLES")) {                                                                          //si à la fin de la section triangles, arrete de récupérer les valeurs
                    dogetdata = false;
                }
                if (dogetdata == true) {                                                                                    //si dans la section triangles
                    mot = line.split(";");
                    mot[1] = mot[1].replace("(","").replace(")", "");
                    mot[2] = mot[2].replace("(","").replace(")", "");
                    mot[3] = mot[3].replace("(","").replace(")", "");
                    coord1 = mot[1].split(",");
                    coord2 = mot[2].split(",");
                    coord3 = mot[3].split(",");
                    lTriangle[k] = new Triangle(mot[0], Double.parseDouble(coord1[0]), Double.parseDouble(coord2[0]), Double.parseDouble(coord3[0]), Double.parseDouble(coord1[1]), Double.parseDouble(coord2[1]), Double.parseDouble(coord3[1]));
                    k = k+1;
                    this.nbtriangles = k;                                                                                   //actualise le nombre total de triangles
                }
                if (line.equals("TRIANGLES")) {                                                                             //si début de la section triangles commence a récupérer des valeurs
                    dogetdata = true;
                }
            }
            this.listetriangles = lTriangle;
            data.close();                                                                                                   //fermeture du BufferredReader
        } catch (Exception err) {
            System.out.println(" Erreur :\n "+err);
        }
    }

    public Triangle getListeTriangles(int i) {                                                                              //méthode get d'un des triangles du tableau
        return this.listetriangles[i];
    }
    public int getListeTriangles() {                                                                                        //méthode get du nombre de triangles du tableau
        return this.nbtriangles;
    }
}
