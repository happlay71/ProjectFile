package experiments.point;

public class Test {
    public static void main(String[] args) {
        Point point = new Point();

        int[][] poi = new int[10][2];
        point.creat(poi);
        int[] dist = point.distance(poi);
        point.sort(dist);

        for (int i = 0; i < 10; i++) {
            System.out.println(dist[i]);
        }
    }
}
