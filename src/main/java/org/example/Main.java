package org.example;

import java.io.*;
import java.util.*;
class Solution {
    static int[] result;

    public static int[] solution(long[] numbers) {

        result = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            StringBuilder sb = new StringBuilder();
            String binaryValue = Long.toBinaryString(numbers[i]);
            if (!binaryValue.contains("0")) {
                result[i] = 1;
                continue;
            }
            if (binaryValue.length() == 1) {
                if (numbers[i] == 0) result[i] = 0;
                continue;
            } else{
                binaryValue = makeTree(binaryValue);
            }
            sb.append(binaryValue);

            binaryValue = sb.toString();

            result[i] = isUnavailable(binaryValue, 0, binaryValue.length() - 1) ? 0 : 1;
        }

        return result;
    }

    public static boolean isUnavailable(String binaryValue, int start, int end) {
        if (start > end || start == end) {
            return false;
        }

        int root = (start + end) / 2;

        if (binaryValue.charAt(root) == '0') {
            for(int i = start; i <= end; i++) {
                if(binaryValue.charAt(i) == '1') return true;
            }
            return false;
        }

        boolean left = isUnavailable(binaryValue, start, root - 1);


        boolean right = isUnavailable(binaryValue, root + 1, end);

        return left || right;
    }

    public static String makeTree(String value){
        int treeLength = 1;
        while(value.length() > treeLength){
            treeLength = treeLength * 2 + 1;
        }

        int zeroLength = treeLength - value.length();
        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(zeroLength));
        return sb.append(value).toString();
    }
}
