package Unit04Heap;

/**
 * @auther: snake
 * @date: 2020/2/23 21:31
 */
// 堆排序: 不使用一个额外的最大堆,直接在原数组上进行原地的堆排序
public class HeapSort {

    public static void sort(Comparable[] arr) {

        int n = arr.length;

        // 注意,此时我们的堆是从0开始索引的
        // 从(最后一个元素的索引-1)/2开始
        // 最后一个元素的索引=n-1
        // parent: k, leftChild: 2k+1, rightChild: 2k+2
        // leftChild or rightChild: k, parent: k-1/2
        for (int i = (n - 1 - 1) / 2; i >= 0; i--) // 建堆(从最后一个非叶子节点开始)
            shiftDown(arr, n, i);

        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            shiftDown(arr, i, 0);// 这时最大长度为i,也就是索引最大为i-1. 最大的数已经被换到最后.
        }
    }

    // 交换堆中索引为i和j的两个元素
    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 优化的shiftDown过程, 使用赋值的方式取代不断的swap,
    // 该优化思想和我们之前对插入排序进行优化的思路是一致的.
    // while 循环条件 左孩子要大于总长度,
    // 如果判断右孩子,就算右孩子不在,但是左孩子无法判断是否在,而且右孩子肯定是小于等于总长度.
    private static void shiftDown(Comparable[] arr, int n, int k) {
        Comparable e = arr[k];
        while (2 * k + 1 < n) {
            int j = 2 * k + 1;
            if (j + 1 < n && arr[j + 1].compareTo(arr[j]) > 0) {
                j += 1;
            }
            if (arr[k].compareTo(arr[j]) > 0) break;
            arr[j] = arr[k];// 类似于插入排序优化,直接将上个复制到当前
            k = j;// 然后下个位置为当前
        }
        arr[k] = e;
    }

    // 测试 HeapSort
    public static void main(String[] args) {
//        int N = 1000000;
//        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        Integer[] arr1 = {3, 2, 4, 5, 7, 89, 2, 455345, 653, 345423, 234, 234, 645, 457, 457};
        SortTestHelper.testSort("Unit04Heap.HeapSort", arr1);
        for (Integer integer : arr1) {
            System.out.println(integer);
        }
    }
}
