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
    //private Dimension txt;
    private JButton close, noeud;
    private JMenuBar bar;
    private JTextField noeuds;

    public FenetreMain() {
        setTitle("Treillis calculator 2000");
        setSize(1500,800);
        //txt = new Dimension(75,24);
        bar = new JMenuBar();
        close = new JButton("Close");
        noeud = new JButton("noeud");
        noeuds = new JTextField();
        noeuds.setPreferredSize(new Dimension(300,200));
        noeuds.setEditable(false);
        Container contenu = getContentPane();
        contenu.setLayout(new FlowLayout());
        contenu.add(close);
        contenu.add(noeuds);
        close.addActionListener(this);
        noeud.addActionListener(this);
        setJMenuBar(bar);
        bar.add(noeud);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            dispose();
        }
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

class FenetreNoeud extends JDialog implements ActionListener{
    private static final long serialVersionUID = 1L;
    private JButton add;
    private JTextField abscisse, ordonnee;
    private Dimension txt;
    private String newajout;

    public FenetreNoeud(java.awt.Frame parent){
        super(parent, true); //permet d'ouvrir le jDialog en modal
        setTitle("Ajout de noeud");
        setSize(500,300);
        txt = new Dimension(75,24);
        add = new JButton("Compute");
        abscisse = new JTextField();
        ordonnee = new JTextField();
        abscisse.setPreferredSize(txt);
        ordonnee.setPreferredSize(txt);
        Container contenu = getContentPane();
        contenu.setLayout(new FlowLayout());
        contenu.add(add);
        contenu.add(abscisse);
        contenu.add(ordonnee);
        add.addActionListener(this);
        abscisse.addActionListener(this);
        ordonnee.addActionListener(this);
    }

    public String getNewajout() {
        return newajout;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String x = abscisse.getText();
            String y = ordonnee.getText();
            this.newajout = x + "," + y;
            dispose();
        }
    }
}