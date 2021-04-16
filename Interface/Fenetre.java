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
        noeuds = new JTextArea("");
        noeuds.setPreferredSize(new Dimension(300,200));
        noeuds.setEditable(false);
        label1 = new JLabel("Noeuds");
        pane1.add(noeuds);
        pane1.add(label1);

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
    private JTextField abscisse, ordonnee;
    private Dimension txt;
    private String newajout = "";
    private JTextArea noeuds;

    public FenetreNoeud(java.awt.Frame parent){
        super(parent, true);                                    //permet d'ouvrir le jDialog en modal, ie pause la FenetreMain pdt l'execution de la FenetreNoeud
        setTitle("Ajout de noeud");
        setSize(500,300);
        
        txt = new Dimension(75,24);
        add = new JButton("ADD");
        close = new JButton("Close");
        abscisse = new JTextField();
        ordonnee = new JTextField();
        noeuds = new JTextArea("");
        abscisse.setPreferredSize(txt);
        ordonnee.setPreferredSize(txt);
        noeuds.setPreferredSize(new Dimension(300,200));

        Container contenu = getContentPane();
        contenu.setLayout(new FlowLayout());
        contenu.add(add);
        contenu.add(abscisse);
        contenu.add(ordonnee);
        contenu.add(close);
        contenu.add(noeuds);
        add.addActionListener(this);
        abscisse.addActionListener(this);
        ordonnee.addActionListener(this);
        close.addActionListener(this);
    }

    public String getNewajout() {
        return newajout;
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String x = abscisse.getText();
            String y = ordonnee.getText();
            newajout = newajout + x + "," + y + "\n";
            abscisse.setText("");
            ordonnee.setText("");
            noeuds.setText(newajout);
        }
        if (e.getSource() == close) {
            dispose();
        }
    }
}