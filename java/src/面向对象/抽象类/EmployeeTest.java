package 面向对象.抽象类;

public class EmployeeTest {
    public static void main(String[] args) {
        Manager manager = new Manager("库克", 131, 132, 23);
        manager.work();

        // 创建抽象类的匿名子类
        Employee e = new Employee() {
            @Override
            public void work() {
                System.out.println("hhh");
            }
        };
        e.work();
    }
}
