package algorithm.recursion_backtrack;



import datastructure.mytree.TreeNode;

import java.util.ArrayList;
import java.util.List;

//给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
public class SearchTree2 {

    public List<TreeNode> generateTrees(int n) {
        if (n == 0)
            return new ArrayList<TreeNode>();
        return backTrack(1, n);
    }

    public List<TreeNode> backTrack(int start, int end){
        List<TreeNode> allTrees = new ArrayList<>();
        if (start > end){
            allTrees.add(null);
            return  allTrees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = backTrack(start, i - 1);
            List<TreeNode> rightTrees = backTrack(i + 1, end);

            for (TreeNode left : leftTrees){
                for (TreeNode right : rightTrees){
                    TreeNode curTree = new TreeNode(i);
                    curTree.left = left;
                    curTree.right = right;
                    allTrees.add(curTree);
                }
            }
        }
        return allTrees;
    }
}
