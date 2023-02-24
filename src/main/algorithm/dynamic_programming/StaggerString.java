package algorithm.dynamic_programming;

//给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
//        两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
//        s = s1 + s2 + ... + sn
//        t = t1 + t2 + ... + tm
//        |n - m| <= 1
//        交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
//        注意：a + b 意味着字符串 a 和 b 连接。


import org.junit.Test;



public class StaggerString {
    @Test
    public void test() {
    }
    public boolean isInterleave(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();
        if (n + m != t)
            return false;

        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = i + j - 1;
                if (i > 0)
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                if (j > 0)
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
            }
        }
        return dp[n][m];
    }

    public boolean isInterleave_opt(String s1, String s2, String s3){
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t)
            return false;
        boolean[] dp = new boolean[m + 1];
        dp[0] = true;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int p = n + m - 1;
                if (i > 0)
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                if (j > 0)
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
            }
        }
        return dp[m];
    }

}
