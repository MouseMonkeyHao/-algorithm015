package com.run.weekend_02;

import java.util.ArrayList;
import java.util.List;

/**
 77. 组合  https://leetcode-cn.com/problems/combinations/
 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

 输入: n = 4, k = 2
 输出:
 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]
 */
public class Day20200913_77 {

    public static void main(String[] args) {
        System.out.println(combine(1, 1));
//        System.out.println(combine(4, 2));
    }

    private static List<List<Integer>> ans = new ArrayList<>();

    public static List<List<Integer>> combine(int n, int k) {
        dfs(n, k, 0, 1, new ArrayList<>());
        return ans;
    }

    private static void dfs(int n, int k, int level, int start, List<Integer> list) {
        if (level == k){
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int j = start; j <= n; j++) {
            list.add(j);
            dfs(n, k, level + 1, j + 1, list);
            list.remove(list.size() - 1);
        }
    }


}


