package experiments.个人通讯录管理系统;

import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);
    Function function = new Function();

    int num;
    public void menuList() {

        while (true) {
            System.out.println("------个人通讯录管理系统-------");
            System.out.println("\t\t1.增添信息");
            System.out.println("\t\t2.修改数据");
            System.out.println("\t\t3.查询数据");
            System.out.println("\t\t4.删除数据");
            System.out.println("\t\t5.保存信息到文件里");
            System.out.println("\t\t退出请按0");

            System.out.print("请选择功能：");
            num = scanner.nextInt();

            if (num == 0) break;

            switch (num) {
                case 1:
                    function.add();
                    break;
                case 2:
                    System.out.print("请输入要修改的用户信息的电话号码：");
                    function.update(scanner.nextInt());
                    break;
                case 3:
                    System.out.print("请输入要查询的用户信息的姓名或电话号码：");
                    String m = scanner.next();
                    try {
                        int n = Integer.parseInt(m);
                        function.select(n);
                    } catch (NumberFormatException e) {
                        function.select(m);
                    }
                    break;
                case 4:
                    System.out.print("请输入要删除的用户信息的电话号码：");
                    function.delete(scanner.nextInt());
                    break;
                case 5:
                    System.out.print("请输入要存在桌面的文本的文本名：");
                    function.save(scanner.next());
            }
        }

    }

}
