package Unit05BinarySearchTree;

/**
 * @auther: snake
 * @date: 2020/2/24 22:54
 */
// 非递归二分查找算法
public class BinarySearch {
    public static int find(Comparable[] arr, Comparable target) {

        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid].compareTo(target) == 0) {
                return mid;
            }
            if (arr[mid].compareTo(target) > 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }
}
