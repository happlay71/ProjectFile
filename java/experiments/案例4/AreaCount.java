package experiments.案例4;
import java.util.Scanner;
public class AreaCount {
    public void menu() {
        System.out.println("输入1计算长方形面积及周长");
        System.out.println("输入2计算圆形面积及周长");
        Scanner sc=new Scanner(System.in);
        int choice=sc.nextInt();
        if(choice==1) {
            System.out.println("请输入长方形的长和宽");
            double width=sc.nextDouble();
            double length=sc.nextDouble();
            rectangle zfx=new rectangle();
            zfx.area(width,length);
            zfx.perimeter(width,length);
        }else if(choice==2) {
            System.out.println("请输入圆形的半径");
            double r=sc.nextDouble();
            circle yx=new circle();
            yx.area(r);
            yx.perimeter(r);
        }
    }
    public static void main(String[] args) {
        AreaCount caidan=new AreaCount();
        caidan.menu();
    }
    abstract class graph{
        double area=0;
        double perimeter=0;
    }
    class rectangle extends graph{
        double width;
        double length;
        double area;
        double perimeter;
        public void area(double width,double length) {
            area=width*length;
            System.out.println("该长方形的面积为："+area);
        }
        public void perimeter(double width,double length){
            perimeter=2*(width+length);
            System.out.println("该长方形的周长为："+perimeter);
        }
    }
    class circle extends graph{
        double r;
        double area;
        double perimeter;
        public void area(double r) {
            area=3.14*r*r;
            System.out.println("该圆的面积为："+area);
        }
        public void perimeter(double r) {
            perimeter=2*3.14*r;
            System.out.println("该圆的周长为："+perimeter);
        }

    }
}
