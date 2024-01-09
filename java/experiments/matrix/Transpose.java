package experiments.matrix;

public class Transpose {
    int[][] n_mat = new int[5][5];
    public int[][] transpose(int[][] mat) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                n_mat[j][i] = mat[i][j];
            }
        }
        return n_mat;
    }
}
