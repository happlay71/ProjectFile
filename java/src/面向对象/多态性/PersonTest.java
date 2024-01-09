package 面向对象.多态性;

public class PersonTest {
    public static void main(String[] args) {
        Person p1 = new Person();
        p1.eat();

        Person p2 = new Man();
        p2.eat();

        // 向下转型（强制类型转换）
        Man m1 = (Man)p2;
        m1.earnMoney();

        // instanceof关键字
        if(p2 instanceof Woman){
            System.out.println("Woman");
        }

        Object o1 = new Woman();
        Person p3 = (Person) o1;
        if(p3 instanceof Person){
            p3.eat();
            System.out.println("Woman");
        }
    }


}
