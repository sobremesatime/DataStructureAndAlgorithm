package algorithm.recursion_backtrack;



import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
//给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。

public class SearchTree {
    Map<Integer, Integer> map = new HashMap<>();
    @Test
    public void test_recursion(){
        int n = 3;
        int res = numTrees_recursion(3);
        System.out.println(res);
    }

    @Test
    public void test_dynamicpro(){
        int n = 3;
        int res = numTrees_dynamicpro(3);
        System.out.println(res);
    }

    public int numTrees_recursion(int n) {
        if (n == 0 || n == 1)
            return 1;
        if (map.containsKey(n)){
            return map.get(n);
        }
        int count = 0;
        for (int i = 1; i <= n; i++){
            int leftNum = numTrees_recursion(i - 1);
            int rightNum = numTrees_recursion(n - i);
            count += leftNum * rightNum;
        }
        map.put(n, count);
        return count;
    }

    public int numTrees_dynamicpro(int n){
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i]  = dp[j - 1] + dp[i - j];
            }
        }
        return  dp[n];
    }
}
