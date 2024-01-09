package experiments.案例4.饲养;


public class Dog extends Animal {
    @Override
    public void Jiao() {
        System.out.println("旺旺旺!");
    }

    @Override
    void eat(Food food) {
        System.out.println(super.getName() + "正在吃着香喷喷的:" + food.gatName());
    }

    public Dog(String name) {
        super(name);
    }
}
