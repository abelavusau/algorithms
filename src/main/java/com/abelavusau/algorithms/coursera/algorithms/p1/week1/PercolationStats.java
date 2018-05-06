package com.abelavusau.algorithms.coursera.algorithms.p1.week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by abelavusau on 4/21/18.
 */
public class PercolationStats {
    private static final double CONFIDENCE_95 = 1.96;
    private final double[] thresholds;
    private double means;
    private double stddev;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Check the input arguments!");
        }

        this.thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int randomRow = StdRandom.uniform(1, n + 1);
                int randomColumn = StdRandom.uniform(1, n + 1);
                percolation.open(randomRow, randomColumn);
            }

            this.thresholds[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(this.thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(this.thresholds);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return means - calculated();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return means + calculated();
    }

    private double calculated() {
        return CONFIDENCE_95 * stddev / Math.sqrt(this.thresholds.length);
    }

    // test client
    public static void main(String[] args) {
        if (args.length < 2) {
            throw new RuntimeException("Wrong number of arguments. Must be 2:\n1) number of sites in a row;\n2) number of trials");
        }

        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        percolationStats.means = percolationStats.mean();
        percolationStats.stddev = percolationStats.stddev();
        System.out.printf("mean                     = %f\n", percolationStats.means);
        System.out.printf("stddev                   = %f\n", percolationStats.stddev);
        System.out.printf("95%% confidence interval  = [%f, %f]", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
