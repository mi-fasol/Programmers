package org.example;

import java.io.*;
import java.util.*;

class Solution {
    static char[] typeList = {'R', 'T', 'C', 'F', 'J', 'M', 'A', 'N'};

    public static String solution(String[] survey, int[] choices) {
        StringBuilder result = new StringBuilder();

        HashMap<Character, Integer> map = new HashMap<>();

        for (char i : typeList) {
            map.put(i, 0);
        }

        for (int i = 0; i < survey.length; i++) {
            char[] surveyChar = survey[i].toCharArray();
            if (choices[i] < 4) map.put(surveyChar[0], map.get(surveyChar[0]) + (4 - choices[i]));
            else if (choices[i] > 4) map.put(surveyChar[1], map.get(surveyChar[1]) + (choices[i] - 4));
            else continue;
        }

        for(int i = 0; i <4; i++){
            if(map.get(typeList[i*2]) < map.get(typeList[i*2 + 1])){
                result.append(typeList[i * 2 + 1]);
            } else{
                result.append(typeList[i * 2]);
            }
        }

        return result.toString();
    }
}