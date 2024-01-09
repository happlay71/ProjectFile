package experiments.案例4;

import java.util.Scanner;

public class Postgraduate {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        A a=new A();
        System.out.println("请输入研究生每月学费：");
        int t=input.nextInt();
        System.out.println("请输入研究生每月工资：");
        int i=input.nextInt();
        a.Tuition(t);
        a.Income(i);
        if(a.getNum()<2000) {
            System.out.println(a.getNum());
            System.out.println("provide a load");
        }else {
            System.out.println("够用");
        }
    }
}

interface Tuition{
    void tuition();
}
interface Income{
    void income() ;
}
class A implements Tuition,Income{
    private int num=0;
    public int getNum() {
        return num;
    }
    void Tuition(int num) {
        this.num-=num;
    }
    void Income(int num) {
        this.num+=num;
    }
    @Override
    public void tuition() {

    }
    @Override
    public void income() {
    }
}