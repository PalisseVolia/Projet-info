package Interface;

import javax.swing.*;
import FileFormat.Actualisenoeud;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

class FenetreMain extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JButton espace, noeud, barre, triangle, compute, importer, Appuis;
    private JTextField espconstru, path;
    private JTextArea noeuds, barres, triangles;
    private JLabel label1, label2, label3, label4, label5;

    public FenetreMain() {
        setTitle("Treillis calculator 2000");
        setSize(1500, 800);

        JPanel pane1 = new JPanel();
        pane1.setLayout(new BorderLayout());
        noeuds = new JTextArea("");
        noeuds.setPreferredSize(new Dimension(300, 200));
        noeuds.setEditable(false);
        label1 = new JLabel("Noeuds :");
        label1.setFont(new Font("Arial", Font.PLAIN, 20));
        pane1.add(noeuds, BorderLayout.SOUTH);
        pane1.add(label1, BorderLayout.NORTH);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new BorderLayout());
        barres = new JTextArea("");
        barres.setPreferredSize(new Dimension(300, 200));
        barres.setEditable(false);
        label2 = new JLabel("Barres :");
        label2.setFont(new Font("Arial", Font.PLAIN, 20));
        pane2.add(barres, BorderLayout.SOUTH);
        pane2.add(label2, BorderLayout.NORTH);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new BorderLayout());
        triangles = new JTextArea("");
        triangles.setPreferredSize(new Dimension(300, 200));
        triangles.setEditable(false);
        label3 = new JLabel("Triangles :");
        label3.setFont(new Font("Arial", Font.PLAIN, 20));
        pane3.add(triangles, BorderLayout.SOUTH);
        pane3.add(label3, BorderLayout.NORTH);

        JPanel pane4 = new JPanel();
        pane4.setLayout(new FlowLayout());
        pane4.add(pane3);
        pane4.add(pane1);
        pane4.add(pane2);

        JPanel pane5 = new JPanel();
        pane5.setLayout(new BorderLayout());
        espconstru = new JTextField();
        espconstru.setEditable(false);
        espconstru.setPreferredSize(new Dimension(150, 24));
        label4 = new JLabel("Espace de construction :");
        label4.setFont(new Font("Arial", Font.PLAIN, 20));
        pane5.add(label4, BorderLayout.NORTH);
        pane5.add(espconstru, BorderLayout.SOUTH);

        JPanel pane6 = new JPanel();
        pane6.setLayout(new BorderLayout());
        path = new JTextField();
        path.setPreferredSize(new Dimension(150, 24));
        label5 = new JLabel("Chemin d'accès pour importation :");
        label5.setFont(new Font("Arial", Font.PLAIN, 20));
        pane6.add(label5, BorderLayout.NORTH);
        pane6.add(path, BorderLayout.SOUTH);

        JPanel pane7 = new JPanel();
        pane7.setLayout(new BorderLayout());
        pane7.add(pane5, BorderLayout.WEST);
        pane7.add(pane6, BorderLayout.EAST);

        Container contenu = getContentPane();
        contenu.setLayout(new BorderLayout());
        contenu.add(pane4, BorderLayout.NORTH);
        contenu.add(pane7, BorderLayout.SOUTH);

        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        espace = new JButton("espace construction");
        triangle = new JButton("triangle");
        triangle.setVisible(false);
        noeud = new JButton("noeud");
        noeud.setVisible(false);
        Appuis = new JButton("appuis");
        Appuis.setVisible(false);
        barre = new JButton("barre");
        barre.setVisible(false);
        compute = new JButton("compute");
        importer = new JButton("IMPORTER");
        bar.add(espace);
        bar.add(triangle);
        bar.add(noeud);
        bar.add(Appuis);
        bar.add(barre);
        bar.add(compute);
        bar.add(importer);
        espace.addActionListener(this);
        triangle.addActionListener(this);
        noeud.addActionListener(this);
        barre.addActionListener(this);
        compute.addActionListener(this);
        importer.addActionListener(this);
        Appuis.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == espace) {
            FenetreEspace fen = new FenetreEspace(this);
            fen.setVisible(true);
            initEspace(fen.getNewajout());
            espace.setVisible(false);
            triangle.setVisible(true);
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
                BufferedWriter data = new BufferedWriter(new FileWriter("Data.txt", true));
                data.write("TRIANGLES" + "\n" + triangles.getText() + "FINTRIANGLES" + "\n");
                data.close();
            } catch (Exception err) {
                System.out.println("Erreur :\n" + err);
            }
        }
        if (e.getSource() == noeud) {
            FenetreNoeud fen = new FenetreNoeud(this);
            fen.setVisible(true);
            initNoeuds(fen.getNewajout());
            noeud.setVisible(false);
            Appuis.setVisible(true);
            try {
                BufferedWriter dataw = new BufferedWriter(new FileWriter("Data.txt", true));
                dataw.write("NOEUDS" + "\n" + noeuds.getText() + "FINNOEUDS" + "\n");
                dataw.close();
                Actualisenoeud actualise = new Actualisenoeud();
                actualise.Appui();
                BufferedReader datar = new BufferedReader(new FileReader("Data.txt"));
                String line;
                boolean dogetdata = false;
                noeuds.setText("");
                while ((line = datar.readLine()) != null) {
                    if (line.equals("FINNOEUDS")) {
                        dogetdata = false;
                    }
                    if (dogetdata == true) {
                        noeuds.setText(noeuds.getText().concat(line) + "\n");
                    }
                    if (line.equals("NOEUDS")) {
                        dogetdata = true;
                    }
                }
                datar.close();
            } catch (Exception err) {
                System.out.println("Erreur :\n" + err);
            }
        }
        if (e.getSource() == Appuis) {
            FenetreAppui fen = new FenetreAppui(this);
            fen.setVisible(true);
            Appuis.setVisible(false);
            barre.setVisible(true);
            try {
                boolean dogetnoeud = false;
                String ligne;
                BufferedReader datar = new BufferedReader(new FileReader("Data.txt"));
                triangles.setText("");
                noeuds.setText("");
                barres.setText("");
                while ((ligne = datar.readLine()) != null) {
                    if (ligne.equals("FINNOEUDS")) {
                        dogetnoeud = false;
                    }
                    if (dogetnoeud == true) {
                        noeuds.setText(noeuds.getText() + ligne + "\n");
                    }
                    if (ligne.equals("NOEUDS")) {
                        dogetnoeud = true;
                    }
                }
                datar.close();
            } catch (Exception err) {
                System.out.println("why tho :(");
            }
        }
        if (e.getSource() == barre) {
            FenetreBarre fen = new FenetreBarre(this);
            fen.setVisible(true);
            initBarres(fen.getNewajout());
            barre.setVisible(false);
            espace.setVisible(true);
            try {
                BufferedWriter data = new BufferedWriter(new FileWriter("Data.txt", true));
                data.write("BARRES" + "\n" + barres.getText() + "FINBARRES" + "\n");
                data.close();
            } catch (Exception err) {
                System.out.println("Erreur :\n" + err);
            }
        }
        if (e.getSource() == compute) {
            try {
                File dataf = new File("Data.txt");
                dataf.delete();
                BufferedWriter data = new BufferedWriter(new FileWriter("Data.txt", true));
                data.write(espconstru.getText() + "\n");
                data.write("TRIANGLES" + "\n" + triangles.getText() + "FINTRIANGLES" + "\n");
                data.write("NOEUDS" + "\n" + noeuds.getText() + "FINNOEUDS" + "\n");
                data.write("BARRES" + "\n" + barres.getText() + "FINBARRES" + "\n");
                data.close();
            } catch (Exception err) {
                System.out.println("Erreur :\n" + err);
            }
        }
        if (e.getSource() == importer) {
            try {
                path.setText(path.getText().replace("\\", "/").replace("\"", ""));
                BufferedReader datanew = new BufferedReader(new FileReader(path.getText()));
                String text = "";
                String ligne;
                Boolean first = true;
                while ((ligne = datanew.readLine()) != null) {
                    if (first == true) {
                        espconstru.setText(ligne);
                        first = false;
                    } else {
                        text = text.concat(ligne) + "\n";
                    }
                }
                datanew.close();
                File dataf = new File("Data.txt");
                dataf.delete();
                BufferedWriter data = new BufferedWriter(new FileWriter("Data.txt", true));
                data.write(text);
                data.close();
                boolean dogettriangle = false;
                boolean dogetnoeud = false;
                boolean dogetbarre = false;
                BufferedReader datar = new BufferedReader(new FileReader("Data.txt"));
                triangles.setText("");
                noeuds.setText("");
                barres.setText("");
                while ((ligne = datar.readLine()) != null) {
                    if (ligne.equals("FINTRIANGLES")) {
                        dogettriangle = false;
                    }
                    if (ligne.equals("FINNOEUDS")) {
                        dogetnoeud = false;
                    }
                    if (ligne.equals("FINBARRES")) {
                        dogetbarre = false;
                    }
                    if (dogettriangle == true) {
                        triangles.setText(triangles.getText() + ligne + "\n");
                    }
                    if (dogetnoeud == true) {
                        noeuds.setText(noeuds.getText() + ligne + "\n");
                    }
                    if (dogetbarre == true) {
                        barres.setText(barres.getText() + ligne + "\n");
                    }
                    if (ligne.equals("TRIANGLES")) {
                        dogettriangle = true;
                    }
                    if (ligne.equals("NOEUDS")) {
                        dogetnoeud = true;
                    }
                    if (ligne.equals("BARRES")) {
                        dogetbarre = true;
                    }
                }
                datar.close();
            } catch (Exception err) {
                System.out.println("osef" + err);
            }
        }
    }

    private void initEspace(String ajout) {
        espconstru.setText(ajout);
    }

    private void initNoeuds(String ajout) {
        ajout = ajout.replace("error : l'abscisse ou l'ordonnée n'est pas un réel" + "\n", "");
        ajout = ajout.replace("error : veuillez entrer un identifiant de noeud" + "\n", "");
        noeuds.setText(noeuds.getText().concat(ajout));
    }

    private void initBarres(String ajout) {
        ajout = ajout.replace("error : deux noeuds similaires sélectionnés" + "\n", "");
        ajout = ajout.replace("error : veuillez entrer un identifiant de barre" + "\n", "");
        barres.setText(barres.getText().concat(ajout));
    }

    private void initTriangles(String ajout) {
        ajout = ajout.replace("error : l'abscisse ou l'ordonnée d'un des points n'est pas un réel" + "\n", "");
        ajout = ajout.replace("error : veuillez entrer un identifiant" + "\n", "");
        triangles.setText(triangles.getText().concat(ajout));
    }
}