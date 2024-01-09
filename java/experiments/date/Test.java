package experiments.date;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        Date date = new Date();

        System.out.println("请输入年份，月份，日期(以回车隔开)：");
        date.setYear(sca.nextInt());
        date.setMouth(sca.nextInt());
        date.setDay(sca.nextInt());

        int year = date.getYear();
        int mouth = date.getMouth();
        int day = date.getDay();

        if (mouth >= 1 && mouth <= 12 && day >= 1 && day <= 31){
            switch (mouth) {
                case 2:
                    switch (day) {
                        case 29:
                            if(!(year % 400 == 0 || (year % 4 ==0 && year % 100 != 0))) {
                                date.setYear(2023);
                                date.setMouth(1);
                                date.setDay(1);
                                System.out.println("输入超出常规，已校准");
                            } break;
                        case 30:
                        case 31:
                            date.setYear(2023);
                            date.setMouth(1);
                            date.setDay(1);
                            System.out.println("输入超出常规，已校准");
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day > 30) {
                        date.setYear(2023);
                        date.setMouth(1);
                        date.setDay(1);
                        System.out.println("输入超出常规，已校准");
                    }
                    break;
            }
        }

        year = date.getYear();
        mouth = date.getMouth();
        day = date.getDay();
        System.out.println(year + "/" + mouth + "/" + day);
    }
}
