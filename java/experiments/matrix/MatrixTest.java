package experiments.matrix;

public class MatrixTest {
    public static void main(String[] args) {
        Matrix mat = new Matrix();
        Max max = new Max();
        Transpose transpose = new Transpose();
        int[][] matr;
        int[][] mrc;
        int[][] tran;
        matr = mat.matrix();
        mrc = max.maxRowCol(max.max(matr), matr);
        tran = transpose.transpose(matr);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matr[i][j] + "\t");
                if (j == 4) {
                    System.out.print("\n");
                }
            }
        }

        System.out.println("\n最大元素值: " + max.max(matr));
        for (int i = 0; i < max.count; i++) {
            System.out.println((mrc[i][0] + 1) + "行, " + (mrc[i][1] + 1) + "列 ");
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(tran[i][j] + "\t");
                if (j == 4) {
                    System.out.print("\n");
                }
            }
        }

    }
}
