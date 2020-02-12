package Unit02SortingBasic;

// 冒泡排序
// 两次循环,外循环n次,内循环n次   优化之后([1,newLength-1)(结束索引递减1,遍历次数变少))
// 特点: 每次内循环,与之前比较是否要交换位置.
// 时间复杂度: O(n^2)
public class BubbleSort {
    public static void sort(Comparable[] arr) {


        // 外循环
        for (int i = 0; i < arr.length; i++) {

            int newLength = arr.length; // 优化:新的结束循环的索引
            // 内循环,每次将最大的元素放到最后
            for (int j = 1; j < newLength; j++) {

                // 每次与前一个数作比较,交换位置
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j - 1, j);
                    newLength--; // 维护新的长度,最后的元素可以不再考虑
                }
            }
        }
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Integer[] arr = {3, 5, 7, 1, 2, 0, 4, 6};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
