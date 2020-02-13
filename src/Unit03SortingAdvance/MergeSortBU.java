package Unit03SortingAdvance;

import java.util.Arrays;

// 归并排序,向上归并
public class MergeSortBU {

    public static void sort(Comparable[] arr) {

        int n = arr.length;

        // Merge Sort Bottom Up 无优化版本
        for (int sz = 1; sz < n; sz *= 2) {
            for (int i = 0; i < n - sz; i += sz + sz) {
                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2sz-1] 进行归并
                merge(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, n - 1));
            }
        }
    }

    // 将arr[l,mid] 和 arr[mid+1,r]合并
    private static void merge(Comparable[] arr, int l, int mid, int r) {

        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);

        // 设置两段的起始索引
        int i = l, j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i > mid) {  // 如果左半部分元素已经全部处理完毕
                arr[k] = aux[j - l];
                j++;
            } else if (j > r) {   // 如果右半部分元素已经全部处理完毕
                arr[k] = aux[i - l];
                i++;
            } else if (aux[i - l].compareTo(aux[j - l]) < 0) {  // 左半部分所指元素 < 右半部分所指元素
                arr[k] = aux[i - l];
                i++;
            } else {  // 左半部分所指元素 >= 右半部分所指元素
                arr[k] = aux[j - l];
                j++;
            }
        }
    }


    // 测试MergeSort
    public static void main(String[] args) {

        // Merge Sort BU 也是一个O(nlogn)复杂度的算法，虽然只使用两重for循环
        // 所以，Merge Sort BU也可以在1秒之内轻松处理100万数量级的数据
        // 注意：不要轻易根据循环层数来判断算法的复杂度，Merge Sort BU就是一个反例
        // 关于这部分陷阱，推荐看我的《玩转算法面试》课程，第二章：《面试中的复杂度分析》：）
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("Unit03SortingAdvance.MergeSortBU", arr);

        return;
    }
}
