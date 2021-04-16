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

    public FenetreMain() {
        setTitle("Treillis calculator 2000");
        setSize(1500,800);
        //txt = new Dimension(75,24);
        bar = new JMenuBar();
        close = new JButton("Close");
        noeud = new JButton("noeud");
        refresh = new JButton("refresh");
        noeuds = new JTextField();
        noeuds.setPreferredSize(new Dimension(300,200));
        noeuds.setEditable(false);
        Container contenu = getContentPane();
        contenu.setLayout(new FlowLayout());
        contenu.add(close);
        contenu.add(noeuds);
        contenu.add(refresh);
        close.addActionListener(this);
        noeud.addActionListener(this);
        refresh.addActionListener(this);
        setJMenuBar(bar);
        bar.add(noeud);

        fen = new FenetreNoeud();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == close) {
            dispose();
        }
        if (e.getSource() == noeud) {
            fen.setVisible(true);
        }
        if (e.getSource() == refresh) {
            initNoeuds();
        }
    }

    public void initNoeuds() {
        noeuds.setText(noeuds.getText().concat(fen.getNewajout()));
        fen.setNewajout();
    }

    //private Dimension txt;
    private JButton close, noeud, refresh;
    private JMenuBar bar;
    private JTextField noeuds;
    private FenetreNoeud fen;
}

class FenetreNoeud extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;

    public FenetreNoeud(){
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

    public String getNewajout(){
        return newajout;
    }
    public void setNewajout(){
        this.newajout = "";
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            String x = abscisse.getText();
            String y = ordonnee.getText();
            newajout = x + "," + y;
            abscisse.setText("");
            ordonnee.setText("");
            setVisible(false);
        }
    }
    
    private JButton add;
    private JTextField abscisse, ordonnee;
    private Dimension txt;
    private String newajout;
}