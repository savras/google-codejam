package com.google.codejam.twenty.one.round.b;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;

public class ConsecutivePrimes {
    public void solve() throws FileNotFoundException {
        URL path = ConsecutivePrimes.class.getResource("ConsecutivePrimes.txt");
        File text = new File(Objects.requireNonNull(path).getFile());
        Scanner in = new Scanner(text);

        int t = in.nextInt();

        TreeSet<Integer> primes = getSieve(100000);

        for (int i = 1; i <= t; ++i) {
            System.out.println("Case #" + i + ": " + getResult(in, primes));
        }
    }

    private long getResult(Scanner in, TreeSet<Integer> primes) {
        long target = in.nextLong();

        int root = (int) Math.ceil(Math.sqrt(target));

        List<Integer> candidatePrimes = new ArrayList<>();
        for(int i = root - 1; candidatePrimes.size() < 3; i--) {
            if(isPrime(i, primes)) {
                candidatePrimes.add(0, i);
            }
        }

        for(int i = root; candidatePrimes.size() < 6; i++) {
            if(isPrime(i, primes)) {
                candidatePrimes.add(i);
            }
        }

        long result = 0;
        for(int i = 0; i < candidatePrimes.size() - 1; i++) {
            long product = (long)candidatePrimes.get(i) * candidatePrimes.get(i + 1);

            if(product <= target) {
                result = Long.max(result, product);
            }
        }
        return result;
    }

    private boolean isPrime(int candidate, TreeSet<Integer> primes) {
        for(int p: primes) {
            if(p > Math.sqrt(candidate)) {
                break;
            }
            if(candidate % p == 0) {
                return false;
            }
        }
        return true;
    }

    private TreeSet<Integer> getSieve(int n) {
        boolean[] sieve = new boolean[n];
        for(int i = 2; i < n; i++) {
            if(!sieve[i]) {
                performSieve(sieve, i, n);
            }
        }

        TreeSet<Integer> primes = new TreeSet<>();
        for(int i = 2; i < n; i++) {
            if(!sieve[i]) {
                primes.add(i);
            }
        }
        return primes;
    }

    private void performSieve(boolean[] sieve, int start, int n) {
        for(int i = start * 2; i < n; i +=start) {
            if(!sieve[i]) {
                sieve[i] = true;
            }
        }
    }
}
