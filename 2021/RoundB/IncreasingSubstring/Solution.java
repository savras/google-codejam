import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

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
        }

        return sb.toString();
    }
}
