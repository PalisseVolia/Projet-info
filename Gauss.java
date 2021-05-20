import java.util.ArrayList;
import java.lang.*;
import java.util.*;

public class Gauss {
	ArrayList<ArrayList<Double>> M;
	
	public Gauss(){
		M = new ArrayList<ArrayList<Double>>();
	}
	
	public ArrayList<ArrayList<Double>> augmente(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> B){
		ArrayList<ArrayList<Double>> res = new ArrayList<ArrayList<Double>>();
		for(int i=0; i<A.size(); i++) {
			res.add(new ArrayList<Double>());
			for(int j=0; j<A.get(i).size();j++) {
				res.get(i).add(A.get(i).get(j));
			}
			for(int k=0; k<B.get(i).size();k++) {
				res.get(i).add(B.get(i).get(k));
			}
		}
		M=res;
		return res;
	}
	//MATRICE DE MEMES TAILLES !
	/*       entrée               sortie
	 * (a1 a2)    (b1 b2)      (a1 a2 b1 b2)
	 * (a3 a4)    (b3 b4)      (a3 a4 b3 b4)
	 */
	
	public ArrayList<ArrayList<Double>> echLigne(int i,int j){
		ArrayList<Double> temp = M.get(i);
		M.set(i, M.get(j));
		M.set(i, temp);
		return M;
	}
	
	public int pivot(int i){
		int n = M.size();
		int j = i;
		for(int k=i+1; k<n; k++) {
			if(Math.abs(M.get(k).get(i))>Math.abs(M.get(j).get(i))) {
				j=k;
			}//if abs(M[k,i])>abs(M[j,i])
		}
		return j;
	}
	
	public ArrayList<ArrayList<Double>> elimine(int i,int j){
		double a = -M.get(j).get(i)/M.get(i).get(i);
		for(int k = 0; k<M.get(j).size(); k++) {
			M.get(j).set(k,M.get(j).get(k)+M.get(i).get(k)*a);
		}//M[j]+=a*M[i]
		return M;
	}
	
	public ArrayList<ArrayList<Double>> normaliseDiagonale(){
		for(int i = 0; i<M.size(); i++) {
			double temp = M.get(i).get(i);
			for(int j=0; j<M.get(i).size(); j++){
				M.get(i).set(j, M.get(i).get(j)/temp);
			}//M[i]/=M[i,i]
		}
		return M;
	}
	
	public ArrayList<ArrayList<Double>> pGauss(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> B) {
		M = augmente(A,B);//On crée la matrice augmentée de M
		int n = M.size();
		for(int i=0; i<n-1; i++) {//On parcours chaque ligne de la matrice M
			int p = pivot(i);//On cherche le plus grand pivot
			if(i!=p) {
				echLigne(i,p);//échange la première ligne par la ligne qui contient le plus grand pivot
			}
			for(int j=i+1; j<n; j++) {
				elimine(i,j);//Elimine tout les coefficients en dessous des pivots
			}
		}
		normaliseDiagonale();//Met les pivots en 1
		for(int i = n-1; i>0; i--) {
			for(int j = i-1; j>=0; j--) {
				elimine(i,j);//élimine les coefficient au dessus des pivots
			}
		}
		return M;
	}
	
	
	
	
	public static void main(String[] args) {
		Gauss test = new Gauss();
		
		ArrayList<ArrayList<Double>> A = new ArrayList<ArrayList<Double>>();
		//à rentrer
		
		ArrayList<ArrayList<Double>> B = new ArrayList<ArrayList<Double>>();
		//à rentrer
		
		
		
		
		ArrayList<ArrayList<Double>> result = test.pGauss(A,B);
		//résultat à récupérer
		System.out.println(result);//pour le voir
	}

}
