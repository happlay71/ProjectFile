package experiments.案例4.老师和学生;

public abstract class Person {
    private final String name;
    private final int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    abstract void dz();
    abstract void xw();

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
}
