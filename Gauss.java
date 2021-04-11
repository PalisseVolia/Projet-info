
public class Gauss
{
	public static void main(String[] args)
	{

		System.out.println("Entrez le nombre d'equations");
		int n = Lire.i();
		Matrice mat = new Matrice(n);
		mat.initialize();
		mat.resolution();
	}
}