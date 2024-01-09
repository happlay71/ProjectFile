package experiments.point;

import java.util.Random;

public class Point {
    Random random = new Random();

    int[][] o = new int[][]{{0, 0}};
    int[] dist = new int[10];

    public void creat(int[][] poi) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 2; j++) {
//                poi[i][j] = random.nextInt();
                poi[i][j] = (int)(Math.random() * 10);
            }
            System.out.println("点" + (i + 1) + ":(" + poi[i][0] + "," + poi[i][1] + ")");
        }
    }

    public int[] distance(int[][] poi) {
        for (int i = 0; i < 10; i++) {
            int poi1 = poi[i][0] * poi[i][0];
            int poi2 = poi[i][1] * poi[i][1];
            dist[i] = Math.abs(poi1 + poi2);
        }
        return dist;
    }

    public int[] sort(int[] dist){
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist.length - 1; j++) {
                if(dist[j] > dist[j + 1]) {
                    int t = dist[j];
                    dist[j] = dist[j + 1];
                    dist[j + 1] = t;
                }
            }
        }
        return dist;
    }
}
