package experiments.案例3.手机;

public class Phone {
    private String brand;
    private String types;
    private String OS;
    private double price;
    private int memory;

    public Phone() {}
    public Phone(String brand,String types,String OS,double price,int memory) {
        this.brand=brand;
        this.types=types;
        this.OS=OS;
        this.price=price;
        this.memory=memory;

    }
    public void show() {
        System.out.println("该手机配置信息如下：");
        System.out.println("品牌："+brand);
        System.out.println("型号："+types);
        System.out.println("操作系统："+OS);
        System.out.println("价格："+price);
        System.out.println("内存："+memory);
    }
    public void call(String number) {
        System.out.println("自动拨号系统已开启，请输入快捷号码：");
        System.out.println("1：警察局\n2：消防局\n3：医院");
        String phoneNumber=null;
        switch(number) {
            case "1":
                System.out.println("正在打电话给警察局...");
                break;
            case "2":
                System.out.println("正在打电话给消防局...");
                break;
            case "3":
                System.out.println("正在打电话给医院...");

        }
    }
    public void game(String number) {
        System.out.println("游戏列表：\n1：王者荣耀\n2：欢乐斗地主\n3:微信");
        switch (number) {
            case "1":
                System.out.println("正在打开王者荣耀");
                System.out.println("敌军还有五秒到达战场...");
                break;
            case "2":
                System.out.println("正在打开欢乐斗地主");
                System.out.println("您余额不足2000欢乐豆，系统今日第一次送出...");
                break;
            case "3":
                System.out.println("正在打开微信");
                System.out.println("未读消息：\n女朋友：我们分手吧！\n爸爸：家里破产了，你快别敲代码了，回家种田！\n女神：我饿了QAQ");
                break;
        }

    }
    public void music(String number) {
        System.out.println("歌曲列表：\n1：陈奕迅-《爱情转移》\n2：周杰伦-《本草纲目》\n3：蔡徐坤-《只因你太美》");
        switch(number) {
            case "1":
                System.out.println("已经开始为您播放《爱情转移》");
                System.out.println("回忆是抓不到的月光，握紧就变黑暗...");
                break;
            case "2":
                System.out.println("已经开始为您播放《本草纲目》");
                System.out.println("让我来调个配方，专治你媚外的内伤...");
                break;
            case "3":
                System.out.println("已经开始为您播放《只因你太美》");
                System.out.println("鸡你太美！");
                break;
        }

    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand=brand;
    }
    public String getTypes() {
        return types;
    }
    public void setTypes(String types) {
        this.types=types;
    }
    public String getOs() {
        return OS;
    }
    public void setOs(String OS) {
        this.OS=OS;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price=price;
    }
    public int getMemory() {
        return memory;
    }
    public void setMemory(int memory) {
        this.memory=memory;
    }

}
