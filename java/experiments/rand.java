package experiments;

public class rand {
    public static void main(String[] args) {
        // System.out.println((int)(Math.random() * 100 - 10));
        int count = 1;
        for (int i = 1; i <= 120; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                if (count % 10 == 0) {
                    System.out.println(i);
                    count = 1;
                    continue;
                }
                System.out.print(i + " ");
                count++;
            }
        }



    }
}
