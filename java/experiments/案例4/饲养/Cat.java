package experiments.案例4.饲养;

public class Cat extends Animal {
    @Override
    public void Jiao() {
        System.out.println("喵喵喵!");
    }

    @Override
    void eat(Food food) {
        System.out.println(super.getName() + "正在吃着香喷喷的:" + food.gatName());
    }

    public Cat(String name) {
        super(name);
    }

}
