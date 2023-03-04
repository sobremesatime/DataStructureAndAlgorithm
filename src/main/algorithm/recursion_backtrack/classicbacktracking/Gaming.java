package algorithm.recursion_backtrack.classicbacktracking;

import java.util.*;

/**
 * @Title: Gaming
 * @Description: 游戏问题
 * @author: LYL
 * @date: 2023/2/27 20:42
 */

//回溯算法是早期简单的人工智能，有些教程把回溯叫做暴力搜索，但回溯没有那么暴力，回溯是有方向地搜索。
// 「力扣」上有一些简单的游戏类问题，解决它们有一定的难度，大家可以尝试一下。
public class Gaming {
    /**
     * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
     * n皇后问题 研究的是如何将 n个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的n皇后问题 的解决方案。
     * 每一种解法包含一个不同的n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     *
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        int[] pos = new int[n];
        recursion(pos, 0, n, new LinkedList<>(), res);
        return res;
    }

    public void recursion(int[] pos, int row, int n, LinkedList<String> list, List<List<String>> res) {
        if (row == n) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (isValid(pos, row, i)) {
                pos[row] = i;
                list.add(getStrings(n, i));
                recursion(pos, row + 1, n, list, res);
                list.removeLast();
            }
        }
    }

    public boolean isValid(int[] pos, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (row == i || col == pos[i] || Math.abs(row - i) == Math.abs(col - pos[i])) {
                return false;
            }
        }
        return true;
    }

    public String getStrings(int n, int position) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i == position) {
                stringBuilder.append("Q");
            } else {
                stringBuilder.append(".");
            }
        }
        return stringBuilder.toString();
    }


    //编写一个程序，通过填充空格来解决数独问题。
    boolean valid = false;
    List<int[]> spaces = new ArrayList<int[]>();
    int[] line = new int[9];
    int[] column = new int[9];
    int[][] block = new int[3][3];
    public void solveSudoku(char[][] board) {

        //对行，列，块数组初始化
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    //移动digit - 1位
                    int digit = board[i][j] - '0' - 1;
                    flip(i, j, digit);
                }
            }
        }

        while (true) {
            boolean modified = false;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] == '.'){
                        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
                        if ((mask & (mask - 1)) == 0) {
                            int digit = Integer.bitCount(mask - 1);
                            flip(i, j, digit);
                            board[i][j] = (char) (digit + '0' + 1);
                            modified = true;
                        }
                    }
                }
            }
            if (!modified) {
                break;
            }
        }
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                }
            }
        }
        dfs_solveSudoku(board, 0);

    }

    public void dfs_solveSudoku(char[][] board, int pos) {
        if (pos == spaces.size()){
            valid = true;
            return;
        }

        int[] space= spaces.get(pos);
        int i = space[0];
        int j = space[1];
        //将第i，j这个位置可以使用的数字标记出来
        int mask = ~(line[i] | column[j] | block[i / 3][j / 3]) & 0x1ff;
        //mask &= mask - 1从低位开始枚举
        for (; mask != 0 && !valid; mask &= mask - 1){
            //得到最低位的1
            int digitMask = mask & (-mask);
            //最低位的1转成数字
            int digit = Integer.bitCount(digitMask - 1);
            //更新标记数组，数独图
            flip(i, j, digit);
            board[i][j] = (char) (digit + '0' + 1);
            dfs_solveSudoku(board, pos + 1);
            //异或还原
            flip(i, j, digit);
        }
    }

    //表示数字digit在第i行，j列，x块已确定
    public void flip(int i, int j, int digit) {
        //数字digit则第digit位为1
        line[i] ^= (1 << digit);
        column[j] ^= (1 << digit);
        block[i / 3][j / 3] ^= (1 << digit);
    }

    //在这个祖玛游戏变体中，桌面上有 一排 彩球，每个球的颜色可能是：
    // 红色 'R'、黄色 'Y'、蓝色 'B'、绿色 'G' 或白色 'W' 。你的手中也有一些彩球。
    //你的目标是 清空 桌面上所有的球。每一回合：
    //从你手上的彩球中选出 任意一颗 ，然后将其插入桌面上那一排球中：两球之间或这一排球的任一端。
    //接着，如果有出现 三个或者三个以上 且 颜色相同 的球相连的话，就把它们移除掉。
    //如果这种移除操作同样导致出现三个或者三个以上且颜色相同的球相连，则可以继续移除这些球，直到不再满足移除条件。
    //如果桌面上所有球都被移除，则认为你赢得本场游戏。
    //重复这个过程，直到你赢了游戏或者手中没有更多的球。
    //给你一个字符串 board ，表示桌面上最开始的那排球。另给你一个字符串 hand ，表示手里的彩球。请你按上述操作步骤移除掉桌上所有球，计算并返回所需的 最少 球数。如果不能移除桌上所有的球，返回 -1 。

    public int findMinStep(String board, String hand) {
        //1<<hand.length：对手里的球进行状态压缩
        int res = dfs_findMinStep(board, hand, new HashMap<>(), 1 << hand.length());
        return res == 0x3F3F3F3F ?  -1 : res;
    }
    public int dfs_findMinStep(String board, String hand, Map<String, Integer> hashMap, int cur){
        if (board.length() == 0)
            return 0;
        if (hashMap.containsKey(board))
            return hashMap.get(board);
        int res = 0x3F3F3F3F;
        for (int i = 0; i < hand.length(); i++) {
            //如果当前球已经用过，则不再使用
            if (((cur >> i) & 1) == 1)
                continue;
            //当前球没有被用过，使用当前球发射，状态压缩标记为已经使用
            int next = (1 << i) | cur;
            //枚举所有插入位置
            for (int j = 0; j <= board.length(); j++) {
                //剪枝，对于RRWW来说，手中的球如果是R，插入第一个位置和第二个位置的情况是一样的
                if (j > 0 && j < board.length() - 1 && board.charAt(j) == board.charAt(j - 1)) continue;
                //剪枝，如果选出的球的颜色和插入的球的颜色不相同，没必要进行下去
                if (j > 0 && j < board.length() - 1 && board.charAt(j) != hand.charAt(i)) continue;
                //curBoard记录插入当前球后的情况
                StringBuilder curBoard = new StringBuilder();
                curBoard.append(board, 0, j).append(hand.charAt(i));
                if (j != board.length()) curBoard.append(board.substring(j));
                //双指针进行消除相同颜色的球，StringBuilder是引用传递，不需要使用返回结果进行更新
                eliminateSameColor(curBoard, j);
                res = Math.min(res, dfs_findMinStep(curBoard.toString(), hand, hashMap, next) + 1);
            }
        }
        hashMap.put(board, res);
        return res;
    }
    public void eliminateSameColor(StringBuilder stringBuilder, int i){
        while (i >=0 && i < stringBuilder.length()){
            int left = i, right = i;
            char c = stringBuilder.charAt(i);
            while (left >= 0 && c == stringBuilder.charAt(left))
                left--;
            while (right < stringBuilder.length() && c == stringBuilder.charAt(right))
                right++;
            if (right - left > 3){
                stringBuilder.delete(left + 1, right);
                i = left >= 0 ? left : right;
            }else
                break;
        }
    }
}
