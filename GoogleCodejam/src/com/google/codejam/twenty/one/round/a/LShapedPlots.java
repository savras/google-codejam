package com.google.codejam.twenty.one.round.a;

import java.net.URL;
import java.util.*;
import java.io.*;
import java.lang.Math;

public class LShapedPlots {
    public void solve() throws FileNotFoundException {
        URL path = LShapedPlots.class.getResource("LShapedPlots.txt");
        File text = new File(Objects.requireNonNull(path).getFile());
        Scanner in = new Scanner(text);

        int t = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            System.out.println("Case #" + i + ": " + getResult(in));
        }
    }

    private static int getResult(Scanner in) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] arr = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        int[][] top = new int[m][n];
        for(int col = 0; col < n; col++) {
            for(int row = 0; row < m; row++) {
                if(arr[row][col] == 1) {
                    if (row > 0) {
                        top[row][col] = top[row - 1][col] + 1;
                    } else {
                        top[row][col] = 1;
                    }
                }
            }
        }

        int[][] down = new int[m][n];
        for(int row = m - 1; row >= 0; row--) {
            for(int col = 0; col < n; col++) {
                if(arr[row][col] == 1) {
                    if(row < m - 1) {
                        down[row][col] = down[row + 1][col] + 1;
                    } else {
                        down[row][col] = 1;
                    }
                }
            }
        }

        int[][] left = new int[m][n];
        for(int row = 0; row < m; row++) {
            for(int col = 0; col < n; col++) {
                if(arr[row][col] == 1) {
                    if(col > 0) {
                        left[row][col] = left[row][col - 1] + 1;
                    } else {
                        left[row][col] = 1;
                    }
                }
            }
        }

        // Bottom up
        int[][] right = new int[m][n];
        for(int row = 0; row < m; row++) {
            for(int col = n - 1; col >= 0; col--) {
                if(arr[row][col] == 1) {
                    if (col < n - 1) {
                        right[row][col] = right[row][col + 1] + 1;
                    } else {
                        right[row][col] = 1;
                    }
                }
            }
        }

        int result = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(arr[i][j] == 1) {
                    result += getSegments(i, j, top, down, left, right);
                }
            }
        }
        return result;
    }

    private static int getSegments(int i, int j,
                                   int[][] top, int[][] down, int[][] left, int[][] right) {
        int result = 0;

        result += top[i][j] > 1 && left[i][j] > 1 ?
                Math.min(top[i][j], left[i][j]/2) + Math.min(left[i][j], top[i][j]/2) - 2 : 0;

        result += top[i][j] > 1 && right[i][j] > 1 ?
                Math.min(top[i][j], right[i][j]/2) + Math.min(right[i][j], top[i][j]/2) - 2 : 0;

        result += down[i][j] > 1 && left[i][j] > 1 ?
                Math.min(down[i][j], left[i][j]/2) + Math.min(left[i][j], down[i][j]/2) - 2 : 0;

        result += down[i][j] > 1 && right[i][j] > 1 ?
                Math.min(down[i][j], right[i][j]/2) + Math.min(right[i][j], down[i][j]/2) - 2 : 0;

        return result;
    }
}
