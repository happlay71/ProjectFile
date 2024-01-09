package 面向对象.抽象类.练习;

import java.util.Scanner;

public class PayrollSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入当月的月份：");
        int mouth = scanner.nextInt();

        // 法二：
//        Calendar calendar = Calendar.getInstance();
//        int mouth = calendar.get(Calendar.Month); // 获取当前月份

        Employee[] emps = new Employee[2];

        emps[0] = new SalariedEmployee("马森", 1002, new MyDate(1993, 3, 23), 10000);
        emps[1] = new HourlyEmployee("潘裕生", 2001, new MyDate(1991, 3, 23), 60, 240);

        for(int i = 0; i < emps.length; i++) {
            System.out.println(emps[i]);
            double salary = emps[i].earnings();
            System.out.println("月工资为：" + salary);

            if(mouth == emps[i].getBirthday().getMonth()) {
                System.out.println("生日快乐！奖励100");
            }
        }
    }
}
