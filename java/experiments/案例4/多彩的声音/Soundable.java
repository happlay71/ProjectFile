package experiments.案例4.多彩的声音;

public interface Soundable {
    public void playSound();
    public void decreaseVolume();
    public void stopSound();
}
class Radio implements Soundable {
    @Override
    public void playSound() {
        System.out.println("收音机发出声音：滋滋滋~~~");
    }
    @Override
    public void decreaseVolume() {
        System.out.println("已降低收音机音量");
    }
    @Override
    public void stopSound() {
        System.out.println("已关闭收音机");
    }
}
class Walkman implements Soundable {
    @Override
    public void playSound() {
        System.out.println("随身听发出声音：铃铃~~~");
    }

    @Override
    public void decreaseVolume() {
        System.out.println("已降低随身听音量");
    }

    @Override
    public void stopSound() {
        System.out.println("已关闭随身听");
    }
}
class MobilePhone implements Soundable {
    @Override
    public void playSound() {
        System.out.println("手机发出来电铃声：叮当、叮当");
    }

    @Override
    public void decreaseVolume() {
        System.out.println("已降低手机音量");
    }

    @Override
    public void stopSound() {
        System.out.println("已关闭手机");
    }
}