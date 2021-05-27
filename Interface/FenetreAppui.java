package Interface;
// ========== CLASSE FenetreAppui ===========
//
// ouvre la fenetre permettant la détermination du type d'appui
//
// ==========================================

import javax.swing.JDialog;
import Treillis.LNoeud;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FenetreAppui extends JDialog implements ActionListener {           //JDialog, comme JFrame mais peut etre executé en modal

    private JButton valider;
    private JTextField identification;
    private JCheckBox asimple, adouble;
    LNoeud lnoeud = new LNoeud();
    int i = 0;

    public FenetreAppui(java.awt.Frame parent) {
        super(parent, true);                                                    //permet d'ouvrir le jDialog en modal, ie pause la FenetreMain pdt l'execution
        setTitle("Détermination du type d'appui");
        setSize(500, 140);

        //création des différents éléments graphiques de la fenetre
        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        identification = new JTextField();
        identification.setPreferredSize(new Dimension(100, 24));
        identification.setEditable(false);
        while (lnoeud.getListeNoeuds(i).getTypeSupport() != 4) {
            i = i + 1;
        }
        identification.setText(lnoeud.getListeNoeuds(i).getIdentificationN());
        asimple = new JCheckBox("Simple");
        adouble = new JCheckBox("Double");
        adouble.setSelected(true);
        asimple.addActionListener(this);
        adouble.addActionListener(this);
        pane1.add(identification);
        pane1.add(asimple);
        pane1.add(adouble);

        Container contenu = getContentPane();
        contenu.setLayout(new BorderLayout());
        valider = new JButton("VALIDER");
        valider.addActionListener(this);
        contenu.add(pane1, BorderLayout.CENTER);
        contenu.add(valider, BorderLayout.SOUTH);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == valider) {
                if (i <= lnoeud.getlisteNoeuds() - 1) {
                    if (asimple.isSelected() == true) {                                                 // si la case "simple" est sélectionnée met le type du noeud a 1 <=> simple
                        lnoeud.getListeNoeuds(i).setTypeSupport(1);
                    }
                    if (adouble.isSelected() == true) {
                        lnoeud.getListeNoeuds(i).setTypeSupport(2);
                    }
                    if (i < lnoeud.getlisteNoeuds()) {                                                  // si la case "double" est sélectionnée met le type du noeud a 2 <=> double
                        while (lnoeud.getListeNoeuds(i).getTypeSupport() != 4) {
                            i = i + 1;
                            identification.setText(lnoeud.getListeNoeuds(i).getIdentificationN());      // actualise l'identifiant du noeud dans le jtextfield au premier noud d'appui indéterminé (4)
                        }
                    }
                } else {
                    Appui();                                                                            // Actualise le fichier de données (voir classe "Actualisenoeud")
                    dispose();                                                                          // ferme la fenetre
                }
            }
        } catch (Exception err) {                                                                       // en cas d'erreur
            Appui();                                                                                    // Actualise le fichier de données (voir classe "Actualisenoeud")
            dispose();                                                                                  // ferme la fenetre
        }
        if (e.getSource() == asimple) {                                                                 // si la case simple est cochée, décoche la case double
            if (asimple.isSelected() == true) {
                adouble.setSelected(false);
            }
        }
        if (e.getSource() == adouble) {                                                                 // si la case double est cochée, décoche la case simple
            if (adouble.isSelected() == true) {
                asimple.setSelected(false);
            }
        }
    }

    public void Appui() {                                                                               //voir classe Actualisenoeud (copie)
        try {
            BufferedReader noeudr = new BufferedReader(new FileReader("Data.txt"));
            String line;
            String data = "";
            boolean dogetdata = false;
            int count = 0;
            while ((line = noeudr.readLine()) != null) {
                if (line.equals("FINNOEUDS")) {
                    dogetdata = false;
                }
                if (dogetdata == true) {
                    switch (lnoeud.getListeNoeuds(count).getTypeSupport()) {
                        case 1:
                            line = line.replace("Appui", "AppuiSimple");
                            break;
                        case 2:
                            line = line.replace("Appui", "AppuiDouble");
                            break;
                        case 3:
                            line = line.replace("Type", "NoeudSimple");
                            break;
                        case 4:
                            line = line.replace("Type", "Appui");
                            break;
                        case 5:
                            line = "".repeat(line.length());
                            break;
                        default:
                            break;
                    }
                    count = count + 1;
                }
                if (line.equals("NOEUDS")) {
                    dogetdata = true;
                }
                if (line.equals("") == false) {
                    data = data + line + "\n";
                }
            }
            noeudr.close();
            File dataf = new File("Data.txt");
            dataf.delete();
            BufferedWriter noeudw = new BufferedWriter(new FileWriter("Data.txt", true));
            noeudw.write(data);
            noeudw.close();
        } catch (Exception e) {
            System.out.println("Erreur :le fichier n’existe pas\n ");
        }
    }
}