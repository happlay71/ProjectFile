package experiments.案例2;

public class Shopping {
    public static void main(String[] args) {
        int[] goods = {1, 2, 3, 5};
        int all = 0;
        int money = 0;
        for (int i = 0; i < goods.length; i++) {
            all = 8 / goods[i];
            money = 8 % goods[i];
            if (i == 0) {
                System.out.println("可以买" + all + "只铅笔，" + "还剩" + money + "元");
            } else if (i == 1) {
                System.out.println("可以买" + all + "个橡皮，" + "还剩" + money + "元");
            } else if (i == 2) {
                System.out.println("可以买" + all + "瓶可乐，" + "还剩" + money + "元");
            } else {
                System.out.println("可以买" + all + "个零食，" + "还剩" + money + "元");
            }
        }
    }
}
