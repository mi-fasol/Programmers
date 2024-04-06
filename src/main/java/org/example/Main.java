package org.example;

import java.io.*;
import java.util.*;

class Solution {
    public static char[][] map;
    public static boolean lever = false;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {1, 0, -1, 0};
    public static boolean[][] visited;
    public static int CNT, ANSWER;
    public static int N, M, LX, LY;

    public static int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();

        map = new char[maps.length][maps[0].length()];
        visited = new boolean[maps.length][maps[0].length()];
        CNT = ANSWER = Integer.MAX_VALUE;

        int x = 0;
        int y = 0;

        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                map[i][j] = maps[i].charAt(j);
                if (map[i][j] == 'S') {
                    x = i;
                    y = j;
                }
            }
        }

        leverBfs(x, y, -1);

        if(CNT != Integer.MAX_VALUE) {
            visited = new boolean[maps.length][maps[0].length()];
            leverBfs(LX, LY, CNT-1);
        }

        return ANSWER == Integer.MAX_VALUE ? -1 : ANSWER;
    }

    public static void leverBfs(int x, int y, int count) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int step = 0; step < size; step++) {
                int[] now = queue.poll();

                int a = now[0];
                int b = now[1];

                visited[a][b] = true;

                if (map[a][b] == 'L' && !lever) {
                    CNT = Math.min(CNT, count);
                    lever = true;
                    LX = a;
                    LY = b;
                    return;
                } else if (map[a][b] == 'E' && lever) {
                    ANSWER = Math.min(ANSWER, count);
                    return;
                }
                for (int i = 0; i < 4; i++) {
                    int fx = a + dx[i];
                    int fy = b + dy[i];

                    if (fx >= 0 && fy >= 0 && fx < N && fy < M && !visited[fx][fy] && map[fx][fy] != 'X') {
                        queue.offer(new int[]{fx, fy});
                        visited[fx][fy] = true;
                    }
                }
            }
        }
    }
}