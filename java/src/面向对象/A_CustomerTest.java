package 面向对象;

public class A_CustomerTest {
    public static void main(String[] args) {
        A_Customer cust = new A_Customer("Jane", "Smith");
        A_Account acct = new A_Account(1000, 2000, 0.0123);

        cust.setAccount(acct);

        cust.getAccount().deposit(100);
        cust.getAccount().withdraw(960);
        cust.getAccount().withdraw(2000);

        System.out.println("Customer[" + cust.getLastName() + "," + cust.getFirstName() +
                "] has a account: id is " + cust.getAccount().getId() + ",annualInterestRate is " +
                cust.getAccount().getAnnualInterestRate() * 100 + "% ,balance is " +
                cust.getAccount().getBalance());
    }
}
