package experiments.matrix;

public class Matrix {
    int[][] mat = new int[5][5];
    public int[][] matrix() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                mat[i][j] = (int)(Math.random() * (20 - 5));
//                System.out.println(mat[i][j]);
            }
        }
        return mat;
    }
}
