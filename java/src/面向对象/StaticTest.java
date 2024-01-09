package 面向对象;

public class StaticTest {
    public static void main(String[] args) {
        Chinese c1 = new Chinese();
        c1.country = "hhh";

        Chinese c2 = new Chinese();
        System.out.println(c2.country);
    }
}

class Chinese{
    String name;
    int age;
    static String country;
}
