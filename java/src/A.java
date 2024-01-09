public class A {
    public static void main(String[] args) {
        int i = 0;
        try {
            i = Integer.parseInt(args[0]);
        } catch (Exception e) {
        }
        switch (2) {
            case 0:
                System.out.println("严防");
            case 1:
                System.out.println("严控");
            case 2:
                System.out.println("预防");
            case 3:
                System.out.println("瘟疫");
                break;
            default:
                System.out.println("武汉加油");
        }
    }
}
