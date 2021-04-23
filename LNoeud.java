import java.io.BufferedReader;
import java.io.FileReader;

public class LNoeud {
    private Noeud[] listenoeuds;
    
    public LNoeud() {
        try {
            BufferedReader data = new BufferedReader(new FileReader("Data.txt"));
            Noeud[] lNoeuds = new Noeud[500];
            String line;
            String[] mot,coord;
            Boolean dogetdata = false;
            int type = 0;
            int k = 0;
            while ((line=data.readLine()) != null) {
                if (line.equals("FINNOEUDS")) {
                    dogetdata = false;
                }
                if (dogetdata == true) {
                    mot = line.split(";");
                    mot[2] = mot[2].replace("(","").replace(")", "");
                    coord = mot[2].split(",");
                    if (mot[0].equals("NoeudSimple")) {
                        type = 3;
                    }
                    if (mot[0].equals("AppuiDouble")) {
                        type = 2;
                    }
                    if (mot[0].equals("AppuiSimple")) {
                        type = 3;
                    }
                    lNoeuds[k] = new Noeud(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]), mot[1], type);
                    k = k+1;
                }
                if (line.equals("NOEUDS")) {
                    dogetdata = true;
                }
            }
            this.listenoeuds = lNoeuds;
            data.close();
        } catch (Exception err) {
            System.out.println(" Erreur :\n "+err);
        }
    }

    public Noeud getListeNoeuds(int i) {
        return this.listenoeuds[i];
    }

}
