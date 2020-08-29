package com.run.weekend_01;

import java.util.Stack;

/**
 42. 接雨水  https://leetcode-cn.com/problems/trapping-rain-water/
 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 输出: 6
 */
public class Day20200829_42 {

    public static void main(String[] args) {
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
    }

    // 双指针
    public static int trap(int[] height) {
        int result = 0;
        int left = 0;
        int right = height.length - 1;
        int left_max = 0;
        int right_max = 0;
        while (left < right){
            if (height[left] < height[right]){
                if (height[left] >= left_max){
                    left_max = height[left];
                }else {
                    result += left_max - height[left];
                }
                left++;
            }else {
                if (height[right] >= right_max){
                    right_max = height[right];
                }else {
                    result += right_max - height[right];
                }
                right--;
            }
        }
        return result;
    }

    // 利用栈
    public static int trap_03(int[] height) {
        int result = 0, current = 0;
        Stack<Integer> st = new Stack();
        while (current < height.length) {
            // 当前小于栈顶，直接入栈
            while (!st.empty() && height[current] > height[st.peek()]) {
                // 取出栈顶，空栈跳出
                int top = st.pop();
                if (st.empty())
                    break;
                // 计算容量
                int distance = current - st.peek() - 1;
                int bounded_height = Math.min(height[current], height[st.peek()]) - height[top];
                result += distance * bounded_height;
            }
            st.push(current++);
        }
        return result;
    }

    // 通过额外空间保存左右的max
    public static int trap_02(int[] height) {
        if (height == null || height.length == 1){
            return 0;
        }
        int n = height.length;
        int result = 0;
        int[] left = new int[n];
        left[0] = height[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(height[i], left[i-1]);
        }
        int[] right = new int[n];
        right[n-1] = height[n-1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(height[i], right[i+1]);
        }
        for (int i = 1; i < n; i++) {
            result += Math.min(left[i], right[i]) - height[i];
        }
        return result;
    }

    // 暴力求解
    public static int trap_01(int[] height) {
        int result = 0;
        for (int i = 1; i < height.length -1; i++) {
            int max_left = 0;
            int max_right = 0;
            for (int j = i; j >= 0; j--) {
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                max_right = Math.max(max_right, height[j]);
            }
            result += Math.min(max_left, max_right) - height[i];
        }
        return result;
    }

}
