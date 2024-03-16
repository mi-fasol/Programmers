package org.example;

import java.io.*;
import java.util.*;

class Solution {
    public static List<List<Integer>> graph = new ArrayList<>();

    public static int solution(int[] info, int[][] edges) {
        int nodeLength = info.length;
        for(int i=0;i<nodeLength;i++){
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
        }

        List<Integer> nextNodes = new ArrayList<>(graph.get(0));
        return dfs(0, 0, 0, nextNodes,info);
    }

    public static int dfs(int sheep, int wolf, int curNode, List<Integer> nextNodes, int[] info){
        if(info[curNode] == 0) sheep++;
        else wolf++;

        int ans = sheep;
        if(sheep <= wolf) return ans;
        for(int i=0;i<nextNodes.size();i++){
            int nextNode = nextNodes.get(i);
            List<Integer> stackNodes = new ArrayList<>(nextNodes);
            stackNodes.remove((Integer)nextNode);
            stackNodes.addAll(graph.get(nextNode));
            ans = Math.max(ans, dfs(sheep, wolf, nextNode, stackNodes, info));
        }
        return ans;
    }
}