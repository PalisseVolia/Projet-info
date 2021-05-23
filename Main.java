import Interface.FenetreMain;

public class Main {
    public static void main(String[] args) {
        
        //Démarrer le programme
        Start();
        //TODO: triangles qui disparaissent après un tour complet ?!

        // LNoeud lnoeud = new LNoeud();
        // System.out.println(lnoeud.getListeNoeuds(4).getTypeSupport());
        // System.out.println(lnoeud.getListeNoeuds(0).getTypeSupport());
        // for (int i = 0; i < lnoeud.getlisteNoeuds(); i++) {
        // System.out.println("N" + (i+1));
        // System.out.println("Type: " + lnoeud.getListeNoeuds(i).getTypeSupport());
        // System.out.println("triangle: " +
        // lnoeud.getListeNoeuds(i).gettriangleappui());
        // System.out.println("cote: " + lnoeud.getListeNoeuds(i).gettrianglecote());
        // System.out.println("===========================");
        // }
        // System.out.println(lnoeud.getlisteNoeuds());
        // LBarre lbarre = new LBarre();
        // System.out.println(lbarre.getlisteBarre(1).getFin_noeud().getIdentificationN());
        // System.out.println(lbarre.getListeNoeuds());
        // LTriangle ltriangle = new LTriangle();
        // System.out.println(ltriangle.getListeTriangles(0).getOrdonneeT2());
        // System.out.println(ltriangle.getListeTriangles());
        // Actualisenoeud actualise = new Actualisenoeud();
        // actualise.Appui();
        // Espace space = new Espace("ZoneConstructible;1;2;3;4");
        // System.out.println(space.getYmax());

        // LNoeud noe = new LNoeud(); //tu crée une liste des noeuds du treillis
        // int i = 0; //tu fera sans doute autrement mais en gros c'est un int qui vas déterminer quel noeud tu récupère dans la liste
        // i = noe.getlisteNoeuds(); //ce code te retourne le nombre de noeuds dans le treillis ça vas sans doute t'etre utile pour un for par exemple
        // int cote = noe.getListeNoeuds(i).gettrianglecote(); //ça te renvoie sur quel coté de triangle le noeud est appuyé (1 pour le premier, 2 deuxième ect)   /!\ ça te renvoie 0 si c'est pas un appui
        // int ntri = noe.getListeNoeuds(i).gettriangleappui(); //ça te renvoie le numéro du triangle sur lequel le noeud est appuyé
        // LTriangle tri = new LTriangle(); //tu crée la liste des triangles du terrain
        // tri.getListeTriangles(ntri).getAbscisseT1(); //te renvoie l'abscisse du premier point du triangle appelé T1 =>
        //                                              //les points sont associés comme ça par rapport aux cotés coté 1 = T1/T2, coté 2 = T2/T3, coté 3 = T3/T1
        // tri.getListeTriangles(ntri).getOrdonneeT2(); //de al mm manière tu peux obtenir l'ordonnée du point T2 par exemple
    }

    public static void Start() {
            FenetreMain fenetrem = new FenetreMain();
            fenetrem.setVisible(true);
    }
}