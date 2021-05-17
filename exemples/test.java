package exemples;

public class test {
    public static void main(String[] args) {
        String test1 = "line 1\n";
        String test2 = "line 2\n";
        String test3 = "line 3";
        String test = "";
        test = test.concat(test1);
        test2 = "".repeat(test2.length());
        test = test.concat(test2);
        test = test.concat(test3);
        System.out.println(test);
    }
}