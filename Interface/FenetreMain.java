package Interface;

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

class FenetreMain extends JFrame implements ActionListener{
    private static final long serialVersionUID = 1L;

    private JButton noeud, compute;
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
        compute = new JButton("compute");
        bar.add(noeud);
        bar.add(compute);
        noeud.addActionListener(this);
        compute.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == noeud) {
            FenetreNoeud fen = new FenetreNoeud(this);
            fen.setVisible(true);
            initNoeuds(fen.getNewajout());
        }  
        if (e.getSource() == compute) {
            try {
                File dataf = new File("Data.txt");
                dataf.delete();
                BufferedWriter data = new BufferedWriter(new FileWriter("Data.txt",true));
                data.write("NOEUDS" + "\n" + noeuds.getText() + "FINNOEUDS");
                data.close();
            } catch (Exception err) {
                System.out.println("Erreur :\n"+err);
            }
        }
    }

    private void initNoeuds(String ajout) {
        noeuds.setText(noeuds.getText().concat(ajout));
    }
}