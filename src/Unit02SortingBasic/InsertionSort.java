package Unit02SortingBasic;

// 插入排序
// 两次循环,外循环n次,内循环范围[1,i],从i开始,递减
// 特点: 每次外循环之后,局部有序.
// 两种实现方式: 每次内循环方式与前元素比较大小: 1. 是否交换位置, 2: 是否改变插入的位置的索引.
// 优势: 近乎有序的数组,效率很高
// 时间复杂度: O(n^2)
public class InsertionSort {
    public static void sort(Comparable[] arr) {

        for (int i = 0; i < arr.length; i++) {

            // 写法一
//            for (int j = i; j > 0; j--) {
//                // 使用compareTo方法比较两个Comparable对象的大小
//                if (arr[j].compareTo(arr[j - 1]) < 0) {
//                    swap(arr, j, j - 1); // 交换位置
//                } else {
//                    break;
//                }
//            }

            // 写法二
//            for (int j = i; j > 0 && arr[j].compareTo(arr[j - 1]) < 0; j--) {
//                swap(arr, j, j - 1);
//            }

            // 写法三
            Comparable e = arr[i];// 寻找元素arr[i]适合插入的位置
            int j = i;// j保存元素e应该插入的位置
            for (; j > 0 && arr[j - 1].compareTo(e) > 0; j--) {
                arr[j] = arr[j - 1];// 前元素移到当前位置
            }
            arr[j] = e;
        }
    }

    private static void swap(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
