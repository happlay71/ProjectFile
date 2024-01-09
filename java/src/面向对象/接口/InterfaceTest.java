package 面向对象.接口;

public class InterfaceTest {
    public static void main(String[] args) {
        System.out.println(Flyable.MAX_SPEED);

    }
}

interface Flyable{
    // 全局常量
    public static final int MAX_SPEED = 7900;
    int MIN_SPEED = 1; // 省略了前面的修饰

    public abstract void fly();
    void stop();
}

interface Attackable{
    void attack();
}

// implements 实现接口（需要实现(类似重写）抽象方法）
class Plane implements Flyable{
    @Override
    public void fly() {
        System.out.println("通过引擎起飞");
    }

    @Override
    public void stop() {
        System.out.println("驾驶员减速停止");
    }
}

abstract class Kite implements Flyable{
    @Override
    public void fly() {

    }
}

class Bullet extends Object implements Flyable, Attackable{
    @Override
    public void fly() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void attack() {

    }
}
