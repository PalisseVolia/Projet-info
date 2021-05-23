import java.util.ArrayList;
import Treillis.LNoeud;
import Treillis.Noeud;
import Treillis.LBarre;
import Terrain.LTriangle;
import Terrain.Triangle;

public class Gauss {
	public ArrayList<ArrayList<Double>> M;
	public LBarre lBarre = new LBarre();
	public LTriangle lTriangle = new LTriangle();

	public Gauss() {
		M = new ArrayList<ArrayList<Double>>();
	}

	public ArrayList<ArrayList<Double>> augmente(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> B) {
		ArrayList<ArrayList<Double>> res = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < A.size(); i++) {
			res.add(new ArrayList<Double>());
			for (int j = 0; j < A.get(i).size(); j++) {
				res.get(i).add(A.get(i).get(j));
			}
			for (int k = 0; k < B.get(i).size(); k++) {
				res.get(i).add(B.get(i).get(k));
			}
		}
		M = res;
		return res;
	}

	public ArrayList<ArrayList<Double>> echLigne(int i, int j) {
		ArrayList<Double> temp = M.get(i);
		M.set(i, M.get(j));
		M.set(i, temp);
		return M;
	}

	public int pivot(int i) {
		int n = M.size();
		int j = i;
		for (int k = i + 1; k < n; k++) {
			if (Math.abs(M.get(k).get(i)) > Math.abs(M.get(j).get(i))) {
				j = k;
			} // if abs(M[k,i])>abs(M[j,i])
		}
		return j;
	}

	public ArrayList<ArrayList<Double>> elimine(int i, int j) {
		double a = -M.get(j).get(i) / M.get(i).get(i);
		for (int k = 0; k < M.get(j).size(); k++) {
			M.get(j).set(k, M.get(j).get(k) + M.get(i).get(k) * a);
		} // M[j]+=a*M[i]
		return M;
	}

	public ArrayList<ArrayList<Double>> normaliseDiagonale() {
		for (int i = 0; i < M.size(); i++) {
			double temp = M.get(i).get(i);
			for (int j = 0; j < M.get(i).size(); j++) {
				M.get(i).set(j, M.get(i).get(j) / temp);
			} // M[i]/=M[i,i]
		}
		return M;
	}

	public ArrayList<ArrayList<Double>> pGauss(ArrayList<ArrayList<Double>> A) {
		// M = augmente(A,B);//On crée la matrice augmentée de M
		this.M = A;
		int n = M.size();
		for (int i = 0; i < n - 1; i++) {// On parcours chaque ligne de la matrice M
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
				elimine(i, j);// élimine les coefficient au dessus des pivots
			}
		}
		return M;
	}

	public static ArrayList<ArrayList<Double>> remplitmatrice(LNoeud list, LBarre lBarre) {
		ArrayList<String> col = new ArrayList<String>();
		ArrayList<ArrayList<Double>> Mat = new ArrayList<ArrayList<Double>>();
		for (int i = 0; i < list.getlisteNoeuds(); i++) {
			Noeud n = list.getListeNoeuds(i);
			for (int j = 0; j < n.getTabbar().size(); j++) {
				if (!col.contains(n.getTabbar().get(j))) {
					col.add(n.getTabbar().get(j));
				}
			}
			if (n.getTypeSupport() == 1) {
				col.add("R" + n.getIdentificationN());
			}
			if (n.getTypeSupport() == 2) {
				col.add("Rx" + n.getIdentificationN());
				col.add("Ry" + n.getIdentificationN());
			}
		}
		double x2 = 0.0;
		double y2 = 0.0;
		double x1 = 0.0;
		double y1 = 0.0;
		for (int i = 0; i < list.getlisteNoeuds(); i++) {
			Noeud n = list.getListeNoeuds(i);
			ArrayList<Double> Ligne1 = new ArrayList<Double>();
			ArrayList<Double> Ligne2 = new ArrayList<Double>();
			for (int a = 0; a < col.size(); a++) {
				Ligne1.add(0.0);
				Ligne2.add(0.0);
			}
			if (n.getTypeSupport() == 1) {
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
							Ligne1.set(a, Math.cos(trouve_angle(x2, x1, 1000.0, y2, y1, y1)));
							Ligne2.set(a, Math.sin(trouve_angle(x2, x1, 1000.0, y2, y1, y1)));
						}
						if (("R" + n.getIdentificationN()) == col.get(a)) {
							LTriangle lTriangle = new LTriangle();
							Triangle t = lTriangle.getListeTriangles(n.gettriangleappui());
							if (n.gettrianglecote() == 1) {
								x1 = t.getAbscisseT1();
								y1 = t.getOrdonneeT1();
								x2 = t.getAbscisseT2();
								y2 = t.getOrdonneeT2();
								Ligne1.set(a, Math.cos(trouve_beta(x1, x2, y1, y2)));
								Ligne2.set(a, Math.sin(trouve_beta(x1, x2, y1, y2)));
							}
							if (n.gettrianglecote() == 2) {
								x1 = t.getAbscisseT2();
								y1 = t.getOrdonneeT2();
								x2 = t.getAbscisseT3();
								y2 = t.getOrdonneeT3();
								Ligne1.set(a, Math.cos(trouve_beta(x1, x2, y1, y2)));
								Ligne2.set(a, Math.sin(trouve_beta(x1, x2, y1, y2)));
							}
							if (n.gettrianglecote() == 3) {
								x1 = t.getAbscisseT3();
								y1 = t.getOrdonneeT3();
								x2 = t.getAbscisseT1();
								y2 = t.getOrdonneeT1();
								Ligne1.set(a, Math.cos(trouve_beta(x1, x2, y1, y2)));
								Ligne2.set(a, Math.sin(trouve_beta(x1, x2, y1, y2)));
							}
						}
					}
				}
			}
			if (n.getTypeSupport() == 2) {
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
							Ligne1.set(a, Math.cos(trouve_angle(x2, x1, 1000.0, y2, y1, y1)));
							Ligne2.set(a, Math.sin(trouve_angle(x2, x1, 1000.0, y2, y1, y1)));
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
			if (n.getTypeSupport() == 3) {
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
							Ligne1.set(a, Math.cos(trouve_angle(x2, x1, 1000.0, y2, y1, y1)));
							Ligne2.set(a, Math.sin(trouve_angle(x2, x1, 1000.0, y2, y1, y1)));
						}

					}

				}

			}
			Mat.add(Ligne1);
			Mat.add(Ligne2);
		}
		return Mat;

	}

	public static double trouve_angle(double x1, double x2, double x3, double y1, double y2, double y3) {
		double p0c = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)); // p0->c (b)
		double p1c = Math.sqrt(Math.pow(x2 - x3, 2) + Math.pow(y2 - x3, 2)); // p1->c (a)
		double p0p1 = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2)); // p0->p1 (c)
		return Math.acos((p1c * p1c + p0c * p0c - p0p1 * p0p1) / (2 * p1c * p0c));
	}

	public static double trouve_beta(double PTx1, double PTx2, double PTy1, double PTy2) {
		double beta = (trouve_angle(PTx1, PTx2, 1000, PTy1, PTy2, PTy2) + Math.PI / 2) % Math.PI;
		return beta;
	}

	// public static double[] trouve_alpha(double x1,double x2, double x3, double
	// y1, double y2, double y3) {
	// double[] res = new double[6];

	// double alpha12 = trouve_angle(x2,x1,1000,y2,y1,y1);
	// res[0]=alpha12;

	// double alpha13 = trouve_angle(x3,x1,1000,y3,y1,y1);
	// res[1]=alpha13;

	// double alpha21 = Math.PI-alpha12;
	// res[2]=alpha21;

	// double alpha23 = trouve_angle(x3,x2,1000,y3,y2,y2);
	// res[3]=alpha23;

	// double alpha31 = Math.PI-alpha13;
	// res[4]=alpha31;

	// double alpha32 = Math.PI-alpha23;
	// res[5]=alpha32;

	// return res;
	// }

	public static void gaussfin() {
		Gauss res = new Gauss();
		LNoeud lnoeud = new LNoeud();
		LBarre lBarre = new LBarre();
		ArrayList<ArrayList<Double>> A = remplitmatrice(lnoeud, lBarre);

		ArrayList<ArrayList<Double>> result = res.pGauss(A);
		// résultat à récupérer
		System.out.println(result);// pour le voir
	}
}