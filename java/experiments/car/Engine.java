package experiments.car;

public abstract class Engine {
    private String model;
    private int speed = 0;
    private boolean state = false;
    private int i = 10;

    public Engine(String model) {
        this.model = model;
    }

    public void start() {
        this.state = true;
        System.out.println("已启动");
    }

    public void speedUp() {
        if (this.isState()) {
            this.setspeed(i);
            System.out.println("正在加速, 车速为：" + i);
            i += 10;
        } else {
            System.out.println("发动机没启动");
        }
    }

    public void stop() {
        this.state = false;
        this.setspeed(0);
        System.out.println("已停止");
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isState() {
        return state;
    }

    public int getSpeed() {
        return speed;
    }

    private void setspeed(int i) {
        this.speed = i;
    }

    public abstract boolean equals(Car car);
}
