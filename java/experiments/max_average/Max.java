package experiments.max_average;

public class Max {

    static int t = 0;
    static int[] n_list = new int[4];
    public static int[] selectionSort(int[][] ls) {
        for (int i = 0, j = 0; j < 4; j++) {
            for (; i < ls.length - 1; i++) {
                if (ls[i][j] < ls[i + 1][j]) {
                    t = ls[i + 1][j];
                    n_list[j] = t;
                }
            }
            i = 0;
        }
        return n_list;
    }

}



