package experiments.案例2;

import java.util.Scanner;

public class Login {
    static String[] username = new String[5];
    static String[] password = new String[5];

    static int idx = 0; // 用于记录添加新用户的位置

    public static void main(String[] args) {

        // 初始用户密码
        username[0] = "admin";
        password[0] = "123";

        Scanner sc = new Scanner(System.in);
        boolean isFlag = true;
        while (isFlag) {
            printInfo();
            String option = sc.next();
            switch (option) {
                case "1":
                    login();
                    break;
                case "2":
                    register();
                    break;
                case "3":
                    showInfo();
                    break;
                case "4":
                    System.out.println("4. 退 出 系 统\n");
                    isFlag = false;
                    break;
                default:
                    System.out.println("输入有误！请重新输入：");
                    break;
            }
        }
    }

    public static void printInfo() {
        System.out.println("------------------用户信息管理系统------------------");
        System.out.println("------------------1. 用 户 登 录-------------------");
        System.out.println("------------------2. 用 户 注 册-------------------");
        System.out.println("------------------3. 查 看 信 息-------------------");
        System.out.println("------------------4. 退 出 系 统-------------------");
        System.out.println("请选择功能：");
    }

    public static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=>用户登录");
        outer:
        while (true) {
            for (int i = 0; i < username.length; i++) {
                System.out.print("用户名：");
                String uname = sc.next();
                System.out.print("密码：");
                String upass = sc.next();
                if (username[i].equals(uname) && password[i].equals(upass)) {
                    System.out.println("登录成功！\n");
                    break outer;
                } else {
                    System.out.println("账号或密码错误！请重新输入\n");
                    break;
                }
            }
        }
    }

    public static void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=>用户注册");

        System.out.println("请输入用户名：");
        String uname = sc.next();
        System.out.println("请输入密码：");
        String upass = sc.next();

        boolean flag = true;
        for (int i = 0; i < username.length; i++) {
            // 判断是否已经存在用户
            if (uname.equals(username[i])) {
                System.out.println("用户名已存在！");
                flag = false;
            }
        }
        if (flag) {
            idx++;
            if (idx < username.length) {
                username[idx] = uname;
                password[idx] = upass;
                System.out.println("注册完成！\n");
            } else {
                // 若idx超过了数组的长度，则需要扩充大数组容量
                expandArrLen();
                username[idx] = uname;
                password[idx] = upass;
                System.out.println("注册完成！");
            }
        }
    }

    private static void expandArrLen() {
        String[] uarr = new String[username.length + 10];
        String[] parr = new String[password.length + 10];

        System.arraycopy(username, 0, uarr, 0, username.length);
        System.arraycopy(password, 0, parr, 0, password.length);

        username = uarr;
        password = parr;
    }

    private static void showInfo() {
        System.out.println("=>查看信息");
        for (int i = 0; i < username.length; ++i) {
            if (username[i] != null) {
                System.out.print("用户名：" + username[i] + ", ");
                System.out.println("密码：" + password[i]);
            }
        }
        System.out.println();
    }
}
