package experiments.案例2;

import java.util.Scanner;

public class Assignment {
    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的姓名以及应聘语言：");
        String name = sc.next();
        String lge = sc.next();
        switch (lge) {
            case "java":
                System.out.println("欢迎"+name+"加入java程序开发部门");
                break;
            case "C#":
                System.out.println("欢迎"+name+"加入C#程序开发部门");
                break;
            case "J#":
                System.out.println("欢迎"+name+"加入程序测试部门");
                break;
            case "js":
                System.out.println("欢迎"+name+"加入前端程序开发部门");
                break;
            default:
                System.out.println("很遗憾没有符合您的岗位");
                break;
        }
    }
}
