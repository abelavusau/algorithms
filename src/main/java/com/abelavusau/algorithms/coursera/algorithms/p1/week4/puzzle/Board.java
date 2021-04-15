package com.abelavusau.algorithms.coursera.algorithms.p1.week4.puzzle;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final byte[] tiles;
    private final int n;
    private int hamming = -1;
    private int manhattan = -1;
    private int emptyPos;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.n = tiles.length;
        this.tiles = new byte[n * n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    emptyPos = k;
                }
                this.tiles[k++] = (byte) tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder(n + "\n");
        int k = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(tiles[k++]);
                if (j < n - 1) {
                    s.append(" ");
                }
            }

            s.append("\n");
        }

        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {
        if (hamming == -1) {
            int h = 0;
            for (int i = 0; i < n * n; i++) {
                if (tiles[i] != 0 && tiles[i] != i + 1) {
                    h++;
                }
            }

            hamming = h;
        }

        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    // abs(x_val - x_goal) + abs(y_val - y_goal)
    public int manhattan() {
        if (manhattan == -1) {
            int m = 0;
            for (int i = 0; i < n * n; i++) {
                if (tiles[i] != 0 && tiles[i] != i + 1) {
                    int value = tiles[i] - 1;
                    int xGoal = value / n;
                    int yGoal = value % n;

                    int xCurrent = i / n;
                    int yCurrent = i % n;
                    m += Math.abs(xCurrent - xGoal) + Math.abs(yCurrent - yGoal);
                }
            }

            manhattan = m;
        }

        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        if (tiles[n * n - 1] != 0) {
            return false;
        }
        return hamming() == 0;
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;
        Board board = (Board) y;
        return Arrays.equals(tiles, board.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int i = emptyPos / n;
        int j = emptyPos % n;
        int exch;
        // move up
        if (i - 1 >= 0) {
            exch = (i - 1) * n + j;
            byte[] copy = tiles.clone();
            swap(copy, exch, emptyPos);
            neighbors.add(new Board(to2DimArray(copy)));
        }

        // move down
        if (i + 1 < n) {
            exch = (i + 1) * n + j;
            byte[] copy = tiles.clone();
            swap(copy, exch, emptyPos);
            neighbors.add(new Board(to2DimArray(copy)));
        }

        // move left
        if (j - 1 >= 0) {
            exch = i * n + (j - 1);
            byte[] copy = tiles.clone();
            swap(copy, exch, emptyPos);
            neighbors.add(new Board(to2DimArray(copy)));
        }

        // move right
        if (j + 1 < n) {
            exch = i * n + (j + 1);
            byte[] copy = tiles.clone();
            swap(copy, exch, emptyPos);
            neighbors.add(new Board(to2DimArray(copy)));
        }

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        byte[] copy = tiles.clone();
        int r = StdRandom.uniform(n * n);

        while (copy[r] == 0) {
            r = StdRandom.uniform(n * n);
        }

        int i = r / n;
        int j = r % n;

        boolean swapSuccess = false;

        while (!swapSuccess) {
            if (j + 1 < n && copy[i * n + (j + 1)] != 0) {
                swap(copy, i * n + j, i * n + (j + 1));
                swapSuccess = true;
            } else if (j > 0 && copy[i * n + (j - 1)] != 0) {
                swap(copy, i * n + j, i * n + (j - 1));
                swapSuccess = true;
            } else if (i < n - 1) {
                i++;
                swapSuccess = false;
            } else if (i == n - 1) {
                i--;
                swapSuccess = false;
            }
        }

        return new Board(to2DimArray(copy));
    }

    private int[][] to2DimArray(byte[] tiles) {
        int[][] matrix = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = tiles[i * n + j];
            }
        }

        return matrix;
    }

    private void swap(byte[] array, int i, int j) {
        byte tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = new int[][]{
                {4, 1, 0}, {5, 3, 2}, {7, 8, 6}
        };

        Board b = new Board(tiles);

        System.out.println("Initial board:");
        System.out.println(b);

        System.out.println("Neighbors:");
        b.neighbors().forEach(System.out::println);

        System.out.println("Twin:");
        System.out.println(b.twin());

        System.out.println("Hamming:");
        System.out.println(b.hamming());

        System.out.println("Manhattan:");
        System.out.println(b.manhattan());
    }
}
