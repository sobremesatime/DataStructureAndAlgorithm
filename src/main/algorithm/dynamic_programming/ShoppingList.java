package algorithm.dynamic_programming;


import java.util.*;

public class ShoppingList {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int m = in.nextInt();
        int[] v = new int[m + 1];
        int[] p = new int[m + 1];
        int[] q = new int[m + 1];
        //用来表示主件与附件的对应关系
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= m; i++) {
            v[i] = in.nextInt();
            p[i] = in.nextInt();
            q[i] = in.nextInt();
            if (!map.containsKey(i)) {
                if (q[i] == 0)
                    map.put(i, null);
                else {
                    List<Integer> list = new ArrayList<>();
                    if (map.get(q[i]) != null )
                        list.add(map.get(q[i]).get(0));
                    list.add(i);
                    map.put(q[i], list);
                }
            }
        }
        int[] dp = new int[N + 1];
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int v0 = -1, v1 = -1, v2 = -1, v3 = -1;
            int p0 = 0, p1 = 0, p2 = 0, p3 = 0;
            v0 = v[entry.getKey()];
            p0 = v[entry.getKey()] * p[entry.getKey()];
            if (entry.getValue() != null && entry.getValue().size() >= 1) {
                v1 = v0 + v[entry.getValue().get(0)];
                p1 = p0 + v[entry.getValue().get(0)] * p[entry.getValue().get(0)];
            }
            if (entry.getValue() != null && entry.getValue().size() >= 2) {
                v2 = v0 + v[entry.getValue().get(1)];
                p2 = p0 + v[entry.getValue().get(1)] * p[entry.getValue().get(1)];

                v3 = v0 + v[entry.getValue().get(0)] + v[entry.getValue().get(1)];
                p3 = p0 + v[entry.getValue().get(0)] * p[entry.getValue().get(0)] + v[entry.getValue().get(1)] * p[entry.getValue().get(1)];
            }

            for (int j = N; j >= 1; j--) {
                int max = dp[j];
                if (v0 != -1 && j >= v0)
                    max = Math.max(max, dp[j - v0] + p0);
                if (v1 != -1 && j >= v1)
                    max = Math.max(max, dp[j - v1] + p1);
                if (v2 != -1 && j >= v2)
                    max = Math.max(max, dp[j - v2] + p2);
                if (v3 != -1 && j >= v3)
                    max = Math.max(max, dp[j - v3] + p3);
                dp[j] = max;
            }
        }
        System.out.println(dp[N]);
    }
}
