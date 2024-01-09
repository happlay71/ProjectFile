package 面向对象.内部类;

public class InnerClassTest {
    public static void main(String[] args) {
        // 创建静态内部类实例
        Person.Dog dog = new Person.Dog();
        dog.show();

        // 创建静态内部类实例
        Person p = new Person();
        Person.Bird bird = p.new Bird();
        bird.sing();
    }
}

class Person{
    String name;
    int age;

    public void eat(){
        System.out.println("人，吃饭");
    }

    // 静态成员内部类
    static class Dog{
        String name;
        int age;

        public void show() {
            System.out.println("狗");
        }
    }

    // 非静态成员内部类
    class Bird{
        String name;

        public void sing() {
            System.out.println("唱歌");
        }

        public void display(String name){
            System.out.println(name);  // 方法形参
            System.out.println(this.name); // 内部类的属性
            System.out.println(Person.this.name); // 外部类的属性
        }
    }

    // 局部内部类
    public void method(){
        class AA{

        }
    }

    {
        class BB{}
    }

    public Person() {
        class CC{}
    }
}
