package 面向对象;

public class A_Customer {
    private String firstName;
    private String lastName;
    private A_Account account;

    public A_Customer(String f, String l){
        this.firstName = f;
        this.lastName = l;
    }

    public A_Account getAccount(){
        return account;
    }

    public void setAccount(A_Account account){
        this.account = account;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
}

