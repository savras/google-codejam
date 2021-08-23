package com.google.codejam.twenty.one.round.a;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Checksum {
    static Map<Integer, List<int[]>> graph;

    public void solve() throws FileNotFoundException {
        URL path = Checksum.class.getResource("Checksum.txt");
        File text = new File(Objects.requireNonNull(path).getFile());
        Scanner in = new Scanner(text);

        int t = in.nextInt();

        for (int i = 1; i <= t; ++i) {
            System.out.println("Case #" + i + ": " + getResult(in));
        }
    }

    private static int getResult(Scanner in) {
        graph = new HashMap<>();
        int n = in.nextInt();

        int[][] A = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                A[i][j] = in.nextInt();
            }
        }

        int totalCost = 0;
        int[][] B = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                B[i][j] = in.nextInt();
                totalCost += B[i][j];
            }
        }

        // Row parity
        for(int i = 0; i < n; i++) {
            in.nextInt();
        }
        // Col parity
        for(int i = 0; i < n; i++) {
            in.nextInt();
        }

        // node -> weight
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        Set<Integer> visited = new HashSet<>();

        buildGraph(A, B, queue, visited);
        int spanningCost = prim(queue, visited);

        return totalCost - spanningCost;
    }

    private static int prim(PriorityQueue<int[]> queue, Set<Integer> visited) {
        int result = 0;

        while(!queue.isEmpty()) {
            int[] item = queue.poll();

            if(!visited.contains(item[0])) {
                result += item[1];
            }
            visited.add(item[0]);
            for(int[] neighbour: graph.getOrDefault(item[0], new ArrayList<>())) {
                if(!visited.contains(neighbour[0])) {
                    queue.add(new int[] {neighbour[0], neighbour[1]});
                }
            }
        }

        return result;
    }

    private static void buildGraph(int[][] A, int[][] B, PriorityQueue<int[]> queue, Set<Integer> visited) {
        int n = A.length;

        int maxCost = 0;
        int startIndex = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(A[i][j] == -1) {
                    if(B[i][j] > maxCost) {
                        maxCost = B[i][j];
                        startIndex = i;
                    }

                    graph.computeIfAbsent(i, v -> new ArrayList<>()).add(new int[] {j + n, B[i][j]});
                    graph.computeIfAbsent(j + n, v -> new ArrayList<>()).add(new int[] {i, B[i][j]});
                }
            }
        }

        queue.add(new int[]{startIndex, 0});
        visited.add(startIndex);
    }
}
