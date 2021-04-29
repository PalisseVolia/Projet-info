import java.io.BufferedReader;
import java.io.FileReader;

public class LTriangle {
    private Triangle[] listetriangles;
    private int nbtriangles;

    public LTriangle() {
        try {
            BufferedReader data = new BufferedReader(new FileReader("Data.txt"));
            Triangle[] lTriangle = new Triangle[500];                                                                                       
            String line;
            String[] mot,coord1, coord2, coord3;
            Boolean dogetdata = false;
            int k = 0;
            while ((line=data.readLine()) != null) {
                if (line.equals("FINTRIANGLES")) {
                    dogetdata = false;
                }
                if (dogetdata == true) {
                    mot = line.split(";");
                    mot[1] = mot[1].replace("(","").replace(")", "");
                    mot[2] = mot[2].replace("(","").replace(")", "");
                    mot[3] = mot[3].replace("(","").replace(")", "");
                    coord1 = mot[1].split(",");
                    coord2 = mot[2].split(",");
                    coord3 = mot[3].split(",");
                    lTriangle[k] = new Triangle(mot[0], Double.parseDouble(coord1[0]), Double.parseDouble(coord2[0]), Double.parseDouble(coord3[0]), Double.parseDouble(coord1[1]), Double.parseDouble(coord2[1]), Double.parseDouble(coord3[1]));
                    k = k+1;
                    this.nbtriangles = k;
                }
                if (line.equals("TRIANGLES")) {
                    dogetdata = true;
                }
            }
            this.listetriangles = lTriangle;
            data.close();
        } catch (Exception err) {
            System.out.println(" Erreur :\n "+err);
        }
    }

    public Triangle getListeTriangles(int i) {
        return this.listetriangles[i];
    }
    public int getListeTriangles() {
        return this.nbtriangles;
    }
}
