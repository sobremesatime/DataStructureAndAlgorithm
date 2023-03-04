package datastructure.mystring;

import org.junit.Test;

public class KMP {
    @Test
    public void test() {
        String s = "acabaabaabcacaabc";
        String t = "abaabc";
        int[] next = getNext(t);
        int res = index_KMP(s, t, 0, next);
        System.out.print(res);
    }

    public int index_KMP(String s, String t, int pos, int[] next) {
        //从下标为pos开始
        int i = pos, j = 0;
        while (i < s.length() && j < t.length()) {
            //如果首个字符不匹配，或者主串和模式串的字符匹配
            if (j == -1 || s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        return j == t.length() - 1 ? i - t.length() : -1;
    }

    //当匹配失效时，得到比较的位置
    public int[] getNext(String t) {
        int i = 0, j = -1;
        int[] next = new int[t.length()];
        next[0] = -1;
        while (i < t.length() - 1) {
            if (j == -1 || t.charAt(i) == t.charAt(j)) {
                next[++i] = ++j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    public int[] getNext_opt(String t) {
        int i = 0, j = -1;
        int[] next = new int[t.length()];
        next[0] = -1;
        while (i < t.length() - 1) {
            if (j == -1 || t.charAt(i) == t.charAt(j)) {
                ++i;
                ++j;
                if (t.charAt(i) != t.charAt(j))
                    next[i] = j;
                else
                    next[i] = next[j];
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
