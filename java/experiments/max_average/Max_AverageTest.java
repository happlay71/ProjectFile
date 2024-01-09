package experiments.max_average;

public class Max_AverageTest {
    public static void main(String[] args) {
        Max max_average = new Max();
        Average average = new Average();
        int[] new_list;
        int[] avg_list;
        int[][] list = {{77, 68, 86, 73}, {96, 87, 89, 81}, {70, 90, 86, 81}};
        new_list = Max.selectionSort(list);
        avg_list = Average.average(list);
        for (int i = 0; i < 4; i++) {
            System.out.print(new_list[i] + "\t");
        }
        System.out.println(' ');
        for (int i = 0; i < 3; i++) {
            System.out.print(avg_list[i] + "\t");
        }
    }
}
