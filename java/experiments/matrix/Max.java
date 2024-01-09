package experiments.matrix;

public class Max {
    int count = 0;

    public int max(int[][] mat) {
        int max = mat[0][0];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (max < mat[i][j]) {
                    max = mat[i][j];
                }
            }
        }
        return max;
    }

    public int[][] maxRowCol(int m, int[][] mat) {
        int[][] row = new int[25][2];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (m == mat[i][j]) {
                    row[count][0] = i;
                    row[count][1] = j;
                    count++;
                }
            }
        }
        return row;
    }
}
