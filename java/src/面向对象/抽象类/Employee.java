package 面向对象.抽象类;

public abstract class Employee {
    private String name;
    private int id;
    private double salary;

    public Employee(){
        super();
    }

    public Employee(String name, int id, double salary){
        super();
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public abstract void work();
}
