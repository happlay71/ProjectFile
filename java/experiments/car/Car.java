package experiments.car;

import java.util.Objects;

public class Car extends Engine{
    public Car(String model) {
        super(model);
    }

    @Override
    public String toString() {
        return "{ 这辆汽车的型号为：" + getModel() +
                '，' + "车速为：" + getSpeed() +
                '}';
    }

    @Override
    public boolean equals(Car car) {
        return Objects.equals(this.getModel(), car.getModel());
    }
}
