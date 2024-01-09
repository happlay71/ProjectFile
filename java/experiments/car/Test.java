package experiments.car;

public class Test {
    public static void main(String[] args) {
        Car car1 = new Car("NTX3060");
        Car car2 = new Car("NTX3050");
        Car car3 = new Car("NTX3060");

        car1.start();
        car1.speedUp();
        car1.stop();

        System.out.println(car1.toString());

        if (car1.equals(car2)) {
            System.out.println("两辆汽车相等。");
        } else {
            System.out.println("两辆汽车不等。");
        }

        if (car1.equals(car3)) {
            System.out.println("两辆汽车相等。");
        } else {
            System.out.println("两辆汽车不等。");
        }
    }
}
