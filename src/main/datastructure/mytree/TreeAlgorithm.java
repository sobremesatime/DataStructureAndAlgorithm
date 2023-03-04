package datastructure.mytree;

import org.junit.Test;

import java.util.*;

/**
 * @Title: TreeAlgorithm
 * @Description: 树相关算法
 * @author: LYL
 * @date: 2023/2/23 12:51
 */
public class TreeAlgorithm {
    @Test
    public void testBiTree() {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        Integer[] arrays = new Integer[]{2, 3, 5, 6, 7, 9 ,8, 0};
        TreeNode root = buildTree_prein(preorder, inorder);
        TreeTraverse treeTraverse = new TreeTraverse();
        treeTraverse.levelOrderTraverse(root);
        System.out.println("\n" + treeDepth(root));
        System.out.println(printTree(root));
        System.out.println(nodeCount(root));
        List<Integer> list = new ArrayList<>();
        list = Arrays.asList(arrays);
        int[] res = list.stream().mapToInt(Integer::intValue).toArray();
    }

    //创建二叉树
    public void creatTree(TreeNode root) {
        Scanner scanner = new Scanner(System.in);
        int data = scanner.nextInt();
        if (data == -1) {
            root = null;
        } else {
            root = new TreeNode(data);
            creatTree(root.left);
            creatTree(root.right);
        }
    }

    //根据先序与中序遍历构建二叉树
    public TreeNode buildTree_prein(int[] preorder, int[] inorder) {
        int n = preorder.length;
        Map<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hashMap.put(inorder[i], i);
        }
        return myBuildTree(hashMap, preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuildTree(Map<Integer, Integer> map, int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right)
            return null;
        //前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = map.get(preorder[preorder_root]);
        //左子树的数目
        int size_left_subtree = inorder_root - inorder_left;
        //构建左子树
        TreeNode root = new TreeNode(preorder[preorder_root]);
        //递归构建左子树
        root.left = myBuildTree(map, preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        //构建右子树
        root.right = myBuildTree(map, preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    //二叉树的深度
    public int treeDepth(TreeNode root) {
        if (root == null)
            return 0;
        else {
            int n = treeDepth(root.left);
            int m = treeDepth(root.right);
            return n < m ? m + 1 : n + 1;
        }
    }

    //按之字形打印二叉树
    public ArrayList<ArrayList<Integer>> printTree(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        boolean flag = true;
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            flag = !flag;
            int n = queue.size();
            for (int i = 0; i < n; i++) {
                TreeNode temp = queue.poll();
                list.add(temp.val);
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
            if (flag)
                Collections.reverse(list);
            res.add(list);
        }
        return res;
    }

    //二叉树的结点
    public int nodeCount(TreeNode root) {
        if (root == null)
            return 0;
        else
            return nodeCount(root.left) + nodeCount(root.right) + 1;
    }

    //二叉树的两结点的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p1, TreeNode p2) {
        if (root == null || root == p1 || root == p2)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p1, p2);
        TreeNode right = lowestCommonAncestor(root.right, p1, p2);
        if (left == null)
            return right;
        if (right == null)
            return left;
        return root;
    }

    //验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode root, int lower, int upper){
        if (root == null)
            return true;
        if (root.val <= lower || root.val >= upper)
            return false;
        return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, upper);
    }

    //二叉搜索树的查找
    public TreeNode searchBST(TreeNode root, int key){
        if (root == null || root.val == key)
            return root;
        else if (key < root.val) {
            return searchBST(root.left, key);
        }else
            return searchBST(root.right, key);
    }

    //二叉搜索树与双向链表
    TreeNode pre = null;
    TreeNode head = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null)
            return null;
        Convert(pRootOfTree.left);
        if(pre == null){
            head = pRootOfTree;
            pre = pRootOfTree;
        }
        else{
            pre.right = pRootOfTree;
            pRootOfTree.left = pre;
            pre = pRootOfTree;
        }
        Convert(pRootOfTree.right);
        return head;
    }

    //二叉树的线索化

    //判断平衡二叉树
    public boolean isBalanced(TreeNode root){
        if (root == null)
            return true;
        else
            return Math.abs(treeDepth(root.left) - treeDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    //二叉树的右视图
    public int[] solve (int[] xianxu, int[] zhongxu) {
        //先序和中序构建二叉树
        TreeNode root = buildTree_prein(xianxu, zhongxu);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode temp = queue.poll();
                if (i == size - 1){
                    res.add(temp.val);
                }
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
