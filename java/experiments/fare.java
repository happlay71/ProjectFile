package experiments;

public class fare {
    public static void main(String[] args) {
        double price = 0.2;
        double weight = 5;
        int s = 1200;
        double freight = 0;

        switch (s / 250) {
            case 12: freight = price * weight * s * (1 - 0.15);break;
            case 8: freight = price * weight * s * (1 - 0.1);break;
            case 4: freight = price * weight * s * (1 - 0.08);break;
            case 2: freight = price * weight * s * (1 - 0.05);break;
            case 1: freight = price * weight * s * (1 - 0.02);break;
            default: freight = price * weight * s;
        }
        System.out.println(freight);

    }
}
