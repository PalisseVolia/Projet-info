package exemples;

public class test {
    public static void main(String[] args) {
        String a = "2";
        try {
            Double b = Double.parseDouble(a);
            System.out.println(b);
        } catch (Exception e) {
            System.out.println("not double");
        }
    }
}
