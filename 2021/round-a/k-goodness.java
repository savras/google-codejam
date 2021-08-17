    import java.util.*;
    import java.io.*;
    import java.lang.Math;
    
    public class Solution {
      public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
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
