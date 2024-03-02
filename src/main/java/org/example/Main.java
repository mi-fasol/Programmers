package org.example;

import java.io.*;
import java.util.*;

class Solution {
    public static ArrayList<String> result;
    public static int[] parent;

    public static String[] solution(String[] commands) {
        parent = new int[2500];
        result = new ArrayList<>();

        String[] table = new String[2500];

        for (int i = 0; i < 2500; i++) {
            parent[i] = i;
            table[i] = "";
        }

        for (int i = 0; i < commands.length; i++) {
            StringTokenizer st = new StringTokenizer(commands[i]);
            String command = st.nextToken();
            if (command.equals("UPDATE")) {
                if (st.countTokens() == 2) {
                    String value = st.nextToken();
                    String change = st.nextToken();
                    for (int j = 0; j < table.length; j++) {
                        if (table[j].equals(value)) {
                            table[j] = change;
                        }
                    }
                } else {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String value = st.nextToken();
                    int root = find(changeIndex(r, c));
                    for (int j = 0; j < table.length; j++) {
                        if (find(j) == root) {
                            table[j] = value;
                        }
                    }
                }
            } else if (command.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());
                int root1 = find(changeIndex(r1, c1));
                int root2 = find(changeIndex(r2, c2));

                String value = table[root1].equals("") ? table[root2] : table[root1];

                if (root1 == root2) {
                    continue;
                }

                union(root1, root2);

                for (int j = 0; j < 2500; j++) {
                    if (find(j) == root2) {
                        table[j] = table[root1];
                        parent[j] = root1;
                    }
                }

                table[changeIndex(r1, c1)] = value;
                table[changeIndex(r2, c2)] = value;
            } else if (command.equals("UNMERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int root = find(changeIndex(r1, c1));

                String value = table[root];
                for (int j = 0; j < 2500; j++) {
                    if (find(j) == root) {
                        table[j] = "";
                        parent[j] = j;
                    }
                }
                if (value != null)
                    table[changeIndex(r1, c1)] = value;
            } else {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                int root = find(changeIndex(r, c));
                String rs = table[root];
                result.add(Objects.equals(rs, "") ? "EMPTY" : rs);
            }
        }
        return result.toArray(new String[0]);
    }

    public static int find(int a) {
        if (parent[a] == a)
            return a;
        else
            return parent[a] = find(parent[a]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b)
            parent[b] = a;
    }

    public static int changeIndex(int r, int c) {
        return (r - 1) * 50 + (c - 1);
    }
}