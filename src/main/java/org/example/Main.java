package org.example;

import java.io.*;
import java.util.*;

class Solution {
    public static ArrayList<Integer> solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> result = new ArrayList<>();

        HashMap<Character, Integer> termMap = new HashMap<>();

        for (String term : terms) {
            StringTokenizer st = new StringTokenizer(term);

            char type = st.nextToken().charAt(0);
            int value = Integer.parseInt(st.nextToken());간

            termMap.put(type, value);
        }

        String todayDate = today.split("\\.")[0] + today.split("\\.")[1] + today.split("\\.")[2];

        int deadline = Integer.parseInt(todayDate);

        System.out.println(deadline);

        for (int i = 0; i < privacies.length; i++) {
            StringTokenizer st = new StringTokenizer(privacies[i]);

            String date = st.nextToken();

            int year = Integer.parseInt(date.split("\\.")[0]);
            int month = Integer.parseInt(date.split("\\.")[1]);
            int day = Integer.parseInt(date.split("\\.")[2]);

            char type = st.nextToken().charAt(0);

            month += termMap.get(type);

            if (month > 12) {
                year += month / 12;
                month = month % 12;

                if (month == 0) {
                    month = 12;
                    year -= 1;
                }
            }

            String m = month < 10 ? "0" + month : month + "";
            String d = day < 10 ? "0" + day : day + "";

            String deadlineDate = year + "" + m + d;

            System.out.println(deadlineDate);


            int dday = Integer.parseInt(deadlineDate);

            if (deadline - dday >= 0) {
                result.add(i + 1);
            }
        }

        return result;
    }
}