package Treillis;

import java.io.BufferedReader;
import java.io.FileReader;

public class LBarre {
    private Barre[] listebarres;
    private int nbbarres;

    public LBarre() {
        try {
            BufferedReader data = new BufferedReader(new FileReader("Data.txt"));
            Barre[] lBarre = new Barre[500];
            String line;
            String[] mot;
            Boolean dogetdata = false;
            Noeud noeud1 = new Noeud();
            Noeud noeud2 = new Noeud();
            int type = 0,k = 0;
            while ((line=data.readLine()) != null) {
                if (line.equals("FINBARRES")) {
                    dogetdata = false;
                }
                if (dogetdata == true) {
                    mot = line.split(";");
                    if (mot[1].equals("Pleine")) {
                        type = 1;
                    }
                    if (mot[1].equals("EnI")) {
                        type = 2;
                    }
                    LNoeud lnoeud = new LNoeud();
                    for (int i = 0; i < lnoeud.getlisteNoeuds(); i++) {
                        if (lnoeud.getListeNoeuds(i).getIdentificationN().equals(mot[2])) {
                            noeud1 = lnoeud.getListeNoeuds(i);
                        }
                        if (lnoeud.getListeNoeuds(i).getIdentificationN().equals(mot[3])) {
                            noeud2 = lnoeud.getListeNoeuds(i);
                        }
                    }
                    lBarre[k] = new Barre(noeud1, noeud2, mot[0], type);
                    k = k+1;
                    this.nbbarres = k;
                }
                if (line.equals("BARRES")) {
                    dogetdata = true;
                }
            }
            this.listebarres = lBarre;
            data.close();
        } catch (Exception e) {
            System.out.println(" Erreur :\n "+e);
        }
    }

    public Barre getlisteBarre(int i) {
        return this.listebarres[i];
    }
    public int getListeNoeuds() {
        return this.nbbarres;
    }
    
}