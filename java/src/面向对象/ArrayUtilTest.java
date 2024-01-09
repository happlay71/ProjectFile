package 面向对象;

public class ArrayUtilTest {
    public static void main(String[] args){
//        ArrayUtil util = new ArrayUtil();
        int[] arr = new int[]{23, 52, 12, 75, 2, -29};
        int max = ArrayUtil.getMax(arr);
        System.out.println("最大值为：" + max);
    }

    //可变个数的形参
    public void show(String ... strs){
        System.out.println(strs);
    }
}
