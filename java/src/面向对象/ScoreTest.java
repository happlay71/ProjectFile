package 面向对象;

import java.util.Scanner;
import java.util.Vector;

public class ScoreTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // 创建Vector对象，相当于原来的数组
        Vector v = new Vector();

        int maxScore = 0;
        for(;;){
            System.out.println("请输入学生成绩(以负数代表输入结束)");
            int score = scan.nextInt();
            if(score < 0){
                break;
            }
            if(score > 100){
                System.out.println("输入的数据非法，请重新输入");
                continue;
            }
            v.addElement(score);
            if(maxScore < score){
                maxScore = score;
            }


            }
        // 遍历Vector，得到每个学生的成绩，并于最大值比较，得到等级
        char level;
        for(int i = 0; i < v.size(); i++){
            Object obj = v.elementAt(i);
            int score = (int)obj;
            if(maxScore - score <= 10){
                level = 'A';
            }else if(maxScore - score <= 20){
                level = 'B';
            }else if(maxScore - score <= 30){
                level = 'C';
            }else{
                level = 'D';
            }

            System.out.println("student-" + i + "score is " + score + ",level is " + level);
        }
    }
}
