package experiments.案例3.手机;

public class Test {
    public static void main(String[] args) {
        Phone p1=new Phone();
        p1.setBrand("华为");
        p1.setTypes("荣耀");
        p1.setOs("鸿蒙");
        p1.setMemory(64);
        p1.setPrice(3000);
        p1.show();
        p1.call("1");
        p1.game("3");
        p1.music("2");
    }
}
