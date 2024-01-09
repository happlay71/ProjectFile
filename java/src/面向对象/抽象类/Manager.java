package 面向对象.抽象类;

public class Manager extends Employee {
    private double bonus;

    public Manager(double bonus){
        super();
        this.bonus = bonus;
    }

    public Manager(String name, int id, double salary, double bonus){
        super(name, id, salary);
        this.bonus = bonus;
    }

    public void work() {
        System.out.println("管理员工，提高公司的效率");
    }
}
