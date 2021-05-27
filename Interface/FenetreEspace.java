package Interface;
// ========== CLASSE FenetreEspace ===========
//
// ouvre la fenetre permettant la création de l'espace de construction
//
// ===========================================

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

public class FenetreEspace extends JDialog implements ActionListener{       //JDialog, comme JFrame mais peut etre executé en modal
    
    private JButton confirm;
    private JTextField xmax, xmin, ymax, ymin, errors;
    private JLabel label1, label2, label3, label4, label5;
    private Dimension txt;
    private String newajout = "";

    public FenetreEspace(java.awt.Frame parent) {
        super(parent, true);                                                //permet d'ouvrir le jDialog en modal, ie pause la FenetreMain pdt l'execution
        setTitle("Espace de construction");
        setSize(410,180);

        //création des différents éléments graphiques de la fenetre
        txt = new Dimension(75,24);

        JPanel pane1 = new JPanel();
        pane1.setLayout(new BorderLayout());
        xmax = new JTextField();
        xmax.setPreferredSize(txt);
        label1 = new JLabel("X max");
        label1.setFont(new Font("Arial", Font.PLAIN, 15));
        pane1.add(label1, BorderLayout.NORTH);
        pane1.add(xmax, BorderLayout.SOUTH);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new BorderLayout());
        xmin = new JTextField();
        xmin.setPreferredSize(txt);
        label2 = new JLabel("X min");
        label2.setFont(new Font("Arial", Font.PLAIN, 15));
        pane2.add(label2, BorderLayout.NORTH);
        pane2.add(xmin, BorderLayout.SOUTH);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new BorderLayout());
        ymax = new JTextField();
        ymax.setPreferredSize(txt);
        label3 = new JLabel("Y max");
        label3.setFont(new Font("Arial", Font.PLAIN, 15));
        pane3.add(label3, BorderLayout.NORTH);
        pane3.add(ymax, BorderLayout.SOUTH);

        JPanel pane4 = new JPanel();
        pane4.setLayout(new BorderLayout());
        ymin = new JTextField();
        ymin.setPreferredSize(txt);
        label4 = new JLabel("Y min");
        label4.setFont(new Font("Arial", Font.PLAIN, 15));
        pane4.add(label4, BorderLayout.NORTH);
        pane4.add(ymin, BorderLayout.SOUTH);

        JPanel pane5 = new JPanel();
        pane5.setLayout(new FlowLayout());
        pane5.add(pane1);
        pane5.add(pane2);
        pane5.add(pane3);
        pane5.add(pane4);

        JPanel pane6 = new JPanel();
        pane6.setLayout(new BorderLayout());
        errors = new JTextField();
        errors.setPreferredSize(new Dimension(120,24));
        errors.setEditable(false);
        confirm = new JButton("CONFIRMER");
        label5 = new JLabel("  Veuillez entrer des réels pour les abscisses et ordonnées");
        label5.setFont(new Font("Arial", Font.PLAIN, 15));
        pane6.add(errors, BorderLayout.NORTH);
        pane6.add(confirm, BorderLayout.CENTER);
        pane6.add(label5, BorderLayout.SOUTH);
        confirm.addActionListener(this);

        Container contenu = getContentPane();
        contenu.setLayout(new BorderLayout());
        contenu.add(pane5, BorderLayout.CENTER);
        contenu.add(pane6, BorderLayout.SOUTH);
    }

    public String getNewajout() {                                   // méthode get de la liste de triangles créés
        return newajout;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirm) {                             // si on appuie sur confirmer
            boolean doerror = false;
            try {                                                   // on vérifie que les abscisses/ordonénes sont des réels
                Double test = Double.parseDouble(xmax.getText()) + Double.parseDouble(xmin.getText()) + Double.parseDouble(ymax.getText()) + Double.parseDouble(ymin.getText());
                test = test + test;
            } catch (Exception err) {
                errors.setText("                      error : l'une des coordonnées n'est pas un réel");
                doerror = true;
            }
            if (doerror == false) {                                 // si il n'y a aps d'erreur on met a jour le string sortie et le Jtextarea et on vide les donnée entrées précedemment
                newajout = "ZoneConstructible;" + xmax.getText() + ";" + xmin.getText() + ";" + ymax.getText() + ";" + ymin.getText();
                dispose();                                          // on ferme la fenetre
            }
        }
    }
}