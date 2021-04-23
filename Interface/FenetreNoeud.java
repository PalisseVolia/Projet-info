package Interface;

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

class FenetreNoeud extends JDialog implements ActionListener{   //JDialog, comme JFrame mais peut etre executé en modal
    private static final long serialVersionUID = 1L;

    private JButton add, close;
    private JTextField abscisse, ordonnee, identification;
    private JLabel label1, label2, label3, label4, label5;
    private JTextArea noeuds;
    private JComboBox<String> type;
    private Dimension txt;
    private String newajout = "";

    public FenetreNoeud(java.awt.Frame parent){
        super(parent, true);                                    //permet d'ouvrir le jDialog en modal, ie pause la FenetreMain pdt l'execution de la FenetreNoeud
        setTitle("Ajout de noeud");
        setSize(500,300);
        
        txt = new Dimension(75,24);
        noeuds = new JTextArea("");
        noeuds.setEditable(false);
        noeuds.setPreferredSize(new Dimension(300,200));

        JPanel pane1 = new JPanel();
        pane1.setLayout(new BorderLayout());
        abscisse = new JTextField();
        abscisse.setPreferredSize(txt);
        label1 = new JLabel("Abscisse");
        label1.setFont(new Font("Arial", Font.PLAIN, 15));
        pane1.add(label1, BorderLayout.NORTH);
        pane1.add(abscisse, BorderLayout.SOUTH);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new BorderLayout());
        ordonnee = new JTextField();
        ordonnee.setPreferredSize(txt);
        label2 = new JLabel("Ordonnee");
        label2.setFont(new Font("Arial", Font.PLAIN, 15));
        pane2.add(label2, BorderLayout.NORTH);
        pane2.add(ordonnee, BorderLayout.SOUTH);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new BorderLayout());
        identification = new JTextField();
        identification.setPreferredSize(txt);
        label3 = new JLabel("Nom");
        label3.setFont(new Font("Arial", Font.PLAIN, 15));
        pane3.add(label3, BorderLayout.NORTH);
        pane3.add(identification, BorderLayout.SOUTH);

        JPanel pane4 = new JPanel();
        pane4.setLayout(new BorderLayout());
        String[] appuis = {"AppuiSimple","AppuiDouble","NoeudSimple"};
        type = new JComboBox<>(appuis);
        type.setPreferredSize(new Dimension(100,24));
        label4 = new JLabel("Type");
        label4.setFont(new Font("Arial", Font.PLAIN, 15));
        pane4.add(label4, BorderLayout.NORTH);
        pane4.add(type, BorderLayout.SOUTH);

        JPanel pane5 = new JPanel();
        pane5.setLayout(new FlowLayout());
        pane5.add(pane4);
        pane5.add(pane3);
        pane5.add(pane1);
        pane5.add(pane2);

        JPanel pane6 = new JPanel();
        pane6.setLayout(new BorderLayout());
        add = new JButton("ADD");
        close = new JButton("Close");
        label5 = new JLabel("  Veuillez entrer des réels pour l'abscisse et l'ordonnée");
        label5.setFont(new Font("Arial", Font.PLAIN, 15));
        pane6.add(label5, BorderLayout.CENTER);
        pane6.add(add, BorderLayout.EAST);
        pane6.add(close, BorderLayout.WEST);
        add.addActionListener(this);
        close.addActionListener(this);
        
        Container contenu = getContentPane();
        contenu.setLayout(new BorderLayout());
        contenu.add(pane5, BorderLayout.NORTH);
        contenu.add(noeuds, BorderLayout.CENTER);
        contenu.add(pane6, BorderLayout.SOUTH);
    }

    public String getNewajout() {
        return newajout;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            boolean doerror = false;
            try {
                Double test = Double.parseDouble(abscisse.getText() + Double.parseDouble(ordonnee.getText()));
                test = test + test;
            } catch (Exception err) {
                newajout = newajout + "error : l'abscisse ou l'ordonnée n'est pas un réel"  + "\n";
                noeuds.setText(newajout);
                doerror = true;
            }
            if (doerror == false) {
                newajout = newajout + type.getItemAt(type.getSelectedIndex()) + ";" + identification.getText() + ";(" + abscisse.getText() + "," + ordonnee.getText() + ")" + "\n";
                abscisse.setText("");
                ordonnee.setText("");
                identification.setText("");
                noeuds.setText(newajout);
            }
        }
        if (e.getSource() == close) {
            dispose();
        }
    }
}