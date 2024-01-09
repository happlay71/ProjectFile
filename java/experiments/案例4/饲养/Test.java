package experiments.案例4.饲养;

public class Test {
    public static void main(String[] args) {
        Breeder b = new Breeder("小陈");
        b.hy();
        Animal cat = new Cat("大花猫");
        cat.Jiao();
        Animal.Food f1 = new Fish();
        b.wei(cat, f1);
        Animal dog = new Dog("小黄狗");
        dog.Jiao();
        Animal.Food f = new Bone();
        b.wei(dog, f);
    }

}