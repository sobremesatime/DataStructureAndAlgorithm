package algorithm.dynamic_programming;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 */
public class LongestPalindromicSubstring {
    //动态规划
    public String longestPalindrome(String s){
        int length = s.length();
        if (length < 2)
            return s;
        int maxLen = 1;
        int begin = 0;
        boolean[][] dp = new boolean[length][length];
        //初始化
        for (int i = 0; i < length; i++) {
            dp[i][i] = true;
        }
        for (int len = 2; len <= length; len++) {
            for (int left = 0; left < length; left++) {
                int right = len + left - 1;
                if (s.charAt(left) != s.charAt(right))
                    dp[left][right] = false;
                else{
                    if (right - left < 2){
                        dp[left][right] = true;
                    }else {
                        dp[left][right] = dp[left + 1][right - 1];
                    }
                }
                if (dp[left][right] && len > maxLen){
                    maxLen = len;
                    begin = left;
                }
            }

        }
        return s.substring(begin, begin + maxLen);
    }

    //中心扩展算法
    public String longestPalindrome_center(String s){
        if (s == null || s.length() == 1)
            return s;
        int end = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            int len_single = expandAroundCenter(s, i, i);
            int len_double = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len_single, len_double);
            if (len > end - start + 1){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
        
    }
    public int expandAroundCenter(String s, int left, int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            --left;
            ++right;
        }
        return right - left - 1;
    }
}
