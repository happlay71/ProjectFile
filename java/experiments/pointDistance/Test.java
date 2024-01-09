package experiments.pointDistance;

public class Test {
    public static void main(String[] args) {
        Point point = new Point(0, 0);
        Point p = new Point(3, 4);
        Point p1 = new Point(6, 8);

        System.out.println(point.distance(p));
        System.out.println(point.distance(6, 8));
        System.out.println(point.distance(p, p1));
    }
}
