package com.run.week_03;

import java.util.HashMap;
import java.util.Map;

/**
 * 860. 柠檬水找零  https://leetcode-cn.com/problems/lemonade-change/description/
 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 注意，一开始你手头没有任何零钱。
 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。

 输入：[5,5,5,10,20] 输出：true
 解释：
 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 由于所有客户都得到了正确的找零，所以我们输出 true。

 输入：[5,5,10] 输出：true

 输入：[10,10] 输出：false

 输入：[5,5,10,10,20] 输出：false
 解释：
 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
 对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
 由于不是每位顾客都得到了正确的找零，所以答案是 false。

 提示：
 0 <= bills.length <= 10000
 bills[i] 不是 5 就是 10 或是 20
 */
public class Day20200917_860 {

    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[]{5,5,5,10,20}));
        System.out.println(lemonadeChange(new int[]{5,5,10}));
        System.out.println(lemonadeChange(new int[]{10,10}));
        System.out.println(lemonadeChange(new int[]{5,5,10,10,20}));
    }

    public static boolean lemonadeChange(int[] bills) {
        if (bills.length == 0 || bills[0] != 5){
            return false;
        }
        int five = 0;
        int ten = 0;
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5){
                five += 1;
                continue;
            }
            if (five == 0)
                return false;
            if (bills[i] == 10){
                five -= 1;
                ten += 1;
            }else if (bills[i] == 20){
                if (ten > 0){
                    five -= 1;
                    ten -= 1;
                }else if (five >= 3){
                    five -= 3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean lemonadeChange_01(int[] bills) {
        if (bills.length == 0 || bills[0] != 5){
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < bills.length; i++) {
            Integer int5 = map.getOrDefault(5, 0);
            if (bills[i] == 5){
                map.put(5, int5 + 1);
                continue;
            }
            if (int5 == 0){
                return false;
            }
            Integer int10 = map.getOrDefault(10, 0);
            if (bills[i] == 10){
                map.put(5, int5 - 1);
                map.put(10, int10 + 1);
            }else if (bills[i] == 20){
                if (int10 > 0){
                    map.put(5, int5 - 1);
                    map.put(10, int10 - 1);
                }else if (int5 >= 3){
                    map.put(5, int5 - 3);
                }else {
                    return false;
                }
            }
        }
        return true;
    }

}
