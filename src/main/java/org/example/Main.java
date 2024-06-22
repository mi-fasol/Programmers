package org.example;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, res = Integer.MAX_VALUE, start, dest;
    static List<List<int[]>> graph = new ArrayList<>();
    static StringTokenizer st;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, c});
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());

        daikstra();

        System.out.println(res);
    }

    public static void daikstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.offer(new int[]{start, 0});

        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int dis = cur[1];

            if (dist[node] < dis) continue;

            for (int[] next : graph.get(node)) {
                int nextNode = next[0];
                int nextDist = dis + next[1];

                if(nextNode == dest) {
                    res = Math.min(res, nextDist);
                }

                if (dist[nextNode] > nextDist) {
                    dist[nextNode] = nextDist;
                    pq.offer(new int[]{nextNode, nextDist});
                }
            }
        }
    }
}