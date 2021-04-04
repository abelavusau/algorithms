package com.abelavusau.algorithms.coursera.algorithms.p1.week4;

import java.util.PriorityQueue;

public class TaxicabNumbers implements Comparable<TaxicabNumbers> {
    private final int i;
    private final int j;
    private final int sum;

    public TaxicabNumbers(int i, int j) {
        this.i = i;
        this.j = j;
        this.sum = i * i * i + j * j * j;
    }

    public String toString() {
        return this.i + "^3 + " + this.j + "^3";
    }

    @Override
    public int compareTo(TaxicabNumbers o) {
        if (this.sum < o.sum) {
            return -1;
        } else if (this.sum > o.sum) {
            return 1;
        } else return Integer.compare(this.i, o.i);
    }

    /**
     * Time complexity n^2*log(n)
     * @param n
     */
    public static void find2(int n) {
        PriorityQueue<TaxicabNumbers> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            pq.add(new TaxicabNumbers(i, i));
        }

        int k = 1;
        TaxicabNumbers prev = new TaxicabNumbers(0, 0);

        while (!pq.isEmpty()) {
            TaxicabNumbers curr = pq.poll();

            if (prev.sum == curr.sum) {
                k++;

                if (k == 2) {
                    System.out.println(prev.sum + " = " + prev + " = " + curr);
                }
            } else {
                k = 1;
            }

            prev = curr;

            if (curr.j < n) {
                pq.add(new TaxicabNumbers(curr.i, curr.j + 1));
            }
        }
    }

    /**
     * Time complexity O(n^3)
     * @param n
     */
    public void find1(int n) {
        int count = 0;
        int k = 1;
        while (count < n) {
            int variantCount = 0;
            double root = Math.pow(k, 1.0 / 3);
            for (int i = 1; i <= root; i++) {
                for (int j = i + 1; j <= root; j++) {
                    if (i * i * i + j * j * j == k) {
                        variantCount++;
                    }
                }
            }

            if (variantCount == 2) {
                count++;
                System.out.println(count + ": " + k);
            }

            k++;
        }
    }


    public static void main(String[] args) {
        find2(12);
    }


}
