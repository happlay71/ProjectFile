package experiments.startstop;

public class Meeting implements StartStop{
    private boolean state = false;


    @Override
    public void start() {
        state = true;
        System.out.println("会议开始");
    }

    @Override
    public void stop() {
        state = false;
        System.out.println("会议结束");
    }
}
