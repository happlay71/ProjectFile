package experiments.startstop;

public class Car implements StartStop{
    private boolean state = false;


    @Override
    public void start() {
        state = true;
        System.out.println("汽车启动");
    }

    @Override
    public void stop() {
        state = false;
        System.out.println("汽车停止");
    }
}
