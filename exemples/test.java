package exemples;

public class test {
    public static void main(String[] args) {
        Double abs1 = 1.0;
        Double abs2 = 2.0;
        Double abs3 = 1.0;
        Double abscheck = 1.5;
        Double ord1 = 1.0;
        Double ord2 = 2.0;
        Double ord3 = 2.0;
        Double ordcheck = 1.5;
        System.out.println(Equation(abs1, abs2, ord1, ord2, abs3, ord3, abscheck, ordcheck));
        
        
    }

    public static int Equation(Double abs1, Double abs2, Double ord1, double ord2, Double abs3, Double ord3, Double abscheck, Double ordcheck) {
        // int indic = 0;
        Double[] equa = new Double[3];      //ax + by + c = 0
        equa[0] = -(abs2-abs1);     //b
        equa[1] = ord2-ord1;        //a
        equa[2] = -(equa[1]*abs1 + equa[0]*ord1);
        Double pt = equa[1]*abs3 + equa[0]*ord3 + equa[2];
        Double ptcheck = equa[1]*abscheck + equa[0]*ordcheck + equa[2];
        System.out.println(pt);
        System.out.println(ptcheck);
        if ((ptcheck < 0.001)&&(ptcheck > -0.001)) {
            if (((abscheck-abs1)*(abscheck-abs2) + (ordcheck-ord1)*(ordcheck-ord2)) <= 0) {
                return(2);
            }
        } 
        if (((pt>0)&&(ptcheck>0))||((pt<0)&&(ptcheck<0))) {
            return(1);
        } 
        else {
            return(0);
        }
    }
}
