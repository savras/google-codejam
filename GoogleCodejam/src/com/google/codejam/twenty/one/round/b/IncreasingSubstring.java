package com.google.codejam.twenty.one.round.b;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class IncreasingSubstring {
    public void solve() throws FileNotFoundException {
        URL path = IncreasingSubstring.class.getResource("IncreasingSubstring.txt");
        File text = new File(Objects.requireNonNull(path).getFile());
        Scanner in = new Scanner(text);

        int t = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            System.out.println("Case #" + i + ": " + getResult(in));
        }
    }

    private static String getResult(Scanner in) {
        int n = in.nextInt();

        StringBuilder sb = new StringBuilder();
        int result = 1;

        in.nextLine();
        String str = in.nextLine();
        for(int i = 0; i < n; i++) {
            if (i > 0) {
                result = str.charAt(i) > str.charAt(i - 1) ? result + 1 : 1;
            }
            sb.append(result);
            sb.append(" ");
        }

        return sb.substring(0, sb.length() - 1);
    }
}

