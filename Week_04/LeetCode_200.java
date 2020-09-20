package com.run.week_03;

/**
 * 200.岛屿数量 https://leetcode-cn.com/problems/number-of-islands/
 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 此外，你可以假设该网格的四条边均被水包围。

 输入:
 [
 ['1','1','1','1','0'],
 ['1','1','0','1','0'],
 ['1','1','0','0','0'],
 ['0','0','0','0','0']
 ]
 输出: 1

 输入:
 [
 ['1','1','0','0','0'],
 ['1','1','0','0','0'],
 ['0','0','1','0','0'],
 ['0','0','0','1','1']
 ]
 输出: 3
 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 */
public class Day20200915_200 {

    public static void main(String[] args) {
//        System.out.println(numIslands(new char[][]{{'1','1','1','1','0'}, {'1','1','0','1','0'}, {'1','1','0','0','0'}, {'0','0','0','0','0'}}));
//        System.out.println(numIslands(new char[][]{{'1','1','0','0','0'}, {'1','1','0','0','0'}, {'0','0','1','0','0'}, {'0','0','0','1','1'}}));
        System.out.println(numIslands(new char[][]{{'1','1','1'}, {'0','1','0'}, {'1','1','1'}}));
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        if (grid .length == 0) return 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1'){
                    convert(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void convert(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0 || grid[i][j] != '1'){
            return;
        }
        if (grid[i][j] == '1'){
            grid[i][j] = '0';
            convert(grid, i + 1, j);
            convert(grid, i - 1, j);
            convert(grid, i, j + 1);
            convert(grid, i, j - 1);
        }
    }

}
