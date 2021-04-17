package Interface;

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

public class Fenetre {
    public static void main(String[] args) {
        FenetreMain fenetrem = new FenetreMain();
        fenetrem.setVisible(true);
    }
}

class FenetreMain extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;

    private JButton noeud;
    private JTextArea noeuds;
    private JLabel label1;

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

        Container contenu = getContentPane();
        contenu.setLayout(new FlowLayout());
        contenu.add(pane1);
        
        JMenuBar bar = new JMenuBar();
        setJMenuBar(bar);
        noeud = new JButton("noeud");
        bar.add(noeud);
        noeud.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == noeud) {
            FenetreNoeud fen = new FenetreNoeud(this);
            fen.setVisible(true);
            initNoeuds(fen.getNewajout());
        }  
    }

    private void initNoeuds(String ajout) {
        noeuds.setText(noeuds.getText().concat(ajout));
    }
}

class FenetreNoeud extends JDialog implements ActionListener{   //JDialog, comme JFrame mais peut etre executé en modal
    private static final long serialVersionUID = 1L;

    private JButton add, close;
    private JTextField abscisse, ordonnee, identification, type;
    private JLabel label1, label2, label3, label4;
    private JTextArea noeuds;
    private Dimension txt;
    private String newajout = "";

    public FenetreNoeud(java.awt.Frame parent){
        super(parent, true);                                    //permet d'ouvrir le jDialog en modal, ie pause la FenetreMain pdt l'execution de la FenetreNoeud
        setTitle("Ajout de noeud");
        setSize(500,300);
        
        txt = new Dimension(75,24);
        noeuds = new JTextArea("");
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
        type = new JTextField();
        type.setPreferredSize(txt);
        label4 = new JLabel("Type");
        label4.setFont(new Font("Arial", Font.PLAIN, 15));
        pane4.add(label4, BorderLayout.NORTH);
        pane4.add(type, BorderLayout.SOUTH);

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
            newajout = newajout + "("+ abscisse.getText() + "," + ordonnee.getText() + ")      " + identification.getText() + "      " + type.getText() + "\n";
            abscisse.setText("");
            ordonnee.setText("");
            identification.setText("");
            type.setText("");
            noeuds.setText(newajout);
        }
        if (e.getSource() == close) {
            dispose();
        }
    }
}