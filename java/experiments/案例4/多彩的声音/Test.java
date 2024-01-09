package experiments.案例4.多彩的声音;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        System.out.println("你想听什么？请输入：");
        System.out.println("0-收音机   1-随身听   2-手机");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        SampleDisplay sampleDisplay = new SampleDisplay();
        switch (choice) {
            case 0:
                Radio radio = new Radio();
                sampleDisplay.display(radio);
                break;
            case 1:
                Walkman walkman = new Walkman();
                sampleDisplay.display(walkman);
                break;
            case 2:
                MobilePhone mobilePhone = new MobilePhone();
                sampleDisplay.display(mobilePhone);
                break;
            default:
                System.out.println("请在可选择范围内选择！");
                break;
        }
    }
}
