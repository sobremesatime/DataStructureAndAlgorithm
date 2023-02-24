package datastructure.mytree;

import org.junit.Test;

import java.util.*;

public class TreeTraverse {
    //先序遍历
    @Test
    public void testTrrrTraverse(){

    }
    public void preOrderTraverse(TreeNode root){
        if (root != null){
            System.out.print(root.val);
            preOrderTraverse(root.left);
            preOrderTraverse(root.right);
        }
    }

    public void preOrder(TreeNode root){
        if (root == null)
            return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode t = stack.pop();
            System.out.print(t.val);
            if (t.right != null)
                stack.push(t.right);
            if (t.left != null)
                stack.push(t.left);
        }
    }

    //中序遍历
    public void inOrderTraverse(TreeNode root){
        if (root != null){
            inOrderTraverse(root.left);
            System.out.print(root.val);
            inOrderTraverse(root.right);
        }
    }
    public void inOrder(TreeNode root){
        if (root == null)
            return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()){
            if (root != null){
                stack.push(root);
                root = root.left;
            }else{
                root = stack.poll();
                System.out.print(root.val);
                root = root.right;
            }
        }
    }

    //后序遍历
    public void postOrderTraverse(TreeNode root){
        if (root != null){
            postOrderTraverse(root.left);
            postOrderTraverse(root.right);
            System.out.print(root.val);
        }
    }
    public void postOrder(TreeNode root){
        if (root == null)
            return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode preNode = null;
        while (root != null && !stack.isEmpty()){
            while (root != null){
                stack.push(root.left);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == preNode){
                System.out.print(root.val);
                preNode = root;
            }else {
                stack.push(root);
                root = root.right;
            }
        }
    }

    //层序遍历
    public void levelOrderTraverse(TreeNode root){
        if (root == null)
            return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int treeLength = queue.size();
            for (int i = 0; i < treeLength; i++){
                TreeNode t = queue.poll();
                System.out.print(t.val + " ");
                if (t.left != null)
                    queue.add(t.left);
                if (t.right != null)
                    queue.add(t.right);
            }
        }
    }
}
