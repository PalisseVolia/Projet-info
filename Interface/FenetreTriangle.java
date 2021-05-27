package Interface;
// ========== CLASSE FenetreTriangle ===========
//
// ouvre la fenetre permettant la création de triangles
//
// =============================================

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;

public class FenetreTriangle extends JDialog implements ActionListener {                    //JDialog, comme JFrame mais peut etre executé en modal

    private JButton add, close;
    private JTextField abscisse1, abscisse2, abscisse3, ordonnee1, ordonnee2, ordonnee3, identification;
    private JLabel label1, label2, label3, label4, label5;
    private JTextArea triangles;
    private Dimension txt;
    private String newajout = "";

    public FenetreTriangle(java.awt.Frame parent) {
        super(parent, true);                                                                //permet d'ouvrir le jDialog en modal, ie pause la FenetreMain pdt l'execution
        setTitle("Ajout de Triangle de terrain");
        setSize(500,300);

        //création des différents éléments graphiques de la fenetre
        txt = new Dimension(50,24);
        triangles = new JTextArea("");
        triangles.setEditable(false);
        triangles.setPreferredSize(new Dimension(300,200));

        JPanel pane1 = new JPanel();
        pane1.setLayout(new BorderLayout());
        identification = new JTextField();
        identification.setPreferredSize(txt);
        label1 = new JLabel("Identification");
        label1.setFont(new Font("Arial", Font.PLAIN, 15));
        pane1.add(label1, BorderLayout.NORTH);
        pane1.add(identification, BorderLayout.SOUTH);

        JPanel pane2 = new JPanel();
        pane2.setLayout(new BorderLayout());
        label2 = new JLabel(" Sommet 1");
        label2.setFont(new Font("Arial", Font.PLAIN, 15));
        JPanel sub1 = new JPanel();
        sub1.setLayout(new FlowLayout());
        abscisse1 = new JTextField();
        ordonnee1 = new JTextField();
        abscisse1.setPreferredSize(txt);
        ordonnee1.setPreferredSize(txt);
        sub1.add(abscisse1);
        sub1.add(ordonnee1);
        pane2.add(label2, BorderLayout.NORTH);
        pane2.add(sub1, BorderLayout.SOUTH);

        JPanel pane3 = new JPanel();
        pane3.setLayout(new BorderLayout());
        label3 = new JLabel(" Sommet 2");
        label3.setFont(new Font("Arial", Font.PLAIN, 15));
        JPanel sub2 = new JPanel();
        sub2.setLayout(new FlowLayout());
        abscisse2 = new JTextField();
        ordonnee2 = new JTextField();
        abscisse2.setPreferredSize(txt);
        ordonnee2.setPreferredSize(txt);
        sub2.add(abscisse2);
        sub2.add(ordonnee2);
        pane3.add(label3, BorderLayout.NORTH);
        pane3.add(sub2, BorderLayout.SOUTH);

        JPanel pane4 = new JPanel();
        pane4.setLayout(new BorderLayout());
        label4 = new JLabel(" Sommet 3");
        label4.setFont(new Font("Arial", Font.PLAIN, 15));
        JPanel sub3 = new JPanel();
        sub3.setLayout(new FlowLayout());
        abscisse3 = new JTextField();
        ordonnee3 = new JTextField();
        abscisse3.setPreferredSize(txt);
        ordonnee3.setPreferredSize(txt);
        sub3.add(abscisse3);
        sub3.add(ordonnee3);
        pane4.add(label4, BorderLayout.NORTH);
        pane4.add(sub3, BorderLayout.SOUTH);

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
        contenu.add(triangles, BorderLayout.CENTER);
        contenu.add(pane6, BorderLayout.SOUTH);
    }

    public String getNewajout() {                       // méthode get de la liste de triangles créés
        return newajout;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {                     // si on appuie sur add
            boolean doerror = false;
            try {                                       // on vérifie que les abscisses/ordonénes sont des réels
                Double test = Double.parseDouble(abscisse1.getText()) + Double.parseDouble(abscisse2.getText()) + Double.parseDouble(abscisse3.getText()) + Double.parseDouble(ordonnee1.getText()) + Double.parseDouble(ordonnee2.getText()) + Double.parseDouble(ordonnee3.getText());
                test = test + test;
            } catch (Exception err) {
                newajout = newajout + "error : l'abscisse ou l'ordonnée d'un des points n'est pas un réel" + "\n";
                triangles.setText(newajout);
                doerror = true;
            }
            if (identification.getText().equals("")) {  // on vérifie qu'on a bien entré un identifiant
                newajout = newajout + "error : veuillez entrer un identifiant" + "\n";
                triangles.setText(newajout);
                doerror = true;
            }
            if (doerror == false) {                     // si il n'y a aps d'erreur on met a jour le string sortie et le Jtextarea et on vide les donnée entrées précedemment
                newajout = newajout + identification.getText() + ";(" + abscisse1.getText() + "," + ordonnee1.getText() + ");(" + abscisse2.getText() + "," + ordonnee2.getText() + ");("  + abscisse3.getText() + "," + ordonnee3.getText() + ")" + "\n";
                identification.setText("");
                abscisse1.setText("");
                abscisse2.setText("");
                abscisse3.setText("");
                ordonnee1.setText("");
                ordonnee2.setText("");
                ordonnee3.setText("");
                triangles.setText(newajout);
            }
        }
        if (e.getSource() == close) {                   //si on appuie sur close on ferme la fenetre
            dispose();
        }
    }
    
}
