package algorithm.recursion_backtrack.classicbacktracking;

import org.junit.Test;

import java.util.*;

/**
 * @Title: PermutationAndCombination
 * @Description: 排列、组合、子集相关问题
 * @author: LYL
 * @date: 2023/2/26 11:11
 */

//提示：这部分练习可以帮助我们熟悉「回溯算法」的一些概念和通用的解题思路。
// 解题的步骤是：先画图，再编码。
// 去思考可以剪枝的条件， 为什么有的时候用 used 数组，有的时候设置搜索起点 begin 变量，理解状态变量设计的想法。
public class PermutationAndCombination {

    @Test
    public void testPermutationAndCombination() {
        String s = "25525511135";
        List<String> list = restoreIpAddresses(s);
        System.out.println(list);
    }

    //全排列-给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        dfs_permute(nums, res, list, visited);
        return res;
    }

    public void dfs_permute(int[] nums, List<List<Integer>> res, LinkedList<Integer> list, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                list.add(nums[i]);
                visited[i] = true;
                dfs_permute(nums, res, list, visited);
                visited[i] = false;
                list.removeLast();
            }
        }
    }

    //全排列-给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        boolean[] visited = new boolean[nums.length];
        dfs_permuteUnique(nums, res, list, visited);
        return res;
    }

    public void dfs_permuteUnique(int[] nums, List<List<Integer>> res, LinkedList<Integer> list, boolean[] visited) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;
            list.add(nums[i]);
            visited[i] = true;
            dfs_permuteUnique(nums, res, list, visited);
            visited[i] = false;
            list.removeLast();
        }
    }

    //组合总和-给你一个 无重复元素 的整数数组candidates 和一个目标整数target，
    // 找出candidates中可以使数字和为目标数target的所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        if (candidates.length == 0)
            return res;
        Arrays.sort(candidates);
        dfs_combinationSum(candidates, target, res, list, 0);
        return res;
    }

    public void dfs_combinationSum(int[] candidates, int target, List<List<Integer>> res, LinkedList<Integer> list, int begin) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            // 重点理解这里剪枝，前提是候选数组已经有序
            if (target - candidates[i] < 0) {
                break;
            }
            list.add(candidates[i]);
            dfs_combinationSum(candidates, target - candidates[i], res, list, i);
            list.removeLast();
        }
    }

    //组合总和 II-给定一个候选人编号的集合candidates和一个目标数target，
    // 找出candidates中所有可以使数字和为target的组合。
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        if (candidates.length == 0)
            return res;
        Arrays.sort(candidates);
        dfs_combinationSum2(candidates, target, 0, res, list);
        return res;
    }

    public void dfs_combinationSum2(int[] candidates, int target, int begin, List<List<Integer>> res, LinkedList<Integer> list) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i < candidates.length; i++) {
            if (target - candidates[i] < 0)
                break;
            if (i > begin && candidates[i] == candidates[i - 1])
                continue;
            list.add(candidates[i]);
            dfs_combinationSum2(candidates, target - candidates[i], i + 1, res, list);
            list.removeLast();
        }
    }

    //组合-给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        dfs_combine(n, k, 1, res, list);
        return res;
    }

    public void dfs_combine(int n, int k, int begin, List<List<Integer>> res, LinkedList<Integer> list) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = begin; i <= n; i++) {
            list.add(i);
            dfs_combine(n, k, i + 1, res, list);
            list.removeLast();
        }
    }

    /**
     * 子集 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        res.add(Collections.EMPTY_LIST);
        dfs_subsets(nums, 0, res, list);
        return res;
    }

    public void dfs_subsets(int[] nums, int begin, List<List<Integer>> res, LinkedList<Integer> list) {
        for (int i = 0; i < nums.length; i++) {
            list.add(nums[i]);
            res.add(new ArrayList<>(list));
            dfs_subsets(nums, i + 1, res, list);
            list.removeLast();
        }
    }

    //子集 II 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
    //解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>();
        res.add(Collections.EMPTY_LIST);
        dfs_subsetsWithDup(nums, 0, res, list);
        return res;
    }

    public void dfs_subsetsWithDup(int[] nums, int begin, List<List<Integer>> res, LinkedList<Integer> list) {
        for (int i = begin; i < nums.length; i++) {
            if (i > begin && nums[i] == nums[i - 1])
                continue;
            list.add(nums[i]);
            res.add(new ArrayList<>(list));
            dfs_subsetsWithDup(nums, i + 1, res, list);
            list.removeLast();
        }
    }

    /**
     * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列按大小顺序列出所有排列情况，并一一标记，当n = 3 时, 所有排列如下：
     * <p>
     * "123"
     * "132"
     * "213"
     * "231"
     * "312"
     * "321"
     * 给定n 和k，返回第k个排列。
     *
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        StringBuilder stringBuilder = new StringBuilder();
        int[] factorial = calculateFactorial(n);
        boolean[] visited = new boolean[n + 1];
        dfs_getPermutation(n, k, 0, stringBuilder, factorial, visited);
        return stringBuilder.toString();
    }

    public void dfs_getPermutation(int n, int k, int index, StringBuilder stringBuilder, int[] factorial, boolean[] visited) {
        if (index == n)
            return;
        int count = factorial[n - 1 - index];
        for (int i = 1; i <= n; i++) {
            if (visited[i])
                continue;
            //没在以1开始的部分
            if (count < k) {
                k -= count;
                continue;
            }
            stringBuilder.append(i);
            visited[i] = true;
            dfs_getPermutation(n, k, index + 1, stringBuilder, factorial, visited);
            // 注意 1：不可以回溯（重置变量），算法设计是「一下子来到叶子结点」，没有回头的过程
            // 注意 2：这里要加 return，后面的数没有必要遍历去尝试了
            return;
        }
    }

    /**
     * 阶乘函数
     *
     * @param n
     */
    private int[] calculateFactorial(int n) {
        int[] factorial = new int[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }
        return factorial;
    }

    public List<String> restoreIpAddresses(String s) {
        int[] segments = new int[4];
        List<String> res = new ArrayList<>();
        dfs_restoreIpAddresses(s, 0, 0, res, segments);
        return res;
    }

    public void dfs_restoreIpAddresses(String s, int segIndex, int segStart, List<String> list, int[] segments) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案, segments储存4段ip
        if (segIndex == 4) {
            if (segStart == s.length()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(segments[0]);
                for (int i = 1; i < 4; i++) {
                    stringBuilder.append(".");
                    stringBuilder.append(segments[i]);
                }
                list.add(stringBuilder.toString());
            }
            //有4段ip，但是ip长度小于字符串的长度 或者符合要求
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length())
            return;
        //由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segIndex] = 0;
            dfs_restoreIpAddresses(s, segIndex + 1, segStart + 1, list, segments);
        }
        // 一般情况，枚举每一种可能性并递归
        int address = 0;
        for (int i = segStart; i < s.length(); i++) {
            address = address * 10 + (s.charAt(i) - '0');
            if (address > 0 && address <= 255) {
                segments[segIndex] = address;
                dfs_restoreIpAddresses(s, segIndex + 1, i + 1, list, segments);
            } else {
                break;
            }
        }
    }
}
