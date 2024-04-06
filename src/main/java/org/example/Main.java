package org.example;

import java.io.*;
import java.util.*;

class Solution {
    static int X, Y;
    static int CNT = Integer.MAX_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static boolean[][] visited;

    public static int solution(String[] board) {

        map = new char[board.length][board[0].length()];
        visited = new boolean[board.length][board[0].length()];

        for (int i = 0; i < board.length; i++) {
            map[i] = board[i].toCharArray();
            if (board[i].contains("R")) {
                X = i;
                Y = board[i].indexOf("R");
            }
        }

        bfs(X, Y);

        return CNT == Integer.MAX_VALUE ? -1 : CNT;
    }

    public static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        int cnt = -1;
        while (!q.isEmpty()) {
            cnt++;
            int size = q.size();

            for (int j = 0; j < size; j++) {
                int[] cur = q.poll();

                int cx = cur[0];
                int cy = cur[1];

                if (map[cx][cy] == 'G') {
                    CNT = Math.min(CNT, cnt);
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = cx;
                    int ny = cy;

                    while (nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length && map[nx][ny] != 'D') {
                        nx += dx[i];
                        ny += dy[i];
                    }

                    nx -= dx[i];
                    ny -= dy[i];

                    if (visited[nx][ny] || (cx == nx && cy == ny)) continue;

                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }
}