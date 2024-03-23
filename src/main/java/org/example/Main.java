package org.example;

import java.io.*;
import java.util.*;

class Solution {

    static int[][] b;
    static int INF = 987654321;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static int solution(int[][] board, int[] aloc, int[] bloc) {

        b = board;

        return Math.abs(dfs(aloc[0], aloc[1], bloc[0], bloc[1], true));
    }

    public static int dfs(int ax, int ay, int bx, int by, boolean turn) {
        int x = turn ? ax : bx;
        int y = turn ? ay : by;

        List<Integer> list = new ArrayList<>();
        for (int d = 0; d < 4; d++) {
            int fx = x + dx[d];
            int fy = y + dy[d];

            // 이동 불가한 곳이면 continue
            if (fx < 0 || fy < 0 || fx >= b.length || fy >= b[0].length || b[fx][fy] == 0) continue;

            // A와 B가 같은 위치에 있는 경우
            if (ax == bx && ay == by) {
                list.add(1);
                continue;
            }

            // 방문 표시 - 발판이 사라짐
            b[x][y] = 0;

            int res;

            // A의 차례인 경우 B의 차례로 변경
            if (turn) res = -dfs(fx, fy, bx, by, !turn);

            // B의 차례인 경우 A의 차례로 변경
            else res = -dfs(ax, ay, fx, fy, !turn);

            // 결과값이 0보다 크거나 같음면 +1, 아니면 -1
            if (res >= 0) res++;
            else res--;

            // 결과값을 list에 추가
            list.add(res);

            // 백트래킹을 위해 다시 1로 설정 - x, y를 밟지 않고 넘어갈 경우
            b[x][y] = 1;
        }

        int pMax, pMin, mMax, mMin;
        // 최대 최소 초기화
        pMax = mMax = -INF;
        pMin = mMin = INF;

        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);

            // list에서 꺼내온 결과가 양수면 p변수 갱신, 음수면 m변수 갱신
            if (num > 0) {
                pMax = Math.max(pMax, num);
                pMin = Math.min(pMin, num);
            } else {
                mMax = Math.max(mMax, num);
                mMin = Math.min(mMin, num);
            }
        }

        // 둘 다 움직일 수 없었던 경우 0 반환
        if (pMax == -INF && mMax == -INF) return 0;
        // pMax가 갱신되지 않았다면 B가 이긴 경우 -> mMin 반환
        else if (pMax == -INF) return mMin;
        // pMax가 갱신되었다면 A가 이긴 경우 -> pMin 반환
        else if (pMax != -INF) return pMin;
        // 예외 처리
        else return 0;
    }
}