package experiments.max_average;

public class Average {
    static int sum;
    static int[] avg = new int[3];
    public static int[] average(int[][] ls) {
        for (int i = 0; i < ls.length; i++) {
            for (int j = 0; j < ls[i].length; j++) {
                sum += ls[i][j];
            }
            avg[i] = sum / 4;
        }
        return avg;
    }
}
