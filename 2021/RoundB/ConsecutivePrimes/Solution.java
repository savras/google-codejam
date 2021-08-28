import java.util.*;
import java.io.*;
import java.lang.Math;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int t = in.nextInt();

        TreeSet<Integer> primes = getSieve(100000);

        for (int i = 1; i <= t; ++i) {
            System.out.println("Case #" + i + ": " + getResult(in, primes));
        }
    }

    private static long getResult(Scanner in, TreeSet<Integer> primes) {
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

    private static boolean isPrime(int candidate, TreeSet<Integer> primes) {
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

    private static TreeSet<Integer> getSieve(int n) {
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

    private static void performSieve(boolean[] sieve, int start, int n) {
        for(int i = start * 2; i < n; i +=start) {
            if(!sieve[i]) {
                sieve[i] = true;
            }
        }
    }
}
