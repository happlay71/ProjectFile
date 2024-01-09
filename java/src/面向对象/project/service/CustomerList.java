package 面向对象.project.service;

import 面向对象.project.bean.Customer;

public class CustomerList {
    private Customer[] customers;// 保存客户对象的数组
    private int total = 0;// 记录已保存对象的数量

    /**
     * 用来初始化customers数组的构造器
     * @param totalCustomer：指定数组长度
     */
    public CustomerList(int totalCustomer){
        customers = new Customer[totalCustomer];
    }

    public boolean addCustomer(Customer customer){
        if(total >= customers.length){
            return false;
        }
        customers[total++] = customer;
        return true;
    }

    public boolean replaceCustomer(int index, Customer cust){
        if(index < 0 || index >= total){
            return false;
        }
        customers[index] = cust;
        return true;
    }

    public boolean deleteCustomer(int index){
        if(index < 0 || index >= total){
            return false;
        }
        for(int i = index; i < total - 1; i++){
            customers[i] = customers[i + 1];
        }
        customers[total - 1] = null;
        total--;
        return true;
    }

    public Customer[] getAllCustomers(){
        Customer[] custs = new Customer[total];
        for(int i =0; i < total; i++){
            custs[i] = customers[i];
        }
        return custs;
    }

    public Customer getCustomer(int index){
        if(index < 0 || index >= total){
            return null;
        }
        return customers[index];
    }

    public int getTotal(){
        return total;
    }
}
