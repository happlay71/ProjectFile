package experiments.案例4.饲养;

public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract void Jiao();
    abstract void eat(Food food);


    public interface Food {
        String gatName();
    }
}
