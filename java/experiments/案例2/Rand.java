package experiments.案例2;

import java.util.Random;
import java.util.Scanner;

public class Rand {
    static String[] arr = new String[3];

    public static void main(String[] args) {
        addAudience();
        showAudiences();
        String name = luckAudience();
        System.out.println("幸运观众为："+name);
    }

    private static String luckAudience() {
        int idx = new Random().nextInt(arr.length);
        return arr[idx];
    }

    private static void showAudiences() {
        System.out.println("观众：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private static void addAudience() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; ++i) {
            System.out.println("存储第" + (i + 1) + "个观众：");
            String audienceName = sc.next();
            arr[i] = audienceName;
        }
    }
}
