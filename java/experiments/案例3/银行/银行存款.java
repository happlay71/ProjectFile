package experiments.案例3.银行;

import java.util.Scanner;

public class 银行存款 {
    public static void main(String[] args) {
        int number;
        int mima;
        int n=1;
        BankAccount ba=new BankAccount();
        ba.accountnumber="123456";
        ba.mima=123456;
        ba.speak();
        while(n==1){
            System.out.println("请输入密码：");
            Scanner sr=new Scanner(System.in);
            mima=sr.nextInt();
            if(mima==ba.mima) {
                ba.leftmoney=500;
                ba.a();
                do {
                    System.out.println("请选择你功能：");
                    Scanner sc=new Scanner(System.in);
                    number=sc.nextInt();
                    switch(number) {
                        case 1:
                            System.out.println("请输入存款金额：");
                            ba.setSavemoney();
                            continue;
                        case 2:
                            System.out.println("请输入取款金额：");
                            ba.setGetmoney();
                            continue;
                        case 3:
                            System.out.println("您的余额为：");
                            ba.getsum();
                            ba.leftmoney=ba.sum;
                            ba.getmoney=0;
                            ba.savemoney=0;
                            continue;
                    }
                } while(number!=0);
                System.out.println("谢谢您的使用！");
                n=0;
            }
            else {
                System.out.println("密码错误！");
                n=1;
            }
        }

    }
}
