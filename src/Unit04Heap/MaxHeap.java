package Unit04Heap;

import java.util.Comparator;

/**
 * @auther: snake
 * @date: 2020/2/23 19:07
 */
// 最大堆: 使用数组实现,0索引不存元素
// parent: k(索引值)  leftChild : 2k, rightChild: 2k+1
// leftChild or rightChild: k, parent: k/2
// count 和 capacity 最大为 data.length -1;
public class MaxHeap<Item extends Comparable> {

    private Item[] data;
    private int count;// 当前大小,也是元素的位置.
    private int capacity;

    // 构造函数,构造一个空堆,可容纳capacity个元素
    public MaxHeap(int capacity) {
        data = (Item[]) new Comparable[capacity + 1];
        count = 0;
        this.capacity = capacity;
    }

    // 构造函数,通过一个给定数组创建一个最大堆
    // 构造堆的过程,时间复杂度为O(n)
    public MaxHeap(Item arr[]) {
        int n = arr.length;

        data = (Item[]) new Comparable[n + 1];
        capacity = n;
        count = n;

        for (int i = 0; i < n; i++) {
            data[i + 1] = arr[i];
        }

        for (int i = count / 2; i >= 1; i--) {
            shiftDown(i);
        }

    }

    // 返回堆中的元素个数
    public int size() {
        return count;
    }

    // 返回一个布尔值,表示堆中是否有元素
    public boolean isEmpty() {
        return count == 0;
    }

    // 向最大堆中插入一个新的元素 item
    public void insert(Item item) {

        assert count + 1 <= capacity; // 新元素的位置为 count+1索引,所以不能大于capacity;
        data[++count] = item;// 数组0索引不存东西.
        shiftUp(count);
    }

    // 从最大堆中取出堆顶元素,即堆中所存储的最大数据
    public Item extractMax() {
        assert count > 0;
        Item res = data[1];

        swap(1, count);
        count--;
        shiftDown(1);

        return res;
    }

    // 获取最大堆中的堆顶元素
    public Item getMax() {
        assert count > 0;
        return data[1];
    }

    // 交换堆中索引为i和j的两个元素
    private void swap(int i, int j) {
        Item t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    // 最大堆核心辅助函数
    // 从下往上移
    private void shiftUp(int k) {
        while (k > 1 && data[k / 2].compareTo(data[k]) < 0) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    // 从上往下,移动位置
    private void shiftDown(int k) {

        while (2 * k <= count) {
            int j = 2 * k;
            if (j + 1 <= count && data[j + 1].compareTo(data[j]) > 0) {
                j++;
            }
            // 在此循环中,data[k]和data[j]比较大小,是否交换位置
            if (data[k].compareTo(data[j]) > 0) break;
            swap(k, j);
            k = j;
        }
    }

    // 测试 MaxHeap
    public static void main(String[] args) {

        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(100);
        int N = 100; // 堆中元素个数
        int M = 100; // 堆中元素取值范围[0, M)
        for (int i = 0; i < N; i++)
            maxHeap.insert(new Integer((int) (Math.random() * M)));

        Integer[] arr = new Integer[N];
        // 将maxheap中的数据逐渐使用extractMax取出来
        // 取出来的顺序应该是按照从大到小的顺序取出来的
        for (int i = 0; i < N; i++) {
            arr[i] = maxHeap.extractMax();
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // 确保arr数组是从大到小排列的
        for (int i = 1; i < N; i++)
            assert arr[i - 1] >= arr[i];
    }
}
