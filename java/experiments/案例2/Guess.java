package experiments.案例2;

import java.util.Random;
import java.util.Scanner;

public class Guess {
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        int win=0;
        int loss=0;
        int draw=0;
        for(int i=1;i<=5;i++) {
            System.out.println("当前为第"+i+"局");
            int randomNumber=new Random().nextInt(3);
            System.out.print("请选择出剪刀、石头、布：");
            String a=input.next();
            if(a.equals("剪刀")) {
                if(randomNumber==0) {
                    System.out.println("电脑为：剪刀");
                    System.out.println("平局！");
                    draw++;
                }
                else if(randomNumber==1) {
                    System.out.println("电脑为：石头");
                    System.out.println("失败!");
                    loss++;
                }
                else if(randomNumber==2) {
                    System.out.println("电脑为:布");
                    System.out.println("胜出");
                    win++;
                }
            }
            else if(a.equals("石头")) {
                if(randomNumber==0) {
                    System.out.println("电脑为：剪刀");
                    System.out.println("胜出");
                    win++;
                }
                else if(randomNumber==1) {
                    System.out.println("电脑为：石头");
                    System.out.println("平局");
                    draw++;
                }
                else if(randomNumber==2) {
                    System.out.println("电脑为：布");
                    System.out.println("失败");
                    loss++;
                }
            }
            else if(a.equals("布")) {
                if(randomNumber==0) {
                    System.out.println("电脑为：剪刀");
                    System.out.println("失败");
                    loss++;
                }
                else if(randomNumber==1) {
                    System.out.println("电脑为：石头");
                    System.out.println("胜出");
                    win++;
                }
                else if(randomNumber==2) {
                    System.out.println("电脑为：布");
                    System.out.println("平局");
                    draw++;

                }
            }
            else {
                System.out.println("输入有误！");
            }
        }
        System.out.println("游戏结束！五局游戏中一共赢"+win+"局，失败"+loss+"局，平"+draw+"局");
        if(win>loss) {
            System.out.println("胜出");
        }
        else if(loss<win) {
            System.out.println("失败");
        }
        else {
            System.out.println("平局");
        }
    }
}
