package Treillis;
// ============ CLASSE LBarre =============
//
// Création d'une liste de toutes les barres du treillis depuis le fichier de données
//
// =======================================

import java.io.BufferedReader;
import java.io.FileReader;

public class LBarre {
    private Barre[] listebarres;                                                                // tableau des barres
    private int nbbarres;                                                                       // nombre total de barres

    public LBarre() {
        try {
            BufferedReader data = new BufferedReader(new FileReader("Data.txt"));               // récupération du fichier texte contenant les données
            Barre[] lBarre = new Barre[500];
            String line;
            String[] mot;
            Boolean dogetdata = false;
            Noeud noeud1 = new Noeud();
            Noeud noeud2 = new Noeud();
            int type = 0, k = 0;
            while ((line = data.readLine()) != null) {                                          // pour chauqe ligne du fichier
                if (line.equals("FINBARRES")) {                                                 // si on atteint le marqueur de fin de barres on arrete la création
                    dogetdata = false;
                }
                if (dogetdata == true) {                                                        // si on est entre les deux marqueurs de barre on récupère les informations
                    mot = line.split(";");                                                      // sépare la ligne en différents Strings
                    if (mot[1].equals("Pleine")) {                                              // si la barre est pleine elle prends le type 1
                        type = 1;
                    }
                    if (mot[1].equals("EnI")) {                                                 // si la barre est En I elle prends le type 2
                        type = 2;
                    }
                    LNoeud lnoeud = new LNoeud();                                               // on récupère la lsite des noeuds
                    for (int i = 0; i < lnoeud.getlisteNoeuds(); i++) {                         //pour chaque noeud on les assigne au noeud de début ou de fin de la barre si leurs identifiants correspondent
                        if (lnoeud.getListeNoeuds(i).getIdentificationN().equals(mot[2])) {
                            noeud1 = lnoeud.getListeNoeuds(i);
                        }
                        if (lnoeud.getListeNoeuds(i).getIdentificationN().equals(mot[3])) {
                            noeud2 = lnoeud.getListeNoeuds(i);
                        }
                    }
                    lBarre[k] = new Barre(noeud1, noeud2, mot[0], type);                        // on ajoute la barre dont on a déterminé les arguments au tableau de barres
                    k = k + 1;
                    this.nbbarres = k;                                                          // on incrémente le nombre total de barres
                }
                if (line.equals("BARRES")) {                                                    // si on atteint le marqueur de barres on commence la création
                    dogetdata = true;
                }
            }
            this.listebarres = lBarre;
            data.close();
        } catch (Exception e) {
            System.out.println(" Erreur :\n " + e);
        }
    }

    public Barre getlisteBarre(int i) {                                                         // méthode get permettant de récupérer la i-ème barre du tableau
        return this.listebarres[i];
    }

    public int getListeBarres() {                                                               // méthode get permettant de récupérer le nombre de barres du tableau
        return this.nbbarres;
    }

}