/**
 * 文档注释
 * @author shkstart
 * @version v1.0
 * 我的第一个java程序
 */

/*
1. 在一个java源文件中可以声明多个class，但只能最多有一个类声明为public
并且用public命名的类必须与文件名一致
2. println是先输出后换行
   print是输出不换行
3. 标识符可以由英文，数字，_或$组成（不建议用中文，不可有空格）
 */
import java.util.Scanner;

public class 基础语法 {
    public static void main(String[] args)
    {
        System.out.print("happy");
        System.out.println("Hello,World!");
        System.out.print("result");
    }
}

/*
基本数据类型：
    1.整形: byte\short\int\long
    2.浮点型:float\double
    3.字符型:char  ''定义一个字符\转义字符\用Unicode的值表示字符常量
    4.布尔型:boolean
引用数据类型：
    1.类:class
    2.接口:interface
    3.数组:array
    4.字符串:String  ""定义一个字符串
 */
class Test1{
    public static void main(String[] args){
        byte b1 = 12;
        byte b2 = -128;
        //定义long时结尾要加'l'或'L'
        long l1 = 24141524L;
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(l1);

        //定义float时结尾要加'f'或'F'
        float f1 = 12.3f;
        double d1 = 123.3;
        System.out.println(f1);
        System.out.println(d1);

        char c1 = 'f';
        char c2 = '\n';
        char c3 = '\u0123';
        System.out.println(c1);
        System.out.print("hello" + c2);
        System.out.println("world");
        System.out.println(c3);

        boolean bl1 = true;
        boolean bl2 = false;
        System.out.println(bl1);
        System.out.println(bl2);

        String s1 = "hello world";
        System.out.println(s1);
    }
}

/*
基础运算:
    1.自动类型提升:
        进行运算后结果为最高类型byte、char、short<int<long<float<double (不能和布尔类型运算)
        byte、char、short之间进行运算结果为int
    2.强制类型转换(自动类型提升的逆运算):
        强转符:()
    3.String运算(可以和任意类型做连接运算):
        用 + 进行运算
 */
class Test2{
    public static void main(String[] args){
        byte b1 = 12;
        int i1 = 123;
        float f1 = b1 + i1;
        char c1 = 'c';
        int i2 = c1 + i1;
        System.out.println(f1);
        System.out.println(i2);

        int i3 = (int)f1;
        System.out.println(i3);

        String s1 = "学号：";
        String s2 = s1 + i1;
        System.out.println(s2);
    }
}

/*
进制:
    二进制(binary):0,1  满2进1，以0b\0B开头
    十进制(decimal):0-9  满10进1
    八进制(octal):0-7  满8进1，以数字0开头
    十六进制(hex):0-9,A-F  满16进1，以0x\0X开头
 */

/*
逻辑运算符：&  |  ！
短路运算符：&&(当前者为假时直接判断，跳过后者)  ||(当前者为真时直接判断，跳过后者)  ^
 */
class Test3{
    public static void main(String[] args){
        int n1 = 0b110;
        int n2 = 123;
        int n3 = 0_127;
        int n4 = 0x110A;
        System.out.println(n1);
        System.out.println(n2);
        System.out.println(n3);
        System.out.println(n4);

        boolean b1 = false;
        int num1 = 10;
        if(b1 & (num1++ > 0)){
            System.out.println("北京");
        }
        else{
            System.out.println("南京");
        }
        System.out.println("num1=" + num1);

        boolean b2 = false;
        int num2 = 10;
        if(b2 && (num2++ > 0)){
            System.out.println("北京");
        }
        else{
            System.out.println("南京");
        }
        System.out.println("num2=" + num2);
    }
}

/*
位运算符:
    左移 <<  在二进制里向左移动，缺的补0
    右移 >>  在二进制里向右移动，全补与符号位相同的1/0
    无符号右移 >>>  在二进制里向右移动，缺的补0
    按位与 &  两数的二进制对比，同为1得1，其余得0
    按位或 |  两数的二进制对比，同为0得0，其余得1
    按位异或 ^  两数的二进制对比，不同得1，相同得0
    按位取反 ~  补码取反得新的补码，-1得反码，除符号位取反得原码
三元运算符:
    (x > y) ? x :y True则为x，False则为y
 */

