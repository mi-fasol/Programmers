package org.example;

import java.io.*;
import java.util.*;

class Solution {
    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] result = new int[id_list.length];
        int[] reportedCount = new int[id_list.length];
        HashMap<String, List<String>> reportMap = new HashMap<>();

        for (String s : report) {
            StringTokenizer st = new StringTokenizer(s);

            String whoReport = st.nextToken();
            String whoReported = st.nextToken();

            if (reportMap.containsKey(whoReport) && reportMap.get(whoReport).contains(whoReported)) {
                continue;
            } else if (reportMap.containsKey(whoReport)) {
                reportMap.get(whoReport).add(whoReported);
            } else {
                reportMap.put(whoReport, new ArrayList<>());
                reportMap.get(whoReport).add(whoReported);
            }
            int idx = Arrays.asList(id_list).indexOf(whoReported);
            reportedCount[idx]++;
        }

        for(int i = 0; i < id_list.length; i++){
            if (reportedCount[i] >= k){
                for(int j = 0; j < id_list.length; j++){
                    if(reportMap.containsKey(id_list[j]) && reportMap.get(id_list[j]).contains(id_list[i])){
                        result[j]++;
                    }
                }
            }
        }

        return result;
    }
}