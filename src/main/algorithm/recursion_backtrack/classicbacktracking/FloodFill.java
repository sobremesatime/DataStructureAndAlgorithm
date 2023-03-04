package algorithm.recursion_backtrack.classicbacktracking;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Title: FloodFill
 * @Description: 泛洪填充
 * @author: LYL
 * @date: 2023/2/26 22:51
 */
//提示：Flood 是「洪水」的意思，Flood Fill 直译是「泛洪填充」的意思，体现了洪水能够从一点开始，迅速填满当前位置附近的地势低的区域。
// 类似的应用还有：PS 软件中的「点一下把这一片区域的颜色都替换掉」，扫雷游戏「点一下打开一大片没有雷的区域」。

public class FloodFill {
    @Test
    public void testFloodFill() {
        char[][] board = {{'A', 'B', 'C', 'E'},  {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCB";
        boolean res = exist(board, word);
        System.out.println(res);
    }

    /**
     * 有一幅以m x n的二维整数数组表示的图画image，其中image[i][j]表示该图画的像素值大小。
     * 你也被给予三个整数 sr , sc 和 newColor 。你应该从像素image[sr][sc]开始对图像进行 上色填充 。
     * 为了完成 上色工作 ，从初始像素开始，记录初始坐标的 上下左右四个方向上 像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应 四个方向上 像素值与初始坐标相同的相连像素点，……，
     * 重复该过程。将所有有记录的像素点的颜色值改为newColor。
     *
     * @param image
     * @param sr
     * @param sc
     * @param color
     * @return
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int currColor = image[sr][sc];
        if (currColor != color) {
            dfs_floodFill(image, sr, sc, currColor, color);
        }
        return image;
    }

    public void dfs_floodFill(int[][] image, int sr, int sc, int curColor, int newColor) {
        if (image[sr][sc] != curColor)
            return;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        image[sr][sc] = newColor;
        for (int i = 0; i < 4; i++) {
            int x = sr + dx[i];
            int y = sc + dy[i];
            if (x >= 0 && x < image.length && y >= 0 && y < image[0].length) {
                dfs_floodFill(image, x, y, curColor, newColor);
            }
        }
    }

    public int[][] floodFill_BFS(int[][] image, int sr, int sc, int color) {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};
        int curColor = image[sr][sc];
        if (curColor == color)
            return image;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = color;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = cell[0] + dx[i];
                int y = cell[1] + dy[i];
                if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == curColor) {
                    queue.offer(new int[]{x, y});
                    image[x][y] = color;
                }
            }
        }
        return image;
    }


    /**
     * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs_numIslands(grid, i, j);
                }
            }
        }
        return count;
    }

    public void dfs_numIslands(char[][] grid, int a, int b) {
        if (grid[a][b] == '1') {
            int[] dx = new int[]{1, 0, -1, 0};
            int[] dy = new int[]{0, -1, 0, 1};
            grid[a][b] = '0';
            for (int i = 0; i < 4; i++) {
                int x = a + dx[i];
                int y = b + dy[i];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length) {
                    dfs_numIslands(grid, x, y);
                }
            }
        }
    }

    public int numIslands_BFS(char[][] grid) {
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, -1, 0, 1};
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        int[] temp = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int x = temp[0] + dx[k];
                            int y = temp[1] + dy[k];
                            if (x >= 0 && x < n && y >= 0 && y < m && grid[x][y] == '1') {
                                queue.offer(new int[]{x, y});
                                grid[x][y] = '0';
                            }
                        }
                    }
                }
            }
        }
        return count;
    }



    /**
     * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
     * @param board
     */
    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        if (n == 0)
            return;
        for (int i = 0; i < n; i++) {
            dfs_solve(board, i, 0);
            dfs_solve(board, m - 1, i);
        }
        for (int i = 0; i < m; i++) {
            dfs_solve(board, 0, i);
            dfs_solve(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A'){
                    board[i][j] = 'O';
                }else if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }

    }
    public void  dfs_solve(char[][] board, int a, int b){
        if (board[a][b] == 'X' || board[a][b] == 'A')
            return;
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, -1, 0, 1};
        board[a][b] = 'A';
        for (int i = 0; i < 4; i++) {
            int x = a + dx[i];
            int y = b +dy[i];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length){
                dfs_solve(board, x, y);
            }
        }
    }

    public void solve_BFS(char[][] board) {
        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, -1, 0, 1};
        int n = board.length;
        int m = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (board[i][0] == 'O'){
                queue.offer(new int[]{i, 0});
                board[i][0] = 'A';
            }
            if (board[i][m - 1] == 'O') {
                queue.offer(new int[]{i, m - 1});
                board[i][m - 1] = 'A';
            }
        }
        for (int i = 0; i < m; i++) {
            if (board[0][i] == 'O'){
                queue.offer(new int[]{0, i});
                board[0][i] = 'A';
            }
            if (board[n - 1][i] == 'O'){
                queue.offer(new int[]{n - 1, i});
                board[n - 1][i] = 'A';
            }
        }
        while (!queue.isEmpty()){
            int[] coll = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = coll[0] + dx[i];
                int y = coll[1] + dy[i];
                if (x >= 0 && x < n && y >= 0 && y < m && board[x][y] == 'O'){
                    queue.offer(new int[]{x, y});
                    board[x][y] = 'A';
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A'){
                    board[i][j] = 'O';
                }else if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }

    /**
     * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
     * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (dfs_exist(board, word, visited, i, j, 0))
                    return true;
            }
        }
        return false;
    }
    public boolean dfs_exist(char[][] board, String word, boolean[][] visited, int row, int col, int index){
        if (board[row][col] != word.charAt(index)) {
            return false;
        } else if (index == word.length() - 1) {
            return true;
        }

        int[] dx = new int[]{1, 0, -1, 0};
        int[] dy = new int[]{0, -1, 0, 1};
        visited[row][col] = true;
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            int x = row + dx[i];
            int y = col + dy[i];
            if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !visited[x][y]){
                flag = flag || dfs_exist(board, word, visited, x, y, index + 1);
            }
        }
        visited[row][col] = false;
        return (flag == true);
    }
}
