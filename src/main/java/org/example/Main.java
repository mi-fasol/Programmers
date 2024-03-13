package org.example;

import java.io.*;
import java.util.*;

class Solution {
    public static int solution(int n, int k) {
        int result = 0;

        String base = Integer.toString(n, k);

        String[] split = base.split("0");

        for(String s : split){
            if(!s.equals("1") && !s.equals("")){
                Long number = Long.parseLong(s);
                if(IsPrimeNumber(number)){
                    result++;
                }
            }
        }
        return result;
    }

    public static boolean IsPrimeNumber(Long number) {
        double sqrtNumber = Math.sqrt(number);
        for (int i = 2; i <= sqrtNumber; ++i) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}