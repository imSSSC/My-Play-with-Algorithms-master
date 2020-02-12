package Unit02SortingBasic;

// 希尔排序
// 多次循环
// 特点: 将数组渐变为有序数组(插入排序的升华)
// 时间复杂度: O(n(logn))
public class ShellSort {
    public static void sort(Comparable[] arr) {

        int n = arr.length;

        // 计算 increment sequence: 1, 4, 13, 40 ....
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {

            // h-sort the array
            for (int i = h; i < n; i++) {

                // 对 arr[i], arr[i-h], arr[i-3*h]... 使用插入排序
                Comparable e = arr[i];
                int j = i;
                for (; j >= h && e.compareTo(arr[j - h]) < 0; j -= h) {
                    arr[j] = arr[j - h];
                }
                arr[j] = e;
            }

            h /= 3;
        }
    }

}
