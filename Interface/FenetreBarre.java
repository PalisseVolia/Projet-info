package Interface;
// ========== CLASSE FenetreBarre ===========
//
// ouvre la fenetre permettant la création de barres
//
// =============================================

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;
import Treillis.LNoeud;

public class FenetreBarre extends JDialog implements ActionListener {       //JDialog, comme JFrame mais peut etre executé en modal

    private JButton add, close;
    private JTextField identification;
    private JLabel label1, label2, label3, label4, label5;
    private JTextArea barres;
    private JComboBox<String> type, noeud1, noeud2;
    private Dimension txt, box;
    private String newajout ="";

    public FenetreBarre(java.awt.Frame parent){
        super(parent, true);                                                //permet d'ouvrir le jDialog en modal, ie pause la FenetreMain pdt l'execution
        setTitle("Ajout de barre");
        setSize(500,300);

        //création des différents éléments graphiques de la fenetre
        txt = new Dimension(75,24);
        box = new Dimension(100,24);
        barres = new JTextArea("");
        barres.setEditable(false);
        barres.setPreferredSize(new Dimension(300,200));

        JPanel pane1 = new JPanel();
        pane1.setLayout(new BorderLayout());
        identification = new JTextField();
        identification.setPreferredSize(txt);
        label1 = new JLabel("Nom");
        label1.setFont(new Font("Arial", Font.PLAIN, 15));
        pane1.add(label1, BorderLayout.NORTH);
        pane1.add(identification, BorderLayout.SOUTH);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new BorderLayout());
        String[] section = {"Pleine","EnI"};
        type = new JComboBox<>(section);
        type.setPreferredSize(box);
        label2 = new JLabel("Section");
        label2.setFont(new Font("Arial", Font.PLAIN, 15));
        pane2.add(label2, BorderLayout.NORTH);
        pane2.add(type, BorderLayout.SOUTH);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new BorderLayout());
        LNoeud lnoeud = new LNoeud();
        String[] noeuds = new String[lnoeud.getlisteNoeuds()];
        for (int i = 0; i < lnoeud.getlisteNoeuds(); i++) {
            noeuds[i] = lnoeud.getListeNoeuds(i).getIdentificationN();
        }
        noeud1 = new JComboBox<>(noeuds);
        noeud1.setPreferredSize(box);
        label3 = new JLabel("Noeud 1");
        label3.setFont(new Font("Arial", Font.PLAIN, 15));
        pane3.add(label3, BorderLayout.NORTH);
        pane3.add(noeud1, BorderLayout.SOUTH);

        JPanel pane4 = new JPanel();
        pane4.setLayout(new BorderLayout());
        noeud2 = new JComboBox<>(noeuds);
        noeud2.setPreferredSize(box);
        label4 = new JLabel("Noeud 1");
        label4.setFont(new Font("Arial", Font.PLAIN, 15));
        pane4.add(label4, BorderLayout.NORTH);
        pane4.add(noeud2, BorderLayout.SOUTH);

        JPanel pane5 = new JPanel();
        pane5.setLayout(new FlowLayout());
        pane5.add(pane1);
        pane5.add(pane2);
        pane5.add(pane3);
        pane5.add(pane4);

        JPanel pane6 = new JPanel();
        pane6.setLayout(new BorderLayout());
        add = new JButton("ADD");
        close = new JButton("Close");
        label5 = new JLabel("               Veuillez choisir deux noeuds différents");
        label5.setFont(new Font("Arial", Font.PLAIN, 15));
        pane6.add(label5, BorderLayout.CENTER);
        pane6.add(add, BorderLayout.EAST);
        pane6.add(close, BorderLayout.WEST);
        add.addActionListener(this);
        close.addActionListener(this);

        Container contenu = getContentPane();
        contenu.setLayout(new BorderLayout());
        contenu.add(pane5, BorderLayout.NORTH);
        contenu.add(barres, BorderLayout.CENTER);
        contenu.add(pane6, BorderLayout.SOUTH);
}
    public String getNewajout() {                           // méthode get de la liste de triangles créés
        return newajout;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {                         // si on appuie sur add
            boolean doerror = false;                        // on vérifie que deux noeuds similaires ne sont pas sélectionnés et qu'un identifiant a bien été entré
            if (noeud1.getItemAt(noeud1.getSelectedIndex()).equals(noeud2.getItemAt(noeud2.getSelectedIndex()))) {
                newajout = newajout + "error : deux noeuds similaires sélectionnés"  + "\n";
                barres.setText(newajout);
                doerror = true;
            }
            if (identification.getText().equals("")) {
                newajout = newajout + "error : veuillez entrer un identifiant de barre"  + "\n";
                barres.setText(newajout);
                doerror = true;
            }
            if (doerror == false) {                         // si il n'y a aps d'erreur on met a jour le string sortie et le Jtextarea et on vide les donnée entrées précedemment
                newajout = newajout + identification.getText() + ";" + type.getItemAt(type.getSelectedIndex()) + ";" + noeud1.getItemAt(noeud1.getSelectedIndex()) + ";" + noeud2.getItemAt(noeud2.getSelectedIndex()) + "\n";
                identification.setText("");
                barres.setText(newajout);
            }
        }
        if (e.getSource() == close) {                       //si on appuie sur close on ferme la fenetre
            dispose();
        }
    }
}