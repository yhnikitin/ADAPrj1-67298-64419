package org.example;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int P = Integer.parseInt(in.readLine().trim());
        int R = Integer.parseInt(in.readLine().trim());

        int[] min = new int[R];
        int[] max = new int[R];

        for (int i = 0; i < R; i++) {
            String[] parts = in.readLine().split(" ");
            int x = Integer.parseInt(parts[1]);

            if (parts[0].equals("MIN")) {
                min[i] = x;
                max[i] = P;
            } else {
                min[i] = 0;
                max[i] = x;
            }
        }

        long[][] dp = new long[R + 1][P + 1];
        dp[0][0] = 1;

        for (int r = 1; r <= R; r++) {
            for (int p = 0; p <= P; p++) {
                if (dp[r - 1][p] == 0) continue;

                for (int k = min[r - 1]; k <= max[r - 1]; k++) {
                    if (p + k <= P) {
                        dp[r][p + k] += dp[r - 1][p];
                    }
                }
            }
        }

        System.out.println(dp[R][P]);
    }
}

