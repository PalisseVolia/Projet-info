package Interface;

import javax.swing.JDialog;

import Treillis.LNoeud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FenetreAppui extends JDialog implements ActionListener {

    private JButton valider;
    private JTextField identification;
    private JCheckBox asimple, adouble;
    LNoeud lnoeud = new LNoeud();
    int i = 0;

    public FenetreAppui(java.awt.Frame parent) {
        super(parent, true);
        setTitle("Détermination du type d'appui");
        setSize(500, 140);

        JPanel pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        identification = new JTextField();
        identification.setPreferredSize(new Dimension(100, 24));
        identification.setText(lnoeud.getListeNoeuds(i).getIdentificationN());
        asimple = new JCheckBox("Simple");
        adouble = new JCheckBox("Double");
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
        if (e.getSource() == valider) {
            if (asimple.isSelected() == true) {
                lnoeud.setAppui(i, 1);
            }
            if (adouble.isSelected() == true) {
                lnoeud.setAppui(i, 2);
            }
            i = i + 1;
            if (i < lnoeud.getlisteNoeuds()) { // TODO: ne faire apparaitre que les noeuds appuis
                identification.setText(lnoeud.getListeNoeuds(i).getIdentificationN());
            } else {
                dispose();
            }
        }
        if (e.getSource() == asimple) {
            if (asimple.isSelected() == true) {
                adouble.setSelected(false);
            }
        }
        if (e.getSource() == adouble) {
            if (adouble.isSelected() == true) {
                asimple.setSelected(false);
            }
        }
    }
}