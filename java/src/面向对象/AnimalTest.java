package 面向对象;

public class AnimalTest {
    public static void main(String[] args){
        Animal a = new Animal();
        a.setLegs(4);
        a.show();
    }
}

class Animal{
    String name;
    private int age;
    private int legs;
    // 构造器--this关键字
    public Animal(){
    }

    public Animal(int legs){
        this();
        this.setLegs(2);
    }

    // 封装性--对属性的设置
    public void setLegs(int legs){
        if(legs >= 0 && legs % 2 == 0){
            this.legs = legs;
        }else{
            this.legs = 0;
        }
    }
    // 获取值
    public int getLegs(){
        return legs;
    }

    public void eat(){
        System.out.println("动物进食");
    }

    public void show(){
        System.out.println("name = " + name + ", age" + age + ", legs" + legs);
    }

    // 提供关于属性age的get和set方法
    public int getAge(){
        return age;
    }

    public void setAge(int a){
        age = a;
    }
}
