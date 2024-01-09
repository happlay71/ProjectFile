package experiments.案例4.老师和学生;

public class Teacher extends Person{
    Student s ;
    public Teacher(String name, int age,Student s) {
        super(name, age);
        this.s = s;
    }
    @Override
    void dz() {
        System.out.println(getName() + "老师在给" +s.getName() +"同学讲课!");
    }
    @Override
    void xw() {
        System.out.println("请" + s.getName() + "同学做一下自我介绍!");
    }
}
