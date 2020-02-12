package Unit02SortingBasic;

// 选择排序
// 两次循环,外循环n次,内循环n次 优化之后(每次从i+1开始, [i+1,n) (开始索引递增1,遍历次数变少)))
// 特点: 每次外循环找到最小(大)值的索引,然后交换位置
// 时间复杂度: O(n^2)
public class SelectionSort {

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 寻找[i,n)区间里的最小值的索引
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                // 使用compareTo方法比较两个Comparable对象的大小
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            // 将最小的索引位置与本次i的索引交换位置
            swap(arr, i, minIndex);
        }
    }

    private static void swap(Comparable[] arr, int i, int minIndex) {
        Comparable t = arr[i];
        arr[i] = arr[minIndex];
        arr[minIndex] = t;
    }
}
