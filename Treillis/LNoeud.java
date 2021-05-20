package Treillis;
// =========== CLASSE LNoeud ============
//
// Récupère les valeurs des noeuds dans le fichier de donnée et les transforme en noeuds placés dans un tableau
//
// ======================================


import java.io.BufferedReader;
import java.io.FileReader;
import Terrain.LTriangle;
import Terrain.Triangle;

public class LNoeud {
    private Noeud[] listenoeuds;                                                                                                //argument -> tableau de tous les noeuds du treillis
    private int nbnoeuds;                                                                                                       //argument -> nombre de noeuds dabs le treillis
    
    public LNoeud() {
        try {
            BufferedReader data = new BufferedReader(new FileReader("Data.txt"));                                               //récupération du fichier de données
            Noeud[] lNoeuds = new Noeud[500];                                                                                       
            String line;
            String[] mot,coord;
            Boolean dogetdata = false;
            int k = 0;
            while ((line=data.readLine()) != null) {                                                                            //tant qu'il n'est pas arrivé à la fin du fichier, récupère la ligne
                if (line.equals("FINNOEUDS")) {                                                                                 //si à la fin de la section noeud, arrete de récupérer les valeurs
                    dogetdata = false;
                }
                if (dogetdata == true) {                                                                                        //si dans la section noeuds
                    mot = line.split(";");
                    mot[2] = mot[2].replace("(","").replace(")", "");
                    coord = mot[2].split(",");
                    lNoeuds[k] = new Noeud(Double.parseDouble(coord[0]), Double.parseDouble(coord[1]), mot[1], 3, 0, 0);        //remplis un tableau avec les noeuds du treillis
                    k = k+1;
                    Appui(lNoeuds, nbnoeuds);                                                                                   //détermine les différents types d'appuis en fonction de la position des noeuds dans le terrain
                    this.nbnoeuds = k;                                                                                          //actualise le nombre total de noeuds
                }
                if (line.equals("NOEUDS")) {                                                                                    //si début de la section noeud commence a récupérer des valeurs
                    dogetdata = true;
                }
            }
            this.listenoeuds = lNoeuds;                                                                                     
            data.close();                                                                                                       //fermeture du BufferredReader
        } catch (Exception err) {
            System.out.println(" Erreur :\n "+err);
        }
    }

