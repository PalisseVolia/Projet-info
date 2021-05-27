package FileFormat;
// ========== CLASSE Actualisenoeud ===========
//
// permet d'actualiser le type de noeud du treillis dans le fichier de données en fonction de leurs valeurs, souvent après une modification
//
// ============================================

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import Treillis.LNoeud;

public class Actualisenoeud {
    public Actualisenoeud() {
    }

    public void Appui() {                                                               //méthode permettant l'actualisation
        try {
            BufferedReader noeudr = new BufferedReader(new FileReader("Data.txt"));     // récupération du fichier texte contenant les données
            LNoeud lNoeud = new LNoeud();                                               //création d'une liste de noeuds 
            String line;
            String data = "";
            boolean dogetdata = false;
            int count = 0;
            while ((line = noeudr.readLine()) != null) {                                //pour chaque ligne
                if (line.equals("FINNOEUDS")) {                                         //voir explication dans la classe "LBarre"
                    dogetdata = false;
                }
                if (dogetdata == true) {
                    switch (lNoeud.getListeNoeuds(count).getTypeSupport()) {            //en fonction du type de support indiqué on modifie la ligne 
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
                            line = "".repeat(line.length());
                            break;
                        default:
                            break;
                    }
                    count = count + 1;
                }
                if (line.equals("NOEUDS")) {
                    dogetdata = true;
                }
                if (line.equals("") == false) {
                    data = data + line + "\n";                                          //on met toutes les lignes bout a bout
                }
            }
            noeudr.close();
            File dataf = new File("Data.txt");
            dataf.delete();
            BufferedWriter noeudw = new BufferedWriter(new FileWriter("Data.txt", true));
            noeudw.write(data);                                                         //on met a jour le fichier en remplaçant l'ancien contenu par le contenu actualisé
            noeudw.close();
        } catch (Exception e) {
            System.out.println("Erreur :le fichier n’existe pas\n ");
        }
    }
}
