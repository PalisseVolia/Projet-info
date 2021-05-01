public class Main {
    public static void main(String[] args) {
        // récupérer des informations sur tous les noeuds depuis la liste/le nb de noeuds:
        LNoeud lnoeud = new LNoeud();
        //System.out.println(lnoeud.getListeNoeuds(0).getTypeSupport());
        for (int i = 0; i < lnoeud.getlisteNoeuds(); i++) {
            System.out.println(lnoeud.getListeNoeuds(i).getTypeSupport());
        }
        // System.out.println(lnoeud.getlisteNoeuds());
        // LBarre lbarre = new LBarre();
        // System.out.println(lbarre.getlisteBarre(1).getFin_noeud().getIdentificationN());
        // System.out.println(lbarre.getListeNoeuds());
        // LTriangle ltriangle = new LTriangle();
        // System.out.println(ltriangle.getListeTriangles(0).getOrdonneeT2());
        // System.out.println(ltriangle.getListeTriangles());
    }
}