package com.google.codejam.twenty.one.round.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

public class RabbitHouse {
    public void solve() throws FileNotFoundException {
        URL path = KGoodness.class.getResource("RabbitHouse.txt");
        File text = new File(Objects.requireNonNull(path).getFile());
        Scanner in = new Scanner(text);

        int t = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            System.out.println("Case #" + i + ": " + getResult(in));
        }
    }

    private static final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    private static long getResult(Scanner in) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] arr = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        long result = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                result += dfs(arr, i, j);
            }
        }
        return result;
    }

    private static long dfs(int[][] arr, int i, int j) {
        int m = arr.length;
        int n = arr[0].length;

        int neighbourMax = arr[i][j];
        for(int[] dir: directions) {
            int nx = i + dir[0];
            int ny = j + dir[1];

            if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                neighbourMax = Math.max(neighbourMax, arr[nx][ny]);
            }
        }

        long result = 0;
        // Set to 1 less than max neighbour
        if(arr[i][j] < neighbourMax) {
            result += neighbourMax - 1 - arr[i][j];
            arr[i][j] = neighbourMax - 1;
        }

        // Fix neighbours if needed
        for(int[] dir: directions) {
            int nx = i + dir[0];
            int ny = j + dir[1];

            if(nx >= 0 && nx < m && ny >= 0 && ny < n &&
                Math.abs(arr[nx][ny] - arr[i][j]) > 1) {
                result += dfs(arr, nx, ny);
            }
        }
        return result;
    }
}
