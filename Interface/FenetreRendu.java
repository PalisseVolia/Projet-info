package Interface;

import javax.swing.*;

import Terrain.LTriangle;
import Treillis.Espace;
import Treillis.LBarre;
import Treillis.LNoeud;
import java.awt.Color;
import java.awt.*;

public class FenetreRendu extends JFrame {

    private BasicStroke str = new BasicStroke(3);
    private Double adjust;

    public FenetreRendu() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        Espace esp = new Espace();
        Double absadjust = esp.getXmax();
        Double ordadjust = esp.getYmax();
        adjust = 1.0;
        while ((absadjust > 1920) || (ordadjust > 1080)) {
            absadjust = absadjust*0.9;
            ordadjust = ordadjust*0.9;
            adjust = adjust*0.9;
        }
        while ((absadjust < 960) || (ordadjust < 540)) {
            absadjust = absadjust*1.1;
            ordadjust = ordadjust*1.1;
            adjust = adjust*1.1;
        }
        int abs = (int) (esp.getXmax()*adjust);
        int ord = (int) (esp.getYmax()*adjust);
        setSize(abs, ord);
        setTitle("Rendu graphique");

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(new Color(225, 231, 242));
        for (int i = 0; i < 1920; i+=75) {
            g.drawLine(i, 0, i, 1080);
        }
        for (int i = 0; i < 1080; i+=75) {
            g.drawLine(0, i, 1920, i);
        }
        g2.setStroke(str);

        LTriangle tri = new LTriangle();
        for (int i = 0; i < tri.getListeTriangles(); i++) {
            int xT1 = (int) Math.round(tri.getListeTriangles(i).getAbscisseT1()*adjust);
            int xT2 = (int) Math.round(tri.getListeTriangles(i).getAbscisseT2()*adjust);
            int xT3 = (int) Math.round(tri.getListeTriangles(i).getAbscisseT3()*adjust);
            int yT1 = (int) Math.round(tri.getListeTriangles(i).getOrdonneeT1()*adjust);
            int yT2 = (int) Math.round(tri.getListeTriangles(i).getOrdonneeT2()*adjust);
            int yT3 = (int) Math.round(tri.getListeTriangles(i).getOrdonneeT3()*adjust);
            int xpoints[] = { xT1, xT2, xT3 };
            int ypoints[] = { yT1, yT2, yT3 };
            int npoints = 3;
            g.setColor(new Color(221, 232, 203));
            g.fillPolygon(xpoints, ypoints, npoints);
            g.setColor(new Color(163, 223, 180));
            g.drawPolygon(xpoints, ypoints, npoints);
        }

        LBarre bar = new LBarre();
        g.setColor(Color.black);
        for (int i = 0; i < bar.getListeBarres(); i++) {
            int x1 = (int) Math.round(bar.getlisteBarre(i).getDebut_noeud().getabscisse()*adjust);
            int y1 = (int) Math.round(bar.getlisteBarre(i).getDebut_noeud().getordonnee()*adjust);
            int x2 = (int) Math.round(bar.getlisteBarre(i).getFin_noeud().getabscisse()*adjust);
            int y2 = (int) Math.round(bar.getlisteBarre(i).getFin_noeud().getordonnee()*adjust);
            g.drawLine(x1, y1, x2, y2);
        }

        LNoeud noe = new LNoeud();
        Image apsimple = Toolkit.getDefaultToolkit().getImage("files/appui simple.png");
        Image apdouble = Toolkit.getDefaultToolkit().getImage("files/appui double.png");
        for (int i = 0; i < noe.getlisteNoeuds(); i++) {
            int x = (int) Math.round(noe.getListeNoeuds(i).getabscisse()*adjust);
            int y = (int) Math.round(noe.getListeNoeuds(i).getordonnee()*adjust);
            switch (noe.getListeNoeuds(i).getTypeSupport()) {
                case 1:
                    g.drawImage(apsimple,(int) (x-10*adjust),(int) (y-10*adjust), (int) (20*adjust), (int) (20*adjust), this);
                    g.setColor(Color.BLACK);
                    g.fillOval(x-(8/2), y-(8/2), 8, 8);
                    break;
                case 2:
                    g.drawImage(apdouble,(int) (x-10*adjust),(int) (y-10*adjust), (int) (20*adjust), (int) (20*adjust), this);
                    g.setColor(Color.BLACK);
                    g.fillOval(x-(8/2), y-(8/2), 8, 8);
                    break;
                case 3:
                    g.setColor(Color.RED);
                    g.fillOval(x-(8/2), y-(8/2), 8, 8);
                    break;
                default:
                    break;
            }
        }
    }
}
