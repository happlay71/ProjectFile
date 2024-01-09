package experiments.案例4;

abstract class person {
    public String name;
    public String address;
    public person(String name, String address){
        this.name=name;
        this.address=address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void add(){

    }
}

class employee extends person {
    public String id;
    public double salary;
    public int age;

    public employee(String name, String address,String id,double salary,int age) {
        super(name, address);
        this.id=id;
        this.salary =salary;
        this.age=age;
    }

    public String getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public int getAge() {
        return age;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void add(){
        salary*=1.1;
    }

    public String getID() {
        return id;
    }
}

class manager extends employee{

    public String rank;
    public manager(String name, String address, String id, double salary, int age,String rank) {
        super(name, address, id, salary, age);
        this.rank=rank;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
    public void add(){
        salary*=1.2;
    }

}
public class Jingli {
    public static void main(String[] args) {
        employee worker = new employee("张三", "郑州", "1001", 4000.0, 2);
        manager manager1 = new manager("李四", "郑州", "1122", 8000.0, 5, "经理");
        manager manager2 = new manager("王五", "郑州", "1100", 10000.0, 4, "高级经理");
        print(worker);
        print(manager1);
        print(manager2);
    }
    public static void print(employee a){
        System.out.println("----------------------普通员工信息表---------------------");
        System.out.println("|姓名|\t\t"+"|工号|\t\t"+"|工资|\t\t"+"|工龄|\t\t"+"|地址|");
        System.out.println(a.getName() + "\t\t\t"+a.getID()+"\t\t"+a.getSalary()+"\t\t "+a.getAge()+"\t\t\t"+a.getAddress());
        a.add();
        System.out.println("-------------------------------------------------------");
        System.out.println("由于"+a.getName()+ "在公司辛勤工作,因此公司决定鼓励,涨薪为:" + a.getSalary());
        System.out.println();
    }
    public static void print(manager a){
        System.out.println("----------------------经理阶层信息表----------------------------");
        System.out.println("|姓名|  \t"+"|工号|  \t"+"|职称|  \t"+"|工资|  \t"+"|工龄|  \t"+"|地址|");
        System.out.println(a.getName() + "   \t"+a.getID()+" \t"+a.getRank()+" \t"+a.getSalary()+"\t "+a.getAge()+"   \t"+a.getAddress());
        a.add();
        System.out.println("-------------------------------------------------------------");
        System.out.println("由于"+a.getName()+ "在公司辛勤工作,因此公司决定鼓励,涨薪为:" + a.getSalary());
        System.out.println();
    }
}