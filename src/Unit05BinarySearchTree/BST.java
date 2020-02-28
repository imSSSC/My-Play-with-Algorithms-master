package Unit05BinarySearchTree;

import java.util.LinkedList;

/**
 * @auther: snake
 * @date: 2020/2/28 20:48
 */
// 二分搜索树
// 由于key需要能够进行比较,索引需要extends Comparable<Key>
public class BST<Key extends Comparable<Key>, Value> {

    // 树中的节点为私有的类,外界不需要了解二分搜索树节点的具体实现
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }

        public Node(Node node) {
            this.key = node.key;
            this.value = node.value;
            this.left = node.left;
            this.right = node.right;
        }
    }


    private Node root; // 根节点
    private int count; // 树中的节点个数

    // 构造函数,没人构造一颗空二分搜索树
    public BST() {
        root = null;
        count = 0;
    }

    // 返回二分搜索树的节点个数
    public int size() {
        return count;
    }

    // 返回二分搜索树是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 向二分搜索树中插入一个新的(key,value)树据对
    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    // 查看二分搜索树中是否存在键key
    public boolean contain(Key key) {
        return contain(root, key);
    }

    // 在二分搜索树中搜索键key所对应的值。如果这个值不存在, 则返回null
    public Value search(Key key) {
        return search(root, key);
    }

    // 二分搜索树的前序遍历(深度优先遍历)
    public void preOrder() {
        preOrder(root);
    }

    // 二分搜索树的中序遍历(深度优先遍历)
    public void inOrder() {
        inOrder(root);
    }

    // 二分搜索树的后序遍历(深度优先遍历)
    public void postOrder() {
        postOrder(root);
    }

    // 二分搜索树的层序遍历(广度优先遍历)
    public void levelOrder() {

        // 我们使用LinkedList来作为我们的队列
        LinkedList<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {

            Node node = q.poll();
            System.out.println(node.key);

            if (node.left != null) {
                q.offer(node.left);
            }
            if (node.right != null) {
                q.offer(node.right);
            }
        }
    }

    // 寻找二分搜索树的最小的键值
    public Key minimum() {
        assert count != 0;
        Node minNode = minimum(root);
        return minNode.key;
    }

    // 寻找二分搜索树的最大的键值
    public Key maximum() {
        assert count != 0;
        Node maxNode = maximum(root);
        return maxNode.key;
    }

    // 从二分搜索树中删除最小值所在的节点
    public void removeMin() {
        if (root != null)
            root = removeMin(root);
    }

    // 从二分搜索树中删除最大值所在节点
    public void removeMax() {
        if (root != null)
            root = removeMax(root);
    }


    // 从二分搜索树种删除键值为key的节点
    public void remove(Key key) {
        root = remove(root, key);
    }

    private Node remove(Node node, Key key) {

        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { // key == node.key

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;// 相当于把自己删除,然后返回右子树
                count--;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                count--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点,即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = new Node(minimum(node.right));
            count++;

            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null; // 相当于把自己删除了
            count--;

            return successor;
        }
    }

    // 删除掉以node为根的二分搜索树中的最大节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            count--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {

        if (node.left == null) {

            Node rightNode = node.right;
            node.right = null;
            count--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 返回以node为根的二分搜索树的最大键值所在的节点
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node);
    }

    // 返回以node为根的二分搜索树的最小键值所在的节点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    private void postOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            inOrder(root.right);
            System.out.println(root.key);
        }
    }

    // 对以node为根的二叉搜索树进行中序遍历, 递归算法
    private void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.key);
            inOrder(root.right);
        }
    }

    // 对以node为根的二叉搜索树进行前序遍历, 递归算法
    private void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.key);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // 在以node为根的二分搜索树中查找key所对应的value, 递归算法
    // 若value不存在, 则返回NULL
    private Value search(Node node, Key key) {
        if (node == null)
            return null;

        if (node.key.compareTo(key) == 0)
            return node.value;
        else if (node.key.compareTo(key) < 0)
            return search(node.left, key);
        else // key > node->key
            return search(node.right, key);
    }

    // 查看以node为根的二分搜索树中是否包含键值为key的节点, 使用递归算法
    private boolean contain(Node node, Key key) {
        if (node == null) {
            return false;
        }
        if (node.key.compareTo(key) == 0)
            return true;
        else if (node.key.compareTo(key) < 0)
            return contain(node.left, key);
        else
            return contain(node.right.key);
    }

    // 向为node为根的二分搜索树中,插入节点(key,value),使用递归算法
    // 返回插入新节点后的二分搜索树的根
    private Node insert(Node node, Key key, Value value) {

        if (node == null) {
            count++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) == 0)
            node.value = value;
        else if (key.compareTo(node.key) < 0)
            node.left = insert(node.left, key, value);
        else
            node.right = insert(node.right, key, value);

        return node;
    }

    // 测试二分搜索树
    public static void main(String[] args) {

        int N = 1000000;

        // 创建一个数组，包含[0...N)的所有元素
        Integer[] arr = new Integer[N];
        for (int i = 0; i < N; i++)
            arr[i] = i;

        // 打乱数组顺序
        for (int i = 0; i < N; i++) {
            int pos = (int) (Math.random() * (i + 1));
            Integer t = arr[pos];
            arr[pos] = arr[i];
            arr[i] = arr[pos];
        }
        // 由于我们实现的二分搜索树不是平衡二叉树，
        // 所以如果按照顺序插入一组数据，我们的二分搜索树会退化成为一个链表
        // 平衡二叉树的实现，我们在这个课程中没有涉及，
        // 有兴趣的同学可以查看资料自学诸如红黑树的实现
        // 以后有机会，我会在别的课程里向大家介绍平衡二叉树的实现的：）


        // 我们测试用的的二分搜索树的键类型为Integer，值类型为String
        // 键值的对应关系为每个整型对应代表这个整型的字符串
        BST<Integer, String> bst = new BST<Integer, String>();
        for (int i = 0; i < N; i++)
            bst.insert(arr[i], Integer.toString(arr[i]));

        // 对[0...2*N)的所有整型测试在二分搜索树中查找Main.java
        // 若i在[0...N)之间，则能查找到整型所对应的字符串
        // 若i在[N...2*N)之间，则结果为null
        for (int i = 0; i < 2 * N; i++) {
            String res = bst.search(i);
            if (i < N)
                assert res.equals(Integer.toString(i));
            else
                assert res == null;
        }
    }
}
