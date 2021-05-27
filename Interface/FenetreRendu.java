package Interface;
// ========== CLASSE FenetreRendu ===========
//
// ouvre la fenetre permettant le rendu graphique du treillis et du terrain
//
// =============================================

import javax.swing.*;
import Terrain.LTriangle;
import Treillis.Espace;
import Treillis.LBarre;
import Treillis.LNoeud;
import java.awt.Color;
import java.awt.*;

public class FenetreRendu extends JFrame {

    private BasicStroke str = new BasicStroke(3);               // sélection de la taille des traits
    private Double adjust;                                      // parametre de scaling général permettant de s'assurer que tout le treillis soit présent à l'écran

    public FenetreRendu() {                                     // constructeur principal
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        Espace esp = new Espace();                              // importation de l'espace de construction donnant la taille de la fenetre
        Double absadjust = esp.getXmax();
        Double ordadjust = esp.getYmax();
        adjust = 1.0;
        while ((absadjust > 1920) || (ordadjust > 1080)) {      // réduit la taille de la fenetre pour une hauteur d'ecran standard de 1080px*1920px
            absadjust = absadjust*0.9;
            ordadjust = ordadjust*0.9;
            adjust = adjust*0.9;                                // met a jour la variable globale d'ajustement
        }
        while ((absadjust < 960) || (ordadjust < 540)) {        // augmente la taille de la fenetre pour une hauteur d'ecran standard de 1080px*1920px
            absadjust = absadjust*1.1;
            ordadjust = ordadjust*1.1;
            adjust = adjust*1.1;                                // met a jour la variable globale d'ajustement
        }
        int abs = (int) (esp.getXmax()*adjust);
        int ord = (int) (esp.getYmax()*adjust);
        setSize(abs, ord);                                      // choisit la taille finale de fenetre après ajustement
        setTitle("Rendu graphique");

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(new Color(225, 231, 242));                   // choisis la couleur de tracé de la grille
        for (int i = 0; i < 1920; i+=75) {                      // trace une grille en arrière plan
            g.drawLine(i, 0, i, 1080);
        }
        for (int i = 0; i < 1080; i+=75) {
            g.drawLine(0, i, 1920, i);
        }
        g2.setStroke(str);

        LTriangle tri = new LTriangle();                        // importe la liste des triangles
        for (int i = 0; i < tri.getListeTriangles(); i++) {     // fait le tracé de chaque triangle
            int xT1 = (int) Math.round(tri.getListeTriangles(i).getAbscisseT1()*adjust);
            int xT2 = (int) Math.round(tri.getListeTriangles(i).getAbscisseT2()*adjust);
            int xT3 = (int) Math.round(tri.getListeTriangles(i).getAbscisseT3()*adjust);
            int yT1 = (int) Math.round(tri.getListeTriangles(i).getOrdonneeT1()*adjust);
            int yT2 = (int) Math.round(tri.getListeTriangles(i).getOrdonneeT2()*adjust);
            int yT3 = (int) Math.round(tri.getListeTriangles(i).getOrdonneeT3()*adjust);
            int xpoints[] = { xT1, xT2, xT3 };
            int ypoints[] = { yT1, yT2, yT3 };
            int npoints = 3;
            g.setColor(new Color(221, 232, 203));               // choisis la couleur de remplissage
            g.fillPolygon(xpoints, ypoints, npoints);           // remplis le triangles dont les abscisses ont été ajustées
            g.setColor(new Color(163, 223, 180));               // choisis la couleur de contour
            g.drawPolygon(xpoints, ypoints, npoints);           // trace le contour des triangles dont les abscisses ont été ajustées
        }

        LBarre bar = new LBarre();                              // importe la liste de barres
        g.setColor(Color.black);                                // choisis la couleur
        for (int i = 0; i < bar.getListeBarres(); i++) {        // fait le tracé de chaque barre
            int x1 = (int) Math.round(bar.getlisteBarre(i).getDebut_noeud().getabscisse()*adjust);
            int y1 = (int) Math.round(bar.getlisteBarre(i).getDebut_noeud().getordonnee()*adjust);
            int x2 = (int) Math.round(bar.getlisteBarre(i).getFin_noeud().getabscisse()*adjust);
            int y2 = (int) Math.round(bar.getlisteBarre(i).getFin_noeud().getordonnee()*adjust);
            g.drawLine(x1, y1, x2, y2);                         // trace des lignes suivant les abscisses et les ordonnées ajustées
        }

        LNoeud noe = new LNoeud();                              // importe la liste de noeuds
        Image apsimple = Toolkit.getDefaultToolkit().getImage("files/appui simple.png"); // importe l'icone d'appui simple
        Image apdouble = Toolkit.getDefaultToolkit().getImage("files/appui double.png"); // importe l'icone d'appui double
        for (int i = 0; i < noe.getlisteNoeuds(); i++) {
            int x = (int) Math.round(noe.getListeNoeuds(i).getabscisse()*adjust);
            int y = (int) Math.round(noe.getListeNoeuds(i).getordonnee()*adjust);
            switch (noe.getListeNoeuds(i).getTypeSupport()) {
                case 1:                                         // si le noeud est un appui simple, fait un point noir et dessine l'icone correspondant
                    g.drawImage(apsimple,(int) (x-15),(int) (y-15), (int) (30), (int) (30), this);
                    g.setColor(Color.BLACK);
                    g.fillOval(x-(8/2), y-(8/2), 8, 8);
                    break;
                case 2:                                         // si le noeud est un appui double, fait un point noir et dessine l'icone correspondant
                    g.drawImage(apdouble,(int) (x-15),(int) (y-15), (int) (30), (int) (30), this);
                    g.setColor(Color.BLACK);
                    g.fillOval(x-(8/2), y-(8/2), 8, 8);
                    break;
                case 3:                                         // si le noeud est un noeud simple, fait un point rouge
                    g.setColor(Color.RED);
                    g.fillOval(x-(8/2), y-(8/2), 8, 8);
                    break;
                default:
                    break;
            }
        }
    }
}
