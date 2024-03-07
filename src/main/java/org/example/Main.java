package org.example;

import java.io.;
import java.util.;

class Solution {
    public static int solution(int[] queue1, int[] queue2) {
        int result = 0;

        Deque<Long> q1 = new ArrayDeque<>();
        Deque<Long> q2 = new ArrayDeque<>();

        long q1Sum = 0;
        long q2Sum = 0;

        for (int queue : queue1) {
            q1.add((long) queue);
            q1Sum += queue;
        }

        long size = q1.size();

        for (int queue : queue2) {
            q2.add((long) queue);
            q2Sum += queue;
        }

        while (q1Sum != q2Sum) {

            result++;

            if (q1Sum > q2Sum) {
                long next = q1.poll();
                q1Sum -= next;
                q2Sum += next;
                q2.offer(next);
            } else {
                long next = q2.poll();
                q2Sum -= next;
                q1Sum += next;
                q1.offer(next);
            }

            if (q1Sum == q2Sum) {
                break;
            }

            if (result > size * 4) {
                result = -1;
                break;
            }

        }

        return result;
    }
}