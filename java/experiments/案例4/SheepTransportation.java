package experiments.案例4;

public class SheepTransportation extends Transportation {
    void transport() {
        System.out.println("轮船运输中。。。");
    }

    public SheepTransportation() {

    }

    public SheepTransportation(String number, String model, String admin) {
        super(number, model, admin);
    }
}
