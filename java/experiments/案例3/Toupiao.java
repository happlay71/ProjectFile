package experiments.案例3;

import java.util.Scanner;

public class Toupiao {
    public static void main(String[] args) {
        System.out.println("班干部人选有：1，小华 2，小雅 3 小晴");

        int a1=0;
        int a2=0;
        int a3=0;
        int b=0;
        Scanner s=new Scanner(System.in);
        boolean c=true;
        while(c&&b<10) {
            for(int i=1;i<=10;i++) {
                System.out.println("请输入对应序号来投票：");
                int s1=s.nextInt();
                if(s1==1) {
                    a1++;
                }
                else if(s1==2) {
                    a2++;
                }
                else if(s1==3) {
                    a3++;
                }
                else {
                    System.out.println("输入错误，请重新输入");
                }
                System.out.println("是否要结束投票？请输入‘Y’或‘N’");
                String s2=s.next();
                if (s2.equals("Y")) {
                    c=false;
                    break;
                }
                else {
                    b++;
                }
            }
        }
        System.out.println("以下是投票结果：");
        System.out.println("小华："+a1+"票   "+"小雅："+a2+"票   "+"小晴："+a3+"票");
    }
}
