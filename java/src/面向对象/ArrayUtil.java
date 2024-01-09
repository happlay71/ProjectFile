package 面向对象;

public class ArrayUtil {
    // 求数组最大值
    public static int getMax(int[] arr){
        int maxValue = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(maxValue < arr[i]){
                maxValue = arr[i];
            }
        }
        return maxValue;
    }

    // 求数组最小值
    public static int getMin(int[] arr){
        int mixValue = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(mixValue > arr[i]){
                mixValue = arr[i];
            }
        }
        return mixValue;
    }

    // 求数组的和
    public static int getSum(int[] arr){
        int sum = 0;
        // 未解*
        for (int j : arr) {
            sum += j;
        }
        return sum;
    }

    //求数组的平均值
    public static int getAvg(int[] arr){
        return getSum(arr) / arr.length;
    }

    // 反转数组
    public static void reverse(int[] arr){
        for(int i = 0; i < arr.length / 2; i++){
            int temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    // 复制数组
    public static int[] copy(int[] arr){
        int[] arr1 = new int[arr.length];
        for(int i = 0; i < arr1.length; i++){
            arr1[i] = arr[i];
        }
        return arr1;
    }


}
