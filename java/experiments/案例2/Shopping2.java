package experiments.案例2;

import java.util.Scanner;

public class Shopping2 {
    static double toothBrushPrice = 8.8;
    static double towelPrice = 10.0;
    static double cupPrice = 18.8;
    static double applePrice = 12.5;
    static double bananaPrice = 15.5;

    public static void main(String[] args) {
        meauInfo();
        String isFlag = "Y";
        while (isFlag.equals("Y")) {
            Scanner sc = new Scanner(System.in);
            int idx = sc.nextInt();
            System.out.println("您要购买该商品的数量：");
            switch (idx) {
                case 1:
                    int tonums = sc.nextInt();
                    double toexpense = tonums * toothBrushPrice;
                    System.out.println("您选择了牙刷" + tonums + "支，需支付" + toexpense + "元");
                    System.out.println("是否继续购买商品？是（输入Y）/否（输入N）");
                    isFlag = sc.next();
                    break;
                case 2:
                    int tnums = sc.nextInt();
                    double texpense = tnums * towelPrice;
                    System.out.println("您选择了毛巾" + tnums + "条，需支付" + texpense + "元");
                    System.out.println("是否继续购买商品？是（输入Y）/否（输入N）");
                    isFlag = sc.next();
                    break;
                case 3:
                    int cnums = sc.nextInt();
                    double cexpense = cnums * cupPrice;
                    System.out.println("您选择了水杯" + cnums + "个，需支付" + cexpense + "元");
                    System.out.println("是否继续购买商品？是（输入Y）/否（输入N）");
                    isFlag = sc.next();
                    break;
                case 4:
                    int anums = sc.nextInt();
                    double aexpense = anums * applePrice;
                    System.out.println("您选择了苹果" + anums + "kg，需支付" + aexpense + "元");
                    System.out.println("是否继续购买商品？是（输入Y）/否（输入N）");
                    isFlag = sc.next();
                    break;
                case 5:
                    int bnums = sc.nextInt();
                    double bexpense = bnums * bananaPrice;
                    System.out.println("您选择了苹果" + bnums + "kg，需支付" + bexpense + "元");
                    System.out.println("是否继续购买商品？是（输入Y）/否（输入N）");
                    isFlag = sc.next();
                    break;
                default:
                    System.out.println("无此商品，请重新输入！");
                    break;
            }
        }
        System.out.println("欢迎下次光临！");
    }

    public static void meauInfo() {
        System.out.println("----------超市商城-----------");
        System.out.println("1------牙刷￥" + toothBrushPrice + "/支----------");
        System.out.println("2------毛巾￥" + towelPrice + "/条---------");
        System.out.println("3------水杯￥" + cupPrice + "/个---------");
        System.out.println("4------苹果￥" + applePrice + "/kg---------");
        System.out.println("5------香蕉￥" + bananaPrice + "/kg---------");
        System.out.println("请选择需要购买的商品的编号：");
    }
}
