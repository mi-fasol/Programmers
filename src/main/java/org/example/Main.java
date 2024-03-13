package org.example;

import java.io.*;
import java.util.*;

class Solution {
    public static int[] solution(int[] fees, String[] records) {

        HashMap<String, Integer> carList = new HashMap<>();
        HashMap<String, Integer> feeList = new HashMap<>();

        int defaultMinute = fees[0];
        int defaultFee = fees[1];
        int extraMinute = fees[2];
        int extraFee = fees[3];


        for (String record : records) {
            StringTokenizer st = new StringTokenizer(record);
            String time = st.nextToken();
            String carNumber = st.nextToken();
            String inOut = st.nextToken();

            int hour = Integer.parseInt(time.split(":")[0]);
            int minute = Integer.parseInt(time.split(":")[1]);

            int recordTime = hour * 60 + minute;

            if (Objects.equals(inOut, "IN")) {
                carList.put(carNumber, recordTime);
            } else {
                int inTime = carList.get(carNumber);
                int feeTime = recordTime - inTime;
                if (feeList.containsKey(carNumber)) {
                    feeTime += feeList.get(carNumber);
                }
                feeList.put(carNumber, feeTime);
                carList.remove(carNumber);
            }
        }

        if (!carList.isEmpty()) {
            for (String carNumber : carList.keySet()) {
                int inTime = carList.get(carNumber);
                int time = 1439 - inTime;
                if (feeList.containsKey(carNumber)) {
                    time += feeList.get(carNumber);
                }
                feeList.put(carNumber, time);
            }
        }

        for (String car : feeList.keySet()) {
            int parkingTime = feeList.get(car);

            if (parkingTime <= defaultMinute) feeList.put(car, defaultFee);
            else {
                int totalFee = defaultFee + ((int) (Math.ceil((double) (parkingTime - defaultMinute) / extraMinute)) * extraFee);
                feeList.put(car,totalFee);
            }
        }

        return feeList.keySet().stream().sorted().mapToInt(feeList::get).toArray();
    }
}