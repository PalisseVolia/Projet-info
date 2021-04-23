// =========== CLASSE LNoeud ============
//
// Récupère les valeurs des noeuds dans le fichier de donnée et les transforme en noeuds placés dans un tableau
//
// ======================================


import java.io.BufferedReader;
import java.io.FileReader;

public class LNoeud {
    private Noeud[] listenoeuds;                                                                                            //argument -> tableau de tous les noeuds du treillis
    private int nbnoeuds;                                                                                                   //argument -> nombre de noeuds dabs le treillis
    
    public LNoeud() {
        try {
            BufferedReader data = new BufferedReader(new FileReader("Data.txt"));                                           //récupération du fichier de données
            Noeud[] lNoeuds = new Noeud[500];                                                                                       
            String line;
            String[] mot,coord;
            Boolean dogetdata = false;
            int type = 0;
            int k = 0;
            while ((line=data.readLine()) != null) {                                                                        //tant qu'il n'est pas arrivé à la fin du fichier, récupère la ligne
                if (line.equals("FINNOEUDS")) {                                                                             //si à la fi nde la section noeud, arrete de récupérer les valeurs
                    dogetdata = false;
                }
                if (dogetdata == true) {                                                                                    //si dans la section noeuds
                    mot = line.split(";");
                    mot[2] = mot[2].replace("(","").replace(")", "");
                    coord = mot[2].split(",");
                    if (mot[0].equals("NoeudSimple")) {                                                                     //assigne un int en fonction du type d'appui
                        type = 3;
                    }
                    if (mot[0].equals("AppuiDouble")) {
                        type = 2;
                    }
                    if (mot[0].equals("AppuiSimple")) {
                        type = 3;
                    }
                    lNoeuds[k] = new Noeud(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]), mot[1], type);       //remplis un tableau avec les noeuds du treillis
                    k = k+1;
                    this.nbnoeuds = k;                                                                                      //actualise le nombre total de noeuds
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

    public Noeud getListeNoeuds(int i) {                                                                                    //méthode get d'un des noeuds du tableau
        return this.listenoeuds[i];
    }
    public int getlisteNoeuds() {                                                                                           //méthode get du nombre de noeuds du tableau
        return this.nbnoeuds;
    }

}
