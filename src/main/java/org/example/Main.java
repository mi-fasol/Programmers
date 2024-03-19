package org.example;

import java.io.*;
import java.util.*;

class Solution {
    public static int solution(int[][] board, int[][] skill) {
        int answer = 0;

        int[] degreeList = new int[board.length*board[0].length + 1];

        for(int i = 0; i< board.length; i++){
            System.arraycopy(board[i], 0, degreeList, i * board[0].length, board[i].length);
        }

        for (int[] ints : skill) {
            int type = ints[0];
            int r1 = ints[1];
            int c1 = ints[2];
            int r2 = ints[3];
            int c2 = ints[4];
            int degree = ints[5];

            if (type == 1) {
                degree *= -1;
            }


            for (int j = r1; j <= r2; j++) {
                for (int k = c1; k <= c2; k++) {
                    int index = j * board[0].length + k;
                    degreeList[index] += degree;
                }
            }
        }

        degreeList[board.length*board[0].length] = 0;

        for (int j : degreeList) {
            if (j > 0) {
                answer++;
            }
        }

        return answer;
    }
}