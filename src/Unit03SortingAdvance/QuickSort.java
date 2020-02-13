package Unit03SortingAdvance;

// 快排
public class QuickSort {

    public static void sort(Comparable[] arr) {

        int n = arr.length;
        sort(arr, 0, n - 1);
    }

    private static void sort(Comparable[] arr, int l, int r) {

        // 对于小规模数组,使用插入排序
        if (r - l < 15) {
            InsertionSort.sort(arr, l, r);
            return;
        }

        int p = partition(arr, l, r);
        sort(arr, l, p - 1);
        sort(arr, p + 1, r);
    }

    // 对arr[j,r]部分进行partition操作
    // 返回p,使得arr[l,p-1] < arr[p] < arr[p+1,r]
    private static int partition(Comparable[] arr, int l, int r) {

        // 随机在arr[l,r]的范围中,选择一个数值作为标定点pivot
        swap(arr, l, (int) (Math.random() * (r - l + 1)) + l);

        Comparable v = arr[l];

        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (arr[i].compareTo(v) < 0) {
                j++;
                swap(arr, j, i);
            }
        }
        swap(arr, l, j);

        return j;
    }

    private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    // 测试 QuickSort
    public static void main(String[] args) {

        // Quick Sort也是一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        int N = 1000000;
        Integer[] arr = SortTestHelper.generateRandomArray(N, 0, 100000);
        SortTestHelper.testSort("Unit03SortingAdvance.QuickSort", arr);

        return;
    }
}
