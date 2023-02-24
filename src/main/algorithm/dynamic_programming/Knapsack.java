package algorithm.dynamic_programming;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Knapsack {
    @Test
    public void test() {
        Scanner scanner = new Scanner(System.in);
        //N为物品数量，V为背包总容量， w[i]为物品质量, v[i]为物品价值, c[i]为物品的数量
        int N = scanner.nextInt();
        int V = scanner.nextInt();
        int[] w = new int[N + 1];
        int[] v = new int[N + 1];
        int[] c = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            w[i] = scanner.nextInt();
            v[i] = scanner.nextInt();
            c[i] = scanner.nextInt();
        }
    }

    //01背包问题二维
    public int maxValue(int N, int V, int[] w, int[] v) {
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                if (j < w[i])
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i]] + v[i]);
            }
        }
        return dp[N][V];
    }

    //01背包问题一维
    public int maxValue_opt(int N, int V, int[] w, int[] v) {
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= w[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }
        return dp[V];
    }

    //01背包问题恰好装满的情况的最大值
    public int maxValueFull(int N, int V, int[] w, int[] v){
        int[] dp = new int[V + 1];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= w[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
            }
        }
        if (dp[V] < 0)
            dp[V] = 0;
        return dp[V];
    }

    //01背包问题回溯
    public List<Integer> flashBack(int[][] dp, int N, int V, int[] w){
        int i = N, j = V;
        List<Integer> list = new ArrayList<>();
        while (i != 0){
            if (dp[i - 1][j] == dp[i][j]){
                i--;
            }else {
                list.add(i);
                j = j - w[i];
                i--;
            }
        }
        return list;
    }

    //多重背包问题
    public int maxValueMultiple(int N, int V, int[] w, int[] v, int[] c){
        //当前容量下背包的最大值
        int max_int = 1000;
        //行拓展之后的每个”物品“实际质量，和实际价值
        int[] rw = new int[max_int + 1];
        int[] rv = new int[max_int + 1];

        int k = 0;
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= c[i]; j <<= 1){
                k++;
                rw[k] = w[i] * j;
                rv[k] = v[i] * j;
                c[i] -= j;
            }
            if (c[i] != 0){
                k++;
                rw[k] = w[i] * c[i];
                rv[k] = v[i] * c[i];
            }
        }
        //行拓展以后调用01背包
        N = k;
        int res = maxValue_opt(N, V, rw, rv);
        return res;
    }

    //完全背包问题二维
    public int maxValueComplete(int N, int V, int[] w, int[] v){
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= w[i]; j--){
                if (w[i] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - w[i]] + v[i]);
            }
        }
        return dp[N][V];
    }

    //完全背包问题一维
    public int maxValueComplete_opt(int N, int V, int[] w, int[] v){
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++){
            for (int j = 1; j <= w[i]; j++){
                dp[j] = Math.max(dp[j - w[i]] + v[i], dp[j]);
            }
        }
        return dp[V];
    }

}
