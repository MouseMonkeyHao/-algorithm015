package com.run.week_03;

import javafx.util.Pair;

import java.util.*;

/**
 * 127. 单词接龙 https://leetcode-cn.com/problems/word-ladder/description/
 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：

 每次转换只能改变一个字母。
 转换过程中的中间单词必须是字典中的单词。

 说明:
 如果不存在这样的转换序列，返回 0。
 所有单词具有相同的长度。
 所有单词只由小写字母组成。
 字典中不存在重复的单词。
 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。

 输入: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 输出: 5
 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog", 返回它的长度 5。

 输入: beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log"]
 输出: 0
 解释: endWord "cog" 不在字典中，所以无法进行转换。
 */
public class Day20200915_127 {

    public static void main(String[] args) {
        System.out.println(ladderLength("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log","cog"})));
//        System.out.println(ladderLength("hit", "cog", Arrays.asList(new String[]{"hot","dot","dog","lot","log"})));
    }

//    对给定的 wordList 做预处理，找出所有的通用状态。将通用状态记录在字典中，键是通用状态，值是所有具有通用状态的单词。
//    将包含 beginWord 和 1 的元组放入队列中，1 代表节点的层次。我们需要返回 endWord 的层次也就是从 beginWord 出发的最短距离。
//    为了防止出现环，使用访问数组记录。
//    当队列中有元素的时候，取出第一个元素，记为 current_word。
//    找到 current_word 的所有通用状态，并检查这些通用状态是否存在其它单词的映射，这一步通过检查 all_combo_dict 来实现。
//    从 all_combo_dict 获得的所有单词，都和 current_word 共有一个通用状态，所以都和 current_word 相连，因此将他们加入到队列中。
//    对于新获得的所有单词，向队列中加入元素 (word, level + 1) 其中 level 是 current_word 的层次。
//    最终当你到达期望的单词，对应的层次就是最短变换序列的长度。
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int length = beginWord.length();
        Map<String, List<String>> allComboDict = new HashMap<>();
        wordList.forEach(
            word -> {
                for (int i = 0; i < length; i++) {
                    String newWord = word.substring(0, i) + '*' + word.substring(i + 1, length);
                    List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                    transformations.add(word);
                    allComboDict.put(newWord, transformations);
                }
            }
        );
        // Queue for BFS
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);

        while (!queue.isEmpty()){
            Pair<String, Integer> node = queue.remove();
            String word = node.getKey();
            Integer level = node.getValue();
            for (int i = 0; i < length; i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, length);
                for (String s : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                    if (s.equals(endWord)){
                        return level + 1;
                    }
                    if (!visited.containsKey(s)){
                        visited.put(s, true);
                        queue.add(new Pair<>(s, level + 1));
                    }
                }
            }
        }
        return 0;
    }



    // 深度优先，有点强制感觉，超时
    static List<List<String>> ans = new ArrayList<>();

    public static int ladderLength_01(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)){
            return 0;
        }
        List<String> list = new ArrayList<>();
        dfs(beginWord, endWord, wordList, list, new HashSet<>());
        int min = 0;
        for (List<String> an : ans) {
            if (min == 0){
                min = an.size() + 2;
            }else {
                min = Math.min(min, an.size() + 2);
            }
        }
        return min;
    }

    private static void dfs(String beginWord, String endWord, List<String> wordList, List<String> list, Set<String> views) {
        if (strCheck(beginWord, endWord)){
            ans.add(new ArrayList<>(list));
            return;
        }
        if (views.size() == wordList.size()){
            return;
        }
        for (int i = 0; i < wordList.size(); i++) {
            String s = wordList.get(i);
            if (views.contains(s)){
                continue;
            }
            if (strCheck(beginWord, s)){
                list.add(s);
                views.add(s);
                dfs(s, endWord, wordList, list, views);
                list.remove(list.size() - 1);
                views.remove(s);
            }
        }
    }

    private static boolean strCheck(String a, String b){
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)){
                count++;
            }
            if (count > 1){
                return false;
            }
        }
        if (count == 1){
            return true;
        }
        return false;
    }

}
