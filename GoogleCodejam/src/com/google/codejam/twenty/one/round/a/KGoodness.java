package com.google.codejam.twenty.one.round.a;

import java.net.URL;
import java.util.*;
import java.io.*;
import java.lang.Math;

public class KGoodness {
    public void solve() throws FileNotFoundException {
        URL path = KGoodness.class.getResource("KGoodness.txt");
        File text = new File(Objects.requireNonNull(path).getFile());
        Scanner in = new Scanner(text);

        int t = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.

        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            int m = in.nextInt();
            in.nextLine();
            String o = in.nextLine();

            int left = 0;
            int right = n - 1;

            int goodness = 0;
            while(left < right) {
                if(o.charAt(left) != o.charAt(right)) {
                  goodness++;
                }
                left++;
                right--;
            }
            System.out.println("Case #" + i + ": " + Math.abs(m - goodness));
        }
    }
}
