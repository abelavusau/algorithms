package com.abelavusau.algorithms.coursera.algorithms.p1.week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by abelavusau on 4/18/18.
 */
public class Percolation {
    private int openCounter = 0;
    private final boolean[] siteMap;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final int dimentional;
    private final int virtualTopValue;
    private final int virtualBottomValue;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Check the input arguments!");
        }
        this.siteMap = new boolean[n * n + 2];
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2); // +2 virtual sites
        this.dimentional = n;
        this.openCounter = 0;
        this.virtualTopValue = 0;
        this.virtualBottomValue = n * n + 1;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > dimentional || col > dimentional) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        int p = encode(row, col);

        if (!isOpen(row, col)) {
            siteMap[p] = true;
            openCounter++;

            if (row == 1) {
                // connect the top row elements to the top virtual site
                connectVirtualSite(row, col, virtualTopValue);
            } else if (row == dimentional) {
                // connect the bottom row elements to the bottom virtual site
                connectVirtualSite(row, col, virtualBottomValue);
            }

            if (row < dimentional && isOpen(row + 1, col)) {
                int topQ = encode(row + 1, col);
                weightedQuickUnionUF.union(p, topQ);
            }

            if (col < dimentional && isOpen(row, col + 1)) {
                int rightQ = encode(row, col + 1);
                weightedQuickUnionUF.union(p, rightQ);
            }

            if (row > 1 && isOpen(row - 1, col)) {
                int bottomQ = encode(row - 1, col);
                weightedQuickUnionUF.union(p, bottomQ);
            }

            if (col > 1 && isOpen(row, col - 1)) {
                int leftQ = encode(row, col - 1);
                weightedQuickUnionUF.union(p, leftQ);
            }
        }
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > dimentional || col > dimentional) {
            throw new IllegalArgumentException("Invalid arguments");
        }

        return siteMap[encode(row, col)];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > dimentional || col > dimentional) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return weightedQuickUnionUF.connected(encode(row, col), virtualTopValue);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openCounter;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(virtualBottomValue, virtualTopValue);
    }

    private int encode(int row, int column) {
        if (row < 1 || column < 1 || row > dimentional || column > dimentional) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        return (row - 1) * this.dimentional + column;
    }

    private void connectVirtualSite(int row, int column, int value) {
        weightedQuickUnionUF.union(value, encode(row, column));
    }
}
