package org.example;

import java.io.*;
import java.util.*;

class Solution {
    public static int[] discount = {10, 20, 30, 40};
    public static ArrayList<int[]> res;

    public static int[] solution(int[][] users, int[] emoticons) {
        res = new ArrayList<>();

        res.add(new int[]{0, 0});

        dfs(users, emoticons, new int[emoticons.length], 0);

        Collections.sort(res, (a, b) -> b[0] - a[0] == 0 ? b[1] - a[1] : b[0] - a[0]);
        return res.get(0);
    }

    public static void dfs(int[][] user, int[] emoticon, int[] dis, int cnt) {
        if (cnt == emoticon.length) {
            int plusUser = 0;
            int emoticonSelling = 0;

            for (int i = 0; i < user.length; i++) {
                int sum = 0;
                for (int j = 0; j < emoticon.length; j++) {
                    if (dis[j] >= user[i][0])
                        sum += emoticon[j] / 100 * (100 - dis[j]);
                }
                if (sum >= user[i][1]) {
                    plusUser++;
                } else {
                    emoticonSelling += sum;
                }
            }
            res.add(new int[]{plusUser, emoticonSelling});
            return;
        } else {
            for (int i = 0; i < 4; i++) {
                dis[cnt] = discount[i];
                dfs(user, emoticon, dis, cnt + 1);
            }
        }
    }
}