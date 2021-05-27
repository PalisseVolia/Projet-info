import Interface.FenetreMain;
// ============ CLASSE Main =============

//Permet l'éxecution du programme

// ======================================

public class Main {
    public static void main(String[] args) {
        // Démarrer le programme
        Start();
    }

    public static void Start() {
        FenetreMain fenetrem = new FenetreMain(); // crée la fenetre principale
        fenetrem.setVisible(true); // la rend visible
    }
}