package experiments.案例3.购书;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static experiments.案例3.购书.BuyBook.scanner;

public class BookOption {
    boolean isFlag = true;
    public void bookInfo(Book[] books) {
        books[0] = new Book(1, "sadfa", "java基础", 50, 500);
        books[1] = new Book(2, "safaf", "c基础", 40, 500);

        System.out.println("--------------------书籍---------------------");
        System.out.println("编号\t\tU编号\t\t名称\t\t\t价格\t\t库存");
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].getId() + "\t\t" + books[i].getU_id() + "\t\t" + books[i].getName() + "\t\t" +
                    books[i].getPrice() + "\t\t" + books[i].getNumber());
        }
        System.out.println("--------------------------------------------");
        int[] sum = new int[books.length];
        if (isFlag) {
            for (int i = 0; i < books.length; i++) {
                sum[i] = books[i].getNumber();
            }
            isFlag = false;
        }
        bookOrder(books);
        orderList(books, sum);
    }

    public static void option(Book[] books, int id, int num) {
        int sum = books[id - 1].getNumber();
        books[id - 1].setNumber(sum - num);
    }



    public static void bookOrder(Book[] books) {

        System.out.print("请输入想要购买的书籍编号：");
        int id = scanner.nextInt();
        if (id > books.length || id <= 0) {
            return;
        }
        System.out.print("请输入想要购买的数量：");
        int num = scanner.nextInt();
        if (num <= books[id - 1].getNumber()) {
            option(books, id, num);
        }
        System.out.print("是否要继续购买(Y/N)：");
        String w = scanner.next();
        if (Objects.equals(w, "Y")) {
            bookOrder(books);
        } else {
            System.out.println("--------------------------------------------");
        }
    }

    public static void orderList(Book[] books, int[] sum) {

        System.out.println("--------------------订单---------------------");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("订单编号：" + formatter.format(date));
        System.out.println("书名\t\t购买数量\t\t单价");
        int[] nums = new int[books.length];
        int money = 0;
        for (int i = 0; i < books.length; i++) {
            nums[i] = sum[i] - books[i].getNumber();
            money = money + nums[i] * books[i].getPrice();
            System.out.println(books[i].getName() + "\t\t" + nums[i] + "\t\t" + books[i].getPrice());
        }
        System.out.println("消费了：" + money + "元");
        System.out.println("--------------------------------------------");
    }
}