    public static void Appui(Noeud[] lNoeud, int nbnoeuds) {                                                                    //méthode de détermination des types d'appuis
        LTriangle tri = new LTriangle();                                                                                        //importation de la liste des triangles
        int nbtriangles = tri.getListeTriangles();                                                                              //get nombre de triangles de la liste
        for (int i = 0; i < nbnoeuds+1; i++) {                                                                                  //pour chaque noeud
            if (lNoeud[i].getTypeSupport() == 3) {
                Double abscheck = lNoeud[i].getabscisse();                                                                      //abscisse du noeud a checker
                Double ordcheck = lNoeud[i].getordonnee();                                                                      //ordonnée du noeud a checker
                int j = 0;
                boolean found = false;                                                                                          //indicateur de résultat
                while ((j < nbtriangles)&&(found != true)) {//pour chaque triangles et tant qu'on n'a pas trouvé de résultat
                    Triangle ctri = tri.getListeTriangles(j);
                    Double[] equa = new Double[3];                                                                              //tableau contenant les coefficiants a, b et c d'un équation paramétrique de droite ax+by+c=0
                    equa[0] = -(ctri.getAbscisseT2()-ctri.getAbscisseT1());                                                     //b
                    equa[1] = ctri.getOrdonneeT2()-ctri.getOrdonneeT1();                                                        //a
                    equa[2] = -(equa[1]*ctri.getAbscisseT1() + equa[0]*ctri.getOrdonneeT1());                                   //c
                    Double pt = equa[1]*ctri.getAbscisseT3() + equa[0]*ctri.getOrdonneeT3() + equa[2];                          //ax+by+c pour le troisième point du triangle
                    Double ptcheck = equa[1]*abscheck + equa[0]*ordcheck + equa[2];                                             //ax+by+c pour les coordonnées du noeud
                    if ((ptcheck < 0.001)&&(ptcheck > -0.001)) {                                                                //si le noeud appartiens au coté 1 à 0.002 près
                        if (((abscheck-ctri.getAbscisseT1())*(abscheck-ctri.getAbscisseT2()) + (ordcheck-ctri.getOrdonneeT1())*(ordcheck-ctri.getOrdonneeT2())) <= 0) {
                            lNoeud[i].setTypeSupport(4);                                                                        //le noeud est un appui dont le type n'a pas encore été déterminé
                            lNoeud[i].settriangleappui(j);                                                                      //le noeud s'appuie sur le triangle N°J
                            lNoeud[i].settrianglecote(1);                                                                       //le noeud s'appuie sur le coté N°1
                            found = true;                                                                                       //la solution est trouvée
                        }
                    }
                    if ((((pt>0)&&(ptcheck>0))||((pt<0)&&(ptcheck<0)))&&(found == false)) {//si pt et ptcheck sont de meme signe les deux points sont du meme coté de la droite 1
                        equa[0] = -(ctri.getAbscisseT3()-ctri.getAbscisseT2());                                                 //comme au dessus mais pour la prochaine droite
                        equa[1] = ctri.getOrdonneeT3()-ctri.getOrdonneeT2();
                        equa[2] = -(equa[1]*ctri.getAbscisseT2() + equa[0]*ctri.getOrdonneeT2());
                        pt = equa[1]*ctri.getAbscisseT1() + equa[0]*ctri.getOrdonneeT1() + equa[2];
                        ptcheck = equa[1]*abscheck + equa[0]*ordcheck + equa[2];
                        if ((ptcheck < 0.001)&&(ptcheck > -0.001)) {                                                            //si le noeud appartiens au coté 2 à 0.002 près
                            if (((abscheck-ctri.getAbscisseT2())*(abscheck-ctri.getAbscisseT3()) + (ordcheck-ctri.getOrdonneeT2())*(ordcheck-ctri.getOrdonneeT3())) <= 0) {
                                lNoeud[i].setTypeSupport(4);                                                                    //le noeud est un appui dont le type n'a pas encore été déterminé
                                lNoeud[i].settriangleappui(j);                                                                  //le noeud s'appuie sur le triangle N°J
                                lNoeud[i].settrianglecote(2);                                                                   //le noeud s'appuie sur le coté N°2
                                found = true;
                            }
                        }
                        if ((((pt>0)&&(ptcheck>0))||((pt<0)&&(ptcheck<0)))&&(found == false)) {//si pt et ptcheck sont de meme signe les deux points sont du meme coté de la droite 2
                            equa[0] = -(ctri.getAbscisseT1()-ctri.getAbscisseT3());                                             //comme au dessus mais pour la prochaine droite
                            equa[1] = ctri.getOrdonneeT1()-ctri.getOrdonneeT3();
                            equa[2] = -(equa[1]*ctri.getAbscisseT3() + equa[0]*ctri.getOrdonneeT3());
                            pt = equa[1]*ctri.getAbscisseT2() + equa[0]*ctri.getOrdonneeT2() + equa[2];
                            ptcheck = equa[1]*abscheck + equa[0]*ordcheck + equa[2];
                            if ((ptcheck < 0.001)&&(ptcheck > -0.001)) {                                                        //si le noeud appartiens au coté 3 à 0.002 près
                                if (((abscheck-ctri.getAbscisseT3())*(abscheck-ctri.getAbscisseT1()) + (ordcheck-ctri.getOrdonneeT3())*(ordcheck-ctri.getOrdonneeT1())) <= 0) {
                                    lNoeud[i].setTypeSupport(4);                                                                //le noeud est un appui dont le type n'a pas encore été déterminé
                                    lNoeud[i].settriangleappui(j);                                                              //le noeud s'appuie sur le triangle N°J
                                    lNoeud[i].settrianglecote(3);                                                               //le noeud s'appuie sur le coté N°3
                                    found = true;
                                }
                            }
                            if ((((pt>0)&&(ptcheck>0))||((pt<0)&&(ptcheck<0)))&&(found == false)) {                             //si pt et ptcheck sont de meme signe les deux points sont du meme coté de la droite 3
                                lNoeud[i].setTypeSupport(5);                                                                    //le noeud est dans le triangle terrain, il est donc invalide
                                found = true;
                            }
                        }
                    }
                    if (found == false) {                                                                                       //si le noeud n'appartiens ni a un coté de triangle ni à un triangle
                        lNoeud[i].setTypeSupport(3);                                                                            //le type du noeud est noeud simple
                    }
                    j++;                                                                                                        //incrément de triangle
                }
            }
        }
    }

    public Noeud getListeNoeuds(int i) {                                                                                        //méthode get d'un des noeuds du tableau
        return this.listenoeuds[i];
    }
    public int getlisteNoeuds() {                                                                                               //méthode get du nombre de noeuds du tableau
        return this.nbnoeuds;
    }
    public void setAppui(int i, int appui) {
        this.listenoeuds[i].setTypeSupport(appui);
    }
}
