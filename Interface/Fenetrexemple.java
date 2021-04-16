package Interface;

import javax.swing.* ;
import java.awt.* ;
import java.awt.event.* ;
// Profil Fenetre 1 *****************
class Fenetre1 extends JFrame implements ActionListener
{
  private static final long serialVersionUID = 1L;
public Fenetre1 ()
  { setTitle ("Fenetre1") ;
    setSize (300, 200) ;
    monBouton1 = new JButton ("Fenetre1") ;
    monBouton2 = new JButton ("Fenetre2") ;
    annuler = new JButton ("annuler");
    Container contenu = getContentPane() ;
    contenu.setLayout(new FlowLayout()) ;
    contenu.add(annuler);
    contenu.add(monBouton1) ;
    contenu.add(monBouton2) ;
    monBouton1.addActionListener(this);
    monBouton2.addActionListener(this);
    annuler.addActionListener(this);
  }
  
  public void actionPerformed (ActionEvent ev)
  { 
    if (ev.getSource() == monBouton1)
	  {
      //System.out.println ("action sur bouton numero 1") ;
	  Fenetre1 fen1 = new Fenetre1() ;
      fen1.setVisible(true) ;
	  }
    if (ev.getSource() == monBouton2)
			  {
      //  System.out.println ("action sur bouton numero 2") ;
	  Fenetre2 fen2 = new Fenetre2() ;
      fen2.setVisible(true) ;
	  }
    if (ev.getSource() == annuler) {
      dispose();
    }
  }
  private JButton monBouton1, monBouton2, annuler ;
}
//  Fin Profil Fenetre1

// Profil Fenetre 2 *****************
class Fenetre2 extends JFrame implements ActionListener
{ /**
   *
   */
  private static final long serialVersionUID = 1L;
public Fenetre2 ()
  { setTitle ("Fenetre2") ;
    setSize (600, 300) ;
	setLocation (350, 0) ;
    monBouton1 = new JButton ("Bouton A") ;
    monBouton2 = new JButton ("Bouton B") ;
    Container contenu = getContentPane() ;
    contenu.setLayout(new FlowLayout()) ;
    contenu.add(monBouton1) ;
    contenu.add(monBouton2) ;
    monBouton1.addActionListener(this);
    monBouton2.addActionListener(this);
  }
  
  public void actionPerformed (ActionEvent ev)
  { if (ev.getSource() == monBouton1)
      System.out.println ("action sur bouton numero 1") ;
    if (ev.getSource() == monBouton2)
      System.out.println ("action sur bouton numero 2") ;
  }
  private JButton monBouton1, monBouton2 ;
}
//  Fin Profil Fenetre2

public class Fenetrexemple
{ public static void main (String args[])
  { Fenetre1 fen = new Fenetre1() ;
    fen.setVisible(true) ;
  }
}
