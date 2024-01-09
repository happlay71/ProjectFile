package experiments.案例4;

public class CarTransportation extends Transportation {
    void transport() {
        System.out.println("汽车运输中。。。");
    }

    public CarTransportation() {

    }

    public CarTransportation(String number, String model, String admin) {
        super(number, model, admin);
    }
}
