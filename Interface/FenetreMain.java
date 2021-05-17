package Interface;

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

class FenetreMain extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;

    private JButton noeud, barre, triangle, compute;
    private JTextArea noeuds, barres, triangles;
    private JLabel label1, label2, label3;

    public FenetreMain() {
        setTitle("Treillis calculator 2000");
        setSize(1500,800);
        
        JPanel pane1 = new JPanel();
        pane1.setLayout(new BorderLayout());
        noeuds = new JTextArea("");
        noeuds.setPreferredSize(new Dimension(300,200));
        noeuds.setEditable(false);
        label1 = new JLabel("Noeuds :");
        label1.setFont(new Font("Arial", Font.PLAIN, 20));
        pane1.add(noeuds, BorderLayout.SOUTH);
        pane1.add(label1, BorderLayout.NORTH);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new BorderLayout());
        barres = new JTextArea("");
        barres.setPreferredSize(new Dimension(300,200));
        barres.setEditable(false);
        label2 = new JLabel("Barres :");
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        pane2.add(barres, BorderLayout.SOUTH);
        pane2.add(label2, BorderLayout.NORTH);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new BorderLayout());
        triangles = new JTextArea("");
        triangles.setPreferredSize(new Dimension(300,200));
        triangles.setEditable(false);
        label3 = new JLabel("Triangles :");
        label3.setFont(new Font("Arial", Font.PLAIN, 20));
        pane3.add(triangles, BorderLayout.SOUTH);
        pane3.add(label3, BorderLayout.NORTH);

        Container contenu = getContentPane();
        contenu.setLayout(new FlowLayout());
        contenu.add(pane3);
        contenu.add(pane1);
        contenu.add(pane2);
        
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        noeud = new JButton("noeud");
        noeud.setVisible(false);
        barre = new JButton("barre");
        barre.setVisible(false);
        triangle = new JButton("triangle");
        compute = new JButton("compute");
        bar.add(triangle);
        bar.add(noeud);
        bar.add(barre);
        bar.add(compute);
        noeud.addActionListener(this);
        barre.addActionListener(this);
        triangle.addActionListener(this);
        compute.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        //TODO: ajouter la définition de l'espace de création du treillis
        if (e.getSource() == noeud) {
            FenetreNoeud fen = new FenetreNoeud(this);
            fen.setVisible(true);
            initNoeuds(fen.getNewajout());
            noeud.setVisible(false);
            barre.setVisible(true);
            try {
                BufferedWriter data = new BufferedWriter(new FileWriter("Data.txt",true));
                data.write("NOEUDS" + "\n" + noeuds.getText() + "FINNOEUDS" + "\n");
                data.close();
            } catch (Exception err) {
                System.out.println("Erreur :\n"+err);
            }
        }
        if (e.getSource() == barre) {
            FenetreBarre fen = new FenetreBarre(this);
            fen.setVisible(true);
            initBarres(fen.getNewajout());
            barre.setVisible(false);
            triangle.setVisible(true);
            try {
                BufferedWriter data = new BufferedWriter(new FileWriter("Data.txt",true));
                data.write("BARRES" + "\n" + barres.getText() + "FINBARRES" + "\n");
                data.close();
            } catch (Exception err) {
                System.out.println("Erreur :\n"+err);
            }
        }
        if (e.getSource() == triangle) {
            FenetreTriangle fen = new FenetreTriangle(this);
            fen.setVisible(true);
            initTriangles(fen.getNewajout());
            triangle.setVisible(false);
            noeud.setVisible(true);
            try {
                File dataf = new File("Data.txt");
                dataf.delete();
                BufferedWriter data = new BufferedWriter(new FileWriter("Data.txt",true));
                data.write("TRIANGLES" + "\n" + triangles.getText() + "FINTRIANGLES" + "\n");
                data.close();
            } catch (Exception err) {
                System.out.println("Erreur :\n"+err);
            }
        }
        if (e.getSource() == compute) {
            try {
                File dataf = new File("Data.txt");
                dataf.delete();
                BufferedWriter data = new BufferedWriter(new FileWriter("Data.txt",true));
                data.write("TRIANGLES" + "\n" + triangles.getText() + "FINTRIANGLES" + "\n");
                data.write("NOEUDS" + "\n" + noeuds.getText() + "FINNOEUDS" + "\n");
                data.write("BARRES" + "\n" + barres.getText() + "FINBARRES" + "\n");
                data.close();
            } catch (Exception err) {
                System.out.println("Erreur :\n"+err);
            }
        }
    }

    private void initNoeuds(String ajout) {
        ajout = ajout.replace("error : l'abscisse ou l'ordonnée n'est pas un réel"+"\n", "");
        ajout = ajout.replace("error : veuillez entrer un identifiant de noeud"+"\n", "");
        noeuds.setText(noeuds.getText().concat(ajout));
        //TODO: actualisation du type de noeuds après changement dans le fichier txt (auto ou manuel)
    }
    private void initBarres(String ajout) {
        ajout = ajout.replace("error : deux noeuds similaires sélectionnés"+"\n", "");
        ajout = ajout.replace("error : veuillez entrer un identifiant de barre" +"\n", "");
        barres.setText(barres.getText().concat(ajout));
    }
    private void initTriangles(String ajout) {
        ajout = ajout.replace("error : l'abscisse ou l'ordonnée d'un des points n'est pas un réel"+"\n", "");
        ajout = ajout.replace("error : veuillez entrer un identifiant"+"\n", "");
        triangles.setText(triangles.getText().concat(ajout));
    }
}