package experiments.startstop;

public class Test {
    public static void main(String[] args) {
        StartStop[] ss = new StartStop[]{new Meeting(), new Car()};

        // 增强for循环
        for (StartStop s : ss) {
            s.start();
            s.stop();
        }
    }
}
