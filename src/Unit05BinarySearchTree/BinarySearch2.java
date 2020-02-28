package Unit05BinarySearchTree;

/**
 * @auther: snake
 * @date: 2020/2/24 22:57
 */
// 递归的二分查找算法
public class BinarySearch2 {

    public static int find(Comparable[] arr, Comparable target) {
        return find(arr, 0, arr.length - 1, target);
    }

    public static int find(Comparable[] arr, int l, int r, Comparable target) {
        if (l > r)
            return -1;
        int mid = l + (r - l) / 2;
        if (arr[mid].compareTo(target) == 0)
            return mid;
        else if (arr[mid].compareTo(target) > 0)
            r = mid - 1;
        else
            l = mid + 1;
        return find(arr, l, r, target);
    }
}
