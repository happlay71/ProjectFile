package experiments.案例3.银行;

import java.util.Scanner;

public class BankAccount {
    public String accountnumber;//账户
    int leftmoney;//存款余额
    int getmoney;//取款
    int savemoney;//存款
    int mima;//密码
    int sum=0;
    void speak(){
        System.out.println("你的账户为："+accountnumber);

    }

    void a() {
        System.out.println("存款余额"+leftmoney);
    }

    void getsum() {
        sum=leftmoney+savemoney-getmoney;
        System.out.println(sum);

    }

    public int setSavemoney(){
        Scanner sc=new Scanner(System.in);
        savemoney=sc.nextInt();
        this.savemoney=savemoney;
        return savemoney;

    }
    public int getSavemoney() {
        // TODO Auto-generated method stub
        return savemoney;
    }
    public int setGetmoney(){
        Scanner sc=new Scanner(System.in);
        return getmoney=sc.nextInt();
    }
    public int getGetmoney(){
        return getmoney;
    }

}
