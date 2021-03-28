public class Matrice {
	private final int nbEquations; // n lignes
	//	private int m ; //m colonnes
	private final double[][] e; //n <=> nbre d'equations
	public final double[] s;
	private boolean ok;

	public Matrice(int aNbEquations)
	{

		nbEquations = aNbEquations;
		if (nbEquations >= 12)
		{
			throw new IllegalArgumentException("Trop d'équation");
		}

		e = new double[nbEquations + 1][nbEquations];
		s = new double[nbEquations];

		ok = false;
	}

	public void initialize()
	{
		for (int i = 0; i < nbEquations; i++)
		{
			System.out.println("equation " + i);
			for (int j = 0; j < nbEquations; j++)
			{
				System.out.print("facteur " + j + " = ");
				e[j][i] = Lire.i();
				System.out.println(" ");
			}
			System.out.println(" ");
			System.out.println("resultat " + i + " = ");
			e[nbEquations][i] = Lire.i();
			System.out.println(" ");
		} // on a saisi les facteurs du système ds e[][]

		ok = true;
	}

	public boolean ok()
	{
		return ok;
	}

	public void resolution()
	{
		double temp;
		int a, b;

		//triangularisation
		for (int k = 0; k < nbEquations - 1; k++)
		{
			for (a = 1 + k; a < nbEquations; a++)
			{
				temp = e[k][a];
				for (b = k; b < nbEquations + 1; b++)
				{
					e[b][a] = e[b][a] * e[k][k] - e[b][k] * temp;
				}
			}
		}
		//remplacement et résolution
		s[nbEquations - 1] = e[nbEquations][nbEquations - 1] / e[nbEquations - 1][nbEquations - 1];
		//le vecteur s commence à 0 donc s[n-1] <=> dernière inconnue
		//la matrice e commence à 0 et reçoit [colonnes][lignes] donc e[n][n-1]
		//correspond à l'élément de la colonne résultat de la dernière ligne
		//e[n-1][n-1] correspond au dernier facteur de la dernière ligne.

		for (int i = 1; i < nbEquations; i++) //décalage des colonnes
		{
			for (int j = 2; j <= nbEquations; j++)
			{ //décalage des lignes
				e[nbEquations - i][nbEquations - j] *= s[nbEquations - i];
				e[nbEquations][nbEquations - j] -= e[nbEquations - i][nbEquations - j];
				e[nbEquations - i][nbEquations - j] = 0;
			}
			s[nbEquations - (i + 1)] = e[nbEquations][nbEquations - (i + 1)] / e[nbEquations - (i + 1)][nbEquations - (i + 1)]; //on met à jour le vecteur
		}
		//affichage
		for (int i = 0; i < nbEquations; i++)
		{
			System.out.println("facteur " + i + "= " + s[i]);

		}

	}
}