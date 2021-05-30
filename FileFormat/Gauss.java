package FileFormat;
import java.util.ArrayList;
import Treillis.LNoeud;
import Treillis.Noeud;
import Treillis.LBarre;
import Terrain.LTriangle;
import Terrain.Triangle;

public class Gauss { // permet d'inverser une matrice 
	public ArrayList<ArrayList<Double>> M; //matrice 
	public LBarre lBarre = new LBarre();
	public LTriangle lTriangle = new LTriangle();
	public LNoeud lnoeud = lBarre.lnoeud;

	public Gauss() {
		M = new ArrayList<ArrayList<Double>>(); // crée la matrice M 
	}

	public ArrayList<ArrayList<Double>> echLigne(int i, int j) { // permet d'échanger deux lignes 
		ArrayList<Double> temp = M.get(i); // ligne tampon 
		M.set(i, M.get(j)); 
		M.set(i, temp);
		return M;
	}

	public int pivot(int i) { // cherche le plus grand pivot 
		int n = M.size(); //soit n le nombre de ligne
		int j = i; 
		for (int k = i+1; k < n; k++) { // pour toutes les colonnes 
			if(i<M.get(k).size()&&i<M.get(j).size()){
				if (Math.abs(M.get(k).get(i)) > Math.abs(M.get(j).get(i))) { // si la valeur lu est plus grande que précedemment 
					j = k;
				}
			}
		}
		return j; // plus grand pivot 
	}

	public ArrayList<ArrayList<Double>> elimine(int i, int j) { // effectue les opérations nécessaires pour elinminer les coeffs devant autres que pivot 
		if(i<M.get(j).size()&&i<M.get(i).size()){
			double a = -M.get(j).get(i) / M.get(i).get(i); //
			for (int k = 0; k < M.get(j).size(); k++) { // pour tout les coefficients 
			M.get(j).set(k, M.get(j).get(k) + M.get(i).get(k) * a); // soustrait le coeff de la case pour optenir un 0
			}
		}
		return M; // matrice diagonale
	}

	public ArrayList<ArrayList<Double>> normaliseDiagonale() { //change les pivots en 1
		for (int i = 0; i < M.size(); i++) { // pour toute la matrice
			if(i<M.get(i).size()){
				double temp = M.get(i).get(i); // coeff de la case
				for (int j = 0; j < M.get(i).size(); j++) {
				M.get(i).set(j, M.get(i).get(j) / temp); // divise par lui même donc 1
				}
			}
		}
		return M; 
	}

	public ArrayList<ArrayList<Double>> pGauss(ArrayList<ArrayList<Double>> A) {
		this.M = A;
		int n = M.size();
		for (int i = 0; i < n-1; i++) {// On parcours chaque ligne de la matrice M
			int p = pivot(i);// On cherche le plus grand pivot
			if (i != p) {
				echLigne(i, p);// échange la première ligne par la ligne qui contient le plus grand pivot
			}
			for (int j = i + 1; j < n; j++) {
				elimine(i, j);// Elimine tout les coefficients en dessous des pivots
			}
		}
		normaliseDiagonale();// Met les pivots en 1
		for (int i = n - 1; i > 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				elimine(i, j);// élimine les coefficients au dessus des pivots
			}
		}
		return M;
	}

	public static ArrayList<ArrayList<Double>> remplitmatrice(LBarre lBarre) {// méthode pour remplir la matrice 
		ArrayList<String> col = new ArrayList<String>(); 
		LNoeud list= lBarre.lnoeud;
		ArrayList<ArrayList<Double>> M = new ArrayList<ArrayList<Double>>(); // Matrice
		for (int i = 0; i < list.getlisteNoeuds(); i++) { //pour chaque noeud de la liste des noeuds
			Noeud n = list.getListeNoeuds(i); // on appelle n le noeud 
			for (int j = 0; j < n.getTabbar().size(); j++) { // pour toutes les barres
				if (!col.contains(n.getTabbar().get(j))) { // si la liste col ne contient pas la barre alors on l'ajoute 
					col.add(n.getTabbar().get(j));
				}
			}
			if (n.getTypeSupport() == 1) { //si appui simple 
				col.add("R" + n.getIdentificationN());
			}
			if (n.getTypeSupport() == 2) { // si appui double
				col.add("Rx" + n.getIdentificationN());
				col.add("Ry" + n.getIdentificationN());
			}
		}
		System.out.println(col+"\n");
		double x2 = 0.0;
		double y2 = 0.0;
		double x1 = 0.0;
		double y1 = 0.0;
		for (int i = 0; i < list.getlisteNoeuds(); i++) { // pour chaque noeud de la liste 
			Noeud n = list.getListeNoeuds(i);
			ArrayList<Double> Ligne1 = new ArrayList<Double>(); // création d'une nouvelle ligne 
			ArrayList<Double> Ligne2 = new ArrayList<Double>(); // de même
			for (int a = 0; a < col.size(); a++) { // on remplit au depart la matrice par des 0
				Ligne1.add(0.0);
				Ligne2.add(0.0);
			}
			if (n.getTypeSupport() == 1) { // si appui simple
				for (int j = 0; j < n.getTabbar().size(); j++) { // pour toutes les barres du noeud
					for (int a = 0; a < col.size(); a++) { // pour la taille de la colonne 
						if (n.getTabbar().get(j) == col.get(a)) { // si la barre correspond a la colonne 
							x1 = n.getabscisse();
							y1 = n.getordonnee();
							for (int k = 0; k < lBarre.getListeBarres(); k++) { // pour le nombre de barre 
								if (lBarre.getlisteBarre(k).getIdentificationB() == n.getTabbar().get(j)) { // si la barre étudiée
									if (n.getIdentificationN() == lBarre.getlisteBarre(k).getDebut_noeud().getIdentificationN()) { // si le noeud correspond au debut de la barre
										x2 = lBarre.getlisteBarre(k).getFin_noeud().getabscisse();
										y2 = lBarre.getlisteBarre(k).getFin_noeud().getordonnee();
									} else {
										x2 = lBarre.getlisteBarre(k).getDebut_noeud().getabscisse();
										y2 = lBarre.getlisteBarre(k).getDebut_noeud().getordonnee();
									}
								}
							}
							System.out.println("x1: "+x1+", x2:"+x2+", y1: "+y1+", y2: "+y2);
							double angle = trouve_angle(x2, x1, 1000.0, y2, y1, y1); // on calcule l'angle entre l'horizontale et la barre 
							System.out.println(angle);
							Ligne1.set(a, Math.cos(angle));
							System.out.println(Ligne1);
							Ligne2.set(a, Math.sin(trouve_angle(x2, x1, 1000.0, y2, y1, y1))); //on calcule le sin entre la barre et l'horrizontale
						}
						if (("R" + n.getIdentificationN()) == col.get(a)) { // si le nom correspond à la colone
							LTriangle lTriangle = new LTriangle();
							Triangle t = lTriangle.getListeTriangles(n.gettriangleappui());
							if (n.gettrianglecote() == 1) { // si le noeud est sur le côté 1
								x1 = t.getAbscisseT1(); 
								y1 = t.getOrdonneeT1();
								x2 = t.getAbscisseT2();
								y2 = t.getOrdonneeT2();
								Ligne1.set(a, Math.cos(trouve_beta(x1, x2, y1, y2))); // permet de trouver le cosinus par rapport au côté du terrain 
								Ligne2.set(a, Math.sin(trouve_beta(x1, x2, y1, y2))); // permet de trouver le sinus par rapport au côté du terrain 
							}
							if (n.gettrianglecote() == 2) { // si le noeud est sur le côté 2
								x1 = t.getAbscisseT2();
								y1 = t.getOrdonneeT2();
								x2 = t.getAbscisseT3();
								y2 = t.getOrdonneeT3();
								Ligne1.set(a, Math.cos(trouve_beta(x1, x2, y1, y2))); // permet de trouver le cosinus par rapport au côté du terrain
								Ligne2.set(a, Math.sin(trouve_beta(x1, x2, y1, y2))); // permet de trouver le sinus par rapport au côté du terrain
							}
							if (n.gettrianglecote() == 3) { // si le noeud est sur le côté 3
								x1 = t.getAbscisseT3();
								y1 = t.getOrdonneeT3();
								x2 = t.getAbscisseT1();
								y2 = t.getOrdonneeT1();
								Ligne1.set(a, Math.cos(trouve_beta(x1, x2, y1, y2))); // permet de trouver le cosinus par rapport au côté du terrain
								Ligne2.set(a, Math.sin(trouve_beta(x1, x2, y1, y2))); // permet de trouver le sinus par rapport au côté du terrain
							}
						}
					}
				}
			}
			if (n.getTypeSupport() == 2) { // si appui double
				for (int j = 0; j < n.getTabbar().size(); j++) {
					for (int a = 0; a < col.size(); a++) {
						if (n.getTabbar().get(j) == col.get(a)) {
							x1 = n.getabscisse();
							y1 = n.getordonnee();
							for (int k = 0; k < lBarre.getListeBarres(); k++) {
								if (lBarre.getlisteBarre(k).getIdentificationB() == n.getTabbar().get(j)) {
									if (n.getIdentificationN() == lBarre.getlisteBarre(k).getDebut_noeud()
											.getIdentificationN()) {
										x2 = lBarre.getlisteBarre(k).getFin_noeud().getabscisse();
										y2 = lBarre.getlisteBarre(k).getFin_noeud().getordonnee();
									} else {
										x2 = lBarre.getlisteBarre(k).getDebut_noeud().getabscisse();
										y2 = lBarre.getlisteBarre(k).getDebut_noeud().getordonnee();
									}
								}
							}
							Ligne1.set(a, Math.cos(trouve_angle(x2, x1, 1000.0, y2, y1, y1))); // trouve le cos de l'angle avec l'horizontale
							Ligne2.set(a, Math.sin(trouve_angle(x2, x1, 1000.0, y2, y1, y1))); // trouve le sin de l'angle avec l'horizontale
						}
						if (("RX" + n.getIdentificationN()) == col.get(a)) {
							Ligne1.set(a, 1.0);
						}
						if (("RY" + n.getIdentificationN()) == col.get(a)) {
							Ligne2.set(a, 1.0);
						}

					}

				}
			}
			if (n.getTypeSupport() == 3) { // si noeud simple 
				for (int j = 0; j < n.getTabbar().size(); j++) {
					for (int a = 0; a < col.size(); a++) {
						if (n.getTabbar().get(j) == col.get(a)) {
							x1 = n.getabscisse();
							y1 = n.getordonnee();

							for (int k = 0; k < lBarre.getListeBarres(); k++) {
								if (lBarre.getlisteBarre(k).getIdentificationB() == n.getTabbar().get(j)) {
									if (n.getIdentificationN() == lBarre.getlisteBarre(k).getDebut_noeud()
											.getIdentificationN()) {
										x2 = lBarre.getlisteBarre(k).getFin_noeud().getabscisse();
										y2 = lBarre.getlisteBarre(k).getFin_noeud().getordonnee();
									} else {
										x2 = lBarre.getlisteBarre(k).getDebut_noeud().getabscisse();
										y2 = lBarre.getlisteBarre(k).getDebut_noeud().getordonnee();
									}
								}
							}
							Ligne1.set(a, Math.cos(trouve_angle(x2, x1, 1000.0, y2, y1, y1))); // trouve le cos de l'angle avec l'horizontale
							Ligne2.set(a, Math.sin(trouve_angle(x2, x1, 1000.0, y2, y1, y1))); // trouve le sin de l'angle avec l'horizontale
						}

					}

				}

			}
			M.add(Ligne1);
			M.add(Ligne2);
		}
		System.out.println(M);
		return M;

	}

	public static double trouve_beta(double PTx1, double PTx2, double PTy1, double PTy2) { // permet de trouver l'angle que fait le vecteur normal au segment du terrain
		double beta = (trouve_angle(PTx1, PTx2, 1000, PTy1, PTy2, PTy2) + Math.PI / 2) % Math.PI; // modulo PI
		return beta;
	}

	public static double trouve_angle(double x1, double x2, double x3, double y1, double y2, double y3) {
		double result = Math.atan2(y3 - y1, x3 - x1) - Math.atan2(y2 - y1, x2 - x1);
		return result;
	}

	public void gaussfin() { // execute le pivot de Gauss 
		Gauss res = new Gauss();
		LBarre lBarre = new LBarre();
		ArrayList<ArrayList<Double>> A = remplitmatrice(lBarre);
		ArrayList<ArrayList<Double>> result = res.pGauss(A);
		// résultat à récupérer
		System.out.println(result);// pour le voir
	}
}