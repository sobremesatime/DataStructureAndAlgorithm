package algorithm.recursion_backtrack.classicbacktracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: StringBackTracking
 * @Description: 字符串中的回溯问题
 * @author: LYL
 * @date: 2023/2/27 17:17
 */

//提示：字符串的问题的特殊之处在于，字符串的拼接生成新对象，因此在这一类问题上没有显示「回溯」的过程，
// 但是如果使用 StringBuilder 拼接字符串就另当别论。
//在这里把它们单独作为一个题型，是希望朋友们能够注意到这个非常细节的地方。

public class StringBackTracking {
    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits.length() == 0) {
            return res;
        }
        Map<Character, String> hashMap = new HashMap<>();
        hashMap.put('2', "abc");
        hashMap.put('3', "def");
        hashMap.put('4', "ghi");
        hashMap.put('5', "jkl");
        hashMap.put('6', "mno");
        hashMap.put('7', "pqrs");
        hashMap.put('8', "tuv");
        hashMap.put('9', "wxyz");
        dfs_letterCombinations(digits, res, hashMap, new StringBuilder(), 0);
        return res;
    }
    public void dfs_letterCombinations(String digits, List<String> list, Map<Character, String> hashMap, StringBuilder stringBuilder, int index){
        if (index == digits.length()){
            list.add(stringBuilder.toString());
            return;
        }
        char digit = digits.charAt(index);
        String letters = hashMap.get(digit);
        for (int i = 0; i < letters.length(); i++) {
            stringBuilder.append(letters.charAt(i));
            dfs_letterCombinations(digits, list, hashMap, stringBuilder,index + 1);
            stringBuilder.deleteCharAt(index);
        }
    }

    /**
     * 给定一个字符串 s ，通过将字符串 s 中的每个字母转变大小写，我们可以获得一个新的字符串。
     * 返回 所有可能得到的字符串集合 。以 任意顺序 返回输
     * @param s
     * @return
     */
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        dfs_letterCasePermutation(s, 0, new StringBuilder(), res);
        return res;
    }
    public void dfs_letterCasePermutation(String s, int index, StringBuilder stringBuilder, List<String> list){
        if (index == s.length()){
            list.add(stringBuilder.toString());
            return;
        }
        char ch = s.charAt(index);
        if (ch >= '0' && ch <= '9') {
            stringBuilder.append(ch);
            dfs_letterCasePermutation(s, index + 1, stringBuilder, list);
            return;
        }
        stringBuilder.append(Character.toLowerCase(ch));
        dfs_letterCasePermutation(s, index + 1, stringBuilder, list);
        stringBuilder.delete(index,s.length());
        stringBuilder.append(Character.toUpperCase(ch));
        dfs_letterCasePermutation(s, index + 1, stringBuilder, list);
        stringBuilder.delete(index, s.length());
    }

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        dfs_generateParenthesis(n, list, new StringBuilder(), 0, 0);
        return list;
    }

    public void dfs_generateParenthesis(int n, List<String> list, StringBuilder stringBuilder, int left, int right){
        if (stringBuilder.length() == 2 * n){
            list.add(stringBuilder.toString());
            return;
        }
        if (left < n){
            stringBuilder.append('(');
            dfs_generateParenthesis(n, list, stringBuilder, left + 1, right);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        if (right < left){
            stringBuilder.append(')');
            dfs_generateParenthesis(n, list, stringBuilder, left, right + 1);
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}
