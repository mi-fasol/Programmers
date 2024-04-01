package org.example;

import java.io.*;
import java.util.*;

class Solution {
    public static int solution(String[][] book_time) {
        HashMap<Integer, List<Integer>> book = new HashMap<>();

        for (int i = 0; i < book_time.length; i++) {
            String start = book_time[i][0];
            String end = book_time[i][1];

            int start_time = Integer.parseInt(start.substring(0, 2)) * 60 + Integer.parseInt(start.substring(3));
            int end_time = Integer.parseInt(end.substring(0, 2)) * 60 + Integer.parseInt(end.substring(3));

            book.put(i, new ArrayList<>(Arrays.asList(start_time, end_time)));
        }

        List<Integer> keySet = new ArrayList<>(book.keySet());

        keySet.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return book.get(o1).get(0).compareTo(book.get(o2).get(0));
            }
        });

        ArrayList<Boolean> room = new ArrayList<>();
        room.add(true);
        ArrayList<Integer> who = new ArrayList<>();
        who.add(keySet.get(0));

        for (int i = 1; i < keySet.size(); i++) {
            boolean isPossible = false;
            for (int j = 0; j < room.size(); j++) {
                if (book.get(keySet.get(i)).get(0) >= book.get(who.get(j)).get(1) + 10) {
                    who.set(j, keySet.get(i));
                    isPossible = true;
                    break;
                }
            }
            if (!isPossible) {
                room.add(true);
                who.add(keySet.get(i));
            }
        }
        return room.size();
    }
}