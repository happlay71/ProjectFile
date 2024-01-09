package experiments.案例3.购书;

import java.util.Scanner;

public class BuyBook {


    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Book[] books = new Book[2];
        BookOption bo = new BookOption();
        bo.bookInfo(books);
    }
}
