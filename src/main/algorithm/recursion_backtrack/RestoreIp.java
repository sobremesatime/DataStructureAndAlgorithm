package algorithm.recursion_backtrack;

import java.util.ArrayList;
import java.util.List;
//    效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
//    例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，
//      但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
//    给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，
//      这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。

public class RestoreIp {
    public static final int SEG_COUNT = 4;
    List<String> res = new ArrayList<>();
    //用来存储ip地址的四个部分
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return res;
    }

    public void dfs(String s, int segIndex, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segIndex == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(segments[0]);
                for (int i = 1; i < SEG_COUNT; i++) {
                    stringBuilder.append('.');
                    stringBuilder.append(segments[i]);
                }
                res.add(stringBuilder.toString());
            }
            return;
        }
        //没分完片段就用完字符串，提前回溯
        if (segStart == s.length()){
            return;
        }

        if (s.charAt(segStart) == '0') {
            segments[segIndex] = 0;
            dfs(s, segIndex + 1, segStart + 1);
        }

        int address = 0;
        for (int i = segStart; i < s.length(); i++) {
            address = address * 10 + s.charAt(i) - '0';
            if (address > 0 && address <= 255){
                segments[segIndex] = address;
                dfs(s, segIndex + 1, i + 1);
            }else{
                break;
            }
        }
    }
}
