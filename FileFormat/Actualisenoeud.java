package FileFormat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import Treillis.LNoeud;

public class Actualisenoeud {
    public Actualisenoeud() {
    }
    public void Appui() {
        try {
            BufferedReader noeudr = new BufferedReader(new FileReader("Data.txt"));
            LNoeud lNoeud = new LNoeud();
            String line;
            String data = "";
            boolean dogetdata = false;
            int count = 0;
            while ((line = noeudr.readLine()) != null) {
                if (line.equals("FINNOEUDS")) {
                    dogetdata = false;
                }
                if (dogetdata == true) {
                    switch (lNoeud.getListeNoeuds(count).getTypeSupport()) {
                        case 1:
                        line = line.replace("Type", "AppuiSimple");
                        break;
                        case 2:
                        line = line.replace("Type", "AppuiDouble");
                        break;
                        case 3:
                        line = line.replace("Type", "NoeudSimple");
                        break;
                        case 4:
                        line = line.replace("Type", "Appui");
                        break;
                        case 5:
                        line = line.replace("Type", "delete");
                        break;
                        default:
                        break;
                    }
                    count = count + 1;
                }
                if (line.equals("NOEUDS")) {
                    dogetdata = true;
                }
                data = data + line + "\n";
            }
            noeudr.close();
            File dataf = new File("Data.txt");
            dataf.delete();
            BufferedWriter noeudw = new BufferedWriter(new FileWriter("Data.txt",true));
            noeudw.write(data);
            noeudw.close();
        }
        catch (Exception e) {
            System.out.println( "Erreur :le fichier n’existe pas\n ");
        }
    }
}
