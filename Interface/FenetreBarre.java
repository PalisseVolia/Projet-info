package Interface;

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

public class FenetreBarre extends JDialog implements ActionListener {

    private JButton add, close;
    private JTextField identification;
    private JLabel label1, label2, label3, label4, label5;
    private JTextArea barres;
    private JComboBox<String> type, noeud1, noeud2;
    private Dimension txt, box;
    private String newajout ="";

    public FenetreBarre(java.awt.Frame parent){
        super(parent, true);                                    //permet d'ouvrir le jDialog en modal, ie pause la FenetreMain pdt l'execution de la FenetreNoeud
        setTitle("Ajout de barre");
        setSize(500,300);

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
        String[] section = {"Pleine","En I"};
        type = new JComboBox<>(section);
        type.setPreferredSize(box);
        label2 = new JLabel("Section");
        label2.setFont(new Font("Arial", Font.PLAIN, 15));
        pane2.add(label2, BorderLayout.NORTH);
        pane2.add(type, BorderLayout.SOUTH);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new BorderLayout());
        String[] noeuds = {"test1","test2"};
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
    public String getNewajout() {   
        return newajout;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            boolean doerror = false;
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
            if (doerror == false) {
                newajout = newajout + identification.getText() + ";" + type.getItemAt(type.getSelectedIndex()) + ";" + noeud1.getItemAt(noeud1.getSelectedIndex()) + ";" + noeud2.getItemAt(noeud2.getSelectedIndex()) + "\n";
                identification.setText("");
                barres.setText(newajout);
            }
        }
        if (e.getSource() == close) {
            dispose();
        }
    }
}