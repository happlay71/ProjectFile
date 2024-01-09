package experiments.案例4.多彩的声音;

import java.util.Scanner;

public class SampleDisplay {
    public void display(Soundable soundable) {
        soundable.playSound();
        System.out.println("您是否要降低音量？");
        System.out.println("1-是     2-否");
        Scanner scan = new Scanner(System.in);
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                soundable.decreaseVolume();
                break;
            case 2:
                break;
            default:
                System.out.println("请在可选择范围内选择！");
        }
        System.out.println("您是否要关机？");
        System.out.println("1-是     2-否");
        choice = scan.nextInt();
        switch (choice) {
            case 1:
                soundable.stopSound();
                break;
            case 2:
                break;
            default:
                System.out.println("请在可选择范围内选择！");
        }
    }

}
