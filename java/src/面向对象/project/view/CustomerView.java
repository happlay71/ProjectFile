package 面向对象.project.view;

import 面向对象.project.bean.Customer;
import 面向对象.project.service.CustomerList;
import 面向对象.project.util.CMUtility;

public class CustomerView {
    private CustomerList customerList = new CustomerList(10);

    public CustomerView(){
        Customer customer = new Customer("王", '男', 23, "124131", "en@jas.com");
        customerList.addCustomer(customer);
    }
    // 显示界面
    public void enterMainMenu(){
        boolean isFlag = true;
        while (isFlag){
            System.out.println("---------------客户信息管理软件------------\n");
            System.out.println("\t\t\t\t1 添加客户");
            System.out.println("\t\t\t\t2 修改客户");
            System.out.println("\t\t\t\t3 删除客户");
            System.out.println("\t\t\t\t4 客户列表");
            System.out.println("\t\t\t\t5  退 出");
            System.out.print("\t 请选择(1-5):");

            char menu = CMUtility.readMenuSelection();
            switch(menu){
                case '1':addNewCustomer();break;
                case '2':modifyCustomer();break;
                case '3':deleteCustomer();break;
                case '4':listAllCustomers();break;
                case '5':
                    System.out.print("确认是否退出(Y/N)：");
                    char isExit = CMUtility.readConfirmSelection();
                    if(isExit == 'Y'){
                        isFlag = false;
                    }
                    break;
            }
        }
    }

    private void addNewCustomer(){
        System.out.println("-----------------添加客户----------------");

        System.out.print("姓名：");
        String name = CMUtility.readString(10);
        System.out.print("性别：");
        char gender = CMUtility.readChar();
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(13);
        System.out.print("邮箱：");
        String email = CMUtility.readString(30);

        //将上述数据封装到对象中
        Customer customer = new Customer(name, gender, age, phone, email);

        boolean isSuccess = customerList.addCustomer(customer);
        if(isSuccess){
            System.out.println("-----------------添加完成----------------");
        }else {
            System.out.println("-----------------添加失败----------------");
        }
    }

    private void modifyCustomer(){
        System.out.println("-----------------修改客户----------------");
        Customer cust;
        int number;
        for(;;){
            System.out.print("请选择待修改的客户编号(-1退出)：");
            number = CMUtility.readInt();
            if(number == -1){
                return;
            }
            cust = customerList.getCustomer(number - 1);
            if(cust == null){
                System.out.println("无法找到指定用户！");
            }else {
                break;
            }
        }
        //修改信息
        System.out.print("姓名(" + cust.getName() + ")：");
        String name = CMUtility.readString(10, cust.getName());
        System.out.print("性别(" + cust.getGender() + ")：");
        char gender = CMUtility.readChar(cust.getGender());
        System.out.print("年龄(" + cust.getAge() + ")：");
        int age = CMUtility.readInt(cust.getAge());
        System.out.print("电话(" + cust.getPhone() + ")：");
        String phone = CMUtility.readString(13, cust.getPhone());
        System.out.print("邮箱(" + cust.getEmail() + ")：");
        String email = CMUtility.readString(30, cust.getEmail());

        Customer newCust = new Customer(name, gender, age, phone, email);
        boolean isRepalaced = customerList.replaceCustomer(number - 1, newCust);
        if(isRepalaced){
            System.out.println("-----------------修改成功----------------");
        }else {
            System.out.println("-----------------修改失败----------------");
        }
    }

    private void deleteCustomer(){
        System.out.println("-----------------删除客户----------------");
        int number;
        for(;;){
            System.out.print("请选择待删除的客户编号(-1退出)：");
            number = CMUtility.readInt();
            if(number == -1){
                return;
            }
            Customer cust = customerList.getCustomer(number - 1);
            if(cust == null){
                System.out.println("无法找到指定用户！");
            }else {
                break;
            }
        }

        System.out.print("确认是否删除(Y/N)：");
        char isDelete = CMUtility.readConfirmSelection();
        if(isDelete == 'Y'){
            boolean deleteSuccess = customerList.deleteCustomer(number - 1);
            if(deleteSuccess){
                System.out.println("-----------------删除成功----------------");
            }else {
                System.out.println("-----------------删除失败----------------");
            }
        }
    }

    private void listAllCustomers(){
        System.out.println("-----------------客户列表----------------");

        int total = customerList.getTotal();
        if(total == 0){
            System.out.println("没有客户记录！");
        }else {
            System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t\t\t邮箱");
            Customer[] custs = customerList.getAllCustomers();
            for(int i = 0; i < custs.length; i++){
                Customer cust = custs[i];
                System.out.println((i + 1) + "\t" + cust.getName() + "\t" + cust.getGender() + "\t" + cust.getAge() + "\t" +
                        cust.getPhone() + "\t" + cust.getEmail());
            }
        }
        System.out.println("----------------客户列表完成--------------\n");
    }

    public static void main(String[] args){
        CustomerView view = new CustomerView();
        view.enterMainMenu();
    }
}
