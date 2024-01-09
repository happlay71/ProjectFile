package experiments.案例4;

import java.util.Scanner;

public class Print {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=1;i<=n;i++){
            for(int j=n-i;j>=1;j--){
                System.out.print(" ");
            }
            for(int m=1;m<=i*2-1;m++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
