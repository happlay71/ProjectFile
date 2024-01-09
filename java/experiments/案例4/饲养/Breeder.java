package experiments.案例4.饲养;

public class Breeder {
    private String name;



    public Breeder(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void hy(){  // 说明
        System.out.println("欢迎来到动物园!");
        System.out.println("我是饲养员:" + getName());
    }
    public void wei(Animal a , Animal.Food food){
        a.eat(food);
    }
}
