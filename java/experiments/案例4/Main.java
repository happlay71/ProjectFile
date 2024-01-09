package experiments.案例4;

public class Main{
    public static void main(String[] args)
    {
        System.out.println("=============货物1============");
        SendTask task = new SendTask("HYX600235", 76.34);
        task.sendBefore();
        System.out.println("==========================");
        CarTransportation car = new CarTransportation("C025", "大奔", "小韩");
        task.send(car);

        System.out.println("==============货物2===========");
        SendTask tasks = new SendTask("kY888", 88.88);
        tasks.sendBefore();
        System.out.println("==========================");
        SheepTransportation sheep = new SheepTransportation("S018", "泰坦尼克号", "Jack");
        tasks.send(sheep);
        System.out.println("===========================");
        tasks.sendAfter(sheep);
    }
}



