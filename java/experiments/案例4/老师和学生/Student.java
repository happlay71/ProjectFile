package experiments.案例4.老师和学生;

public class Student extends Person {
    public Student(String name, int age) {
        super(name, age);
    }
    @Override
    void dz() {
        System.out.println(getName() + "同学在听老师讲课!");
    }
    @Override
    void xw() {
        System.out.println("大家好,我叫" + getName() + "今年" + getAge() + "岁了,爱好是学习!");
    }
}


