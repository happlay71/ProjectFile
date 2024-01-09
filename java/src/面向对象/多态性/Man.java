package 面向对象.多态性;

public class Man extends Person{
    boolean isSmoking;

    public void earnMoney() {
        System.out.println("男人负责赚钱养家");
    }

    // 对父类方法的重写
    public void eat() {
        System.out.println("男人多吃肉，长肌肉");
    }

    public void walk() {
        System.out.println("男人霸气的走路");
    }
}