class Test4{
    public static void main(String[] args){
        int n1 = 4;
        int n2 = -4;
        System.out.println("n1 >> 1 = " + (n1 >> 1));
        System.out.println("n1 << 1 = " + (n1 << 1));
        System.out.println("n2 >>> 1 = " + (n2 >>> 1));

        int n3 = 7;
        System.out.println("n1 & n3 = " + (n1 & n3));
        System.out.println("n1 | n3 = " + (n1 | n3));
        System.out.println("n1 ^ n3 = " + (n1 ^ n3));
        System.out.println("~n3 = " + ~n3);

        n1 = n1 ^ n3;
        n3 = n1 ^ n3;
        n1 = n1 ^ n3;
        System.out.println("n1 = " + n1 + "\nn3 = " + n3);

    }
}

/*
Scanner包:从键盘输入，相当于scanf
    import java.util.Scanner导入
    Scanner实例化
    调用相关类的方法
 */
class Test5{
    public static void main(String[] args){
        // 随机数[a, b)  Math.random() * (b - a + 1) + a
        int math = (int)(Math.random() * 90 + 10);  // [0.0, 1.0) -> [0.0, 90.0) -> [10.0, 100.0)(强转为int类)
        System.out.println(math);

        Scanner scan = new Scanner(System.in);

        System.out.println("姓名:");
        String name = scan.next();
        System.out.println(name);

        System.out.println("性别(男/女):");
        String gender = scan.next();
        char genderChar = gender.charAt(0);
        System.out.println(genderChar);

        System.out.println("年龄:");
        int age = scan.nextInt();
        System.out.println(age);

        System.out.println("体重:");
        double weight = scan.nextDouble();
        System.out.println(weight);

        System.out.println("开心吗(true/false):");
        boolean happy = scan.nextBoolean();
        System.out.println(happy);

        System.out.println("你是码农吗？(是/否):");
        String answer = scan.next();
        if(answer.equals("是")){
            System.out.println("同命相连");
        }
        else{
            System.out.println("真好");
        }
    }
}

/*
输出质数：优化算法
 */
class Test6{
    public static void main(String[] args){
        boolean isFlag = true;
        int count = 0;
        long start = System.currentTimeMillis();
        for(int i = 2; i <= 100; i++){
            for(int j = 2; j <= Math.sqrt(i); j++){
                if(i % j != 0){
                    isFlag = false;
                    break;
                }
            }
            if(isFlag == true) {
                count++;
                System.out.println(i);
            }
            isFlag = true;
        }
        // 获取当前时间距离1970-01-01 00:00:00 的毫秒数
        long end = System.currentTimeMillis();
        System.out.println("质数个数：" + count);
        System.out.println("运行时间：" + (end - start));
    }
}

/*
break,continue指定标签
 */
class Test7{
    public static void main(String[] args){
        label:for(int i = 1; i <= 4; i++){
            for(int j = 1; j <= 10; j++){
                if(j % 4 == 0){
                    break label;
                }
                System.out.println(j);
            }
            System.out.println();
        }
        System.out.println();
        int count = 0;
        long start = System.currentTimeMillis();
        label:for(int i = 2; i <= 100000; i++){
            for(int j = 2; j <= Math.sqrt(i); j++){
                if(i % j != 0){
                    continue label;
                }
            }
            count++;
            System.out.println(i);
        }
        // 获取当前时间距离1970-01-01 00:00:00 的毫秒数
        long end = System.currentTimeMillis();
        System.out.println("质数个数：" + count);
        System.out.println("运行时间：" + (end - start));
    }
}

// 数组
class Test8{
    public static void main(String[] args){
        // 一维数组
        int[] id;
        id = new int[]{1, 2, 3, 4};
        String[] names;
        names = new String[3];
        names[0] = "hh";
        names[1] = "nh";

        //二维数组
        int[][] ids;
        int[] ps[] = {{12, 3}, {53, 5}};  //类型推断，省略前面的new...
        ids = new int[][]{{11, 42}, {32, 53}};
        String[][] nums;
        nums = new String[2][5];
        nums = new String[2][];

        // 获取数组长度
        System.out.println(names.length);

        // 遍历数组
        for(int i = 0; i < names.length; i++){
            System.out.println(names[1]);
        }

        // 二分法查找：适合有序
        int[] arr = new int[]{-95, -42, -32, -13, 21, 35, 45, 63, 78, 98};
        int dest = -32;
        int head = 0;  // 初始首索引
        int end = arr.length - 1;
        boolean isFlag = true;
        while(head <= end){
            int middle = (head + end) / 2;
            if(dest == arr[middle]){
                System.out.println("找到了指定元素,位置为：" + middle);
                isFlag = false;
                break;
            }else if(arr[middle] > dest){
                end = middle - 1;
            }else{
                head = middle + 1;
            }
        }
        if(isFlag){
            System.out.println("没有找到");
        }
    }
}






