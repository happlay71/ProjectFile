package 面向对象;

public class SingletonTest1 {

}

// 饿汉式
class Bank{
    // 私有化构造器
    private Bank(){}

    // 内部创建类的对象--静态
    private static Bank instance = new Bank();

    // 提供方法获取创建的对象
    public static Bank getInstance() {
        return instance;
    }
}

// 懒汉式
class Lank{
    private Lank(){}

    private static Lank instance = null;

    public static Lank getInstance() {
        if(instance == null){
            instance = new Lank();
        }
        return instance;
    }
}
