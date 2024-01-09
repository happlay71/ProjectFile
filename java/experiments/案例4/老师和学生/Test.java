package experiments.案例4.老师和学生;

public class Test {
    public static void main(String[] args) {
        Student s = new Student("小陈", 19);
        Teacher t = new Teacher("张", 35, s);
        t.dz();
        s.dz();
        System.out.println("------------------");
        t.xw();
        s.xw();
    }
}
