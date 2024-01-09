package 面向对象;


public class WrapperTest {

    public static void main(String[] args){
        // 基本数据类型--->包装类
        int num1 = 10;
        System.out.println(num1);
        Integer in1 = num1;
        System.out.println(in1.getClass());

        Float f1 = 12.3f;
        System.out.println(f1);

        Boolean b1 = Boolean.valueOf("true12");
        System.out.println(b1);

        Order order = new Order();
        System.out.println(order.b2);
        System.out.println(order.b3); //引用数据类型-->null

        // 基本数据类型<---包装类
        Integer i1 = 12;
        int i = i1;

        // 基本数据类型、包装类--->String类型
        String str1 = in1 + "";
        String str2 = String.valueOf(f1);

        // 基本数据类型、包装类<---String类型
        String str3 = "123";
        int n1 = Integer.parseInt(str3);
        System.out.println(n1 + 1);

    }
}

class Order{
    boolean b2;
    Boolean b3;
}
