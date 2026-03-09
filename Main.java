package org.example;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int TestCases = Integer.parseInt(in.readLine().trim());


        for (int i = 0; i < TestCases; i++) {
            int numOfOffers = Integer.parseInt(in.readLine().trim());

            Record[] characteristics = new Record[numOfOffers];

            for(int j=0;j<numOfOffers;j++){
                String offer = (in.readLine().trim());
                String[] parts = offer.split(" ");
                int startTime = Integer.parseInt(parts[0]);
                int duration = Integer.parseInt(parts[1]);
                int price = Integer.parseInt(parts[2]);

                Record rec = new Record(startTime, duration, price);

                characteristics[j] = rec;
            }
            Arrays.sort(characteristics);
            System.out.println(dynamicProg(characteristics, numOfOffers));
        }

    }


    private static int dynamicProg(Record[] characteristics, int numOfOffers) {
        int[] max = new int[numOfOffers];
        max[0] = characteristics[0].price;
        for(int j=1;j<numOfOffers;j++){
            Record rec = characteristics[j];
            max[j] = Math.max(max[j-1], rec.price + search(characteristics, max, j));
        }
        Arrays.sort(max);
        return max[numOfOffers-1];
    }

    private static int search(Record[] characteristics, int[] max,  int j) {
        Record rec = characteristics[j];
        for (int i=j-1;i>=0;i--){
            Record rec2 = characteristics[i];
            if(rec2.endTime<=rec.startTime){
                return max[i];
            }
        }
        return 0;
    }

    public static class Record implements Comparable {
        int startTime;
        int duration;
        int price;
        int endTime;
        public Record(int startTime, int duration, int price) {
            this.startTime = startTime;
            this.duration = duration;
            this.price = price;
            this.endTime = startTime + duration;
        }

        @Override
        public int compareTo(Object o) {
            if(o instanceof Record){
                return this.endTime - ((Record)o).endTime;
            }
            else return 0;
        }
    }
}

