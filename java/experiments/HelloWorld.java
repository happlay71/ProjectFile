package experiments;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello World");

        // 水仙花数
        int count = 0;
        int i = 0;
        for(i = 100; i < 1000; i++){
            int a = i % 10;
            int b = (i / 10) % 10;
            int c = i / 100;
            if((int)(pow(a) + pow(b) + pow(c)) == i){
                System.out.println(i);
                count++;
                if(count == 3) break;
            }
        }
    }
    public static double pow(int i){
        double x = Math.pow(i, 3);
        return x;
    }

}
