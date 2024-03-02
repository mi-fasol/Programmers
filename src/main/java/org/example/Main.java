package org.example;

import java.io.*;
import java.util.*;

class Solution {
    static Character[] location = {'d', 'l', 'r', 'u'};
    static char[][] campus;
    static String result = "impossible";
    static int[] dy = {0, -1, 1, 0};
    static int[] dx = {1, 0, 0, -1};
    static int N, M, X, Y, R, C, K;


    public static String solution(int n, int m, int x, int y, int r, int c, int k) {

        campus = new char[n][m];
        N = n;
        M = m;
        Y = y - 1;
        X = x - 1;
        R = r - 1;
        C = c - 1;
        K = k;

        if (y == c && x == r) return "";
        if (!isAvailable(X, Y, 0)) return result;

        dfs(X, Y, 0, "");

        return result;
    }

    public static boolean isAvailable(int x, int y, int cnt) {
        int remainMoves = K - cnt;
        int distanceToGoal = Math.abs(R - x) + Math.abs(C - y);

        return remainMoves >= distanceToGoal && ((remainMoves - distanceToGoal) % 2 == 0);
    }

    public static boolean dfs(int x, int y, int cnt, String str) {
        if(!isAvailable(x, y, cnt)) return false;

        if (cnt == K) {
            if (x == R && y == C) {
                result = str;
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int fx = x + dx[i];
            int fy = y + dy[i];

            if (fy < 0 || fx < 0 || fx >= N || fy >= M) continue;

            if (dfs(fx, fy, cnt + 1, str + location[i])) {
                return true;
            }
        }

        return false;
    }
}