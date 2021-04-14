package com.abelavusau.algorithms.coursera.algorithms.p1.week4.puzzle;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final int[][] tiles;
    private int hamming = -1;
    private int manhattan = -1;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
    }

    // string representation of this board
    public String toString() {
        StringBuilder s = new StringBuilder(tiles.length + "\n");
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                s.append(tiles[i][j]);
                if (j < tiles.length - 1) {
                    s.append(" ");
                } else {
                    s.append("\n");
                }
            }
        }

        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        if (hamming == -1) {
            int h = 0;
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles[i].length; j++) {
                    int properValue = getProperValue(i, j);

                    if (tiles[i][j] != 0 && tiles[i][j] != properValue) {
                        h++;
                    }
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
            for (int i = 0; i < tiles.length; i++) {
                for (int j = 0; j < tiles.length; j++) {
                    int properValue = getProperValue(i, j);

                    if (tiles[i][j] != 0 && tiles[i][j] != properValue) {
                        int value = tiles[i][j] - 1;
                        int xGoal = value / dimension();
                        int yGoal = value % dimension();
                        m += Math.abs(i - xGoal) + Math.abs(j - yGoal);
                    }
                }
            }

            manhattan = m;
        }

        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        if (tiles[tiles.length - 1][tiles.length - 1] != 0) {
            return false;
        }
        return hamming() == 0;
    }

    @Override
    public boolean equals(Object y) {
        if (this == y) return true;
        if (y == null || getClass() != y.getClass()) return false;
        Board board = (Board) y;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == 0) {
                    // move up
                    if (i - 1 >= 0) {
                        int[][] copy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
                        swap(copy, i - 1, j, i, j);
                        neighbors.add(new Board(copy));
                    }

                    // move down
                    if (i + 1 < dimension()) {
                        int[][] copy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
                        swap(copy, i + 1, j, i, j);
                        neighbors.add(new Board(copy));
                    }

                    // move left
                    if (j - 1 >= 0) {
                        int[][] copy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
                        swap(copy, i, j - 1, i, j);
                        neighbors.add(new Board(copy));
                    }

                    // move right
                    if (j + 1 < dimension()) {
                        int[][] copy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
                        swap(copy, i, j + 1, i, j);
                        neighbors.add(new Board(copy));
                    }
                }
            }
        }

        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] copy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);

        int r = StdRandom.uniform(dimension() * dimension());
        int i = r / copy.length;
        int j = r % copy.length;

        while (copy[i][j] == 0) {
            r = StdRandom.uniform(dimension() * dimension());
            i = r / copy.length;
            j = r % copy.length;
        }

        boolean swapSuccess = false;

        while (!swapSuccess) {
            if (j < dimension() - 1 && copy[i][j + 1] != 0) {
                swap(copy, i, j, i, j + 1);
                swapSuccess = true;
            } else if (j > 0 && copy[i][j - 1] != 0) {
                swap(copy, i, j, i, j - 1);
                swapSuccess = true;
            } else if (i < dimension() - 1) {
                i++;
                swapSuccess = false;
            } else if (i == dimension() - 1) {
                i--;
                swapSuccess = false;
            }
        }

        return new Board(copy);
    }

    private int getProperValue(int i, int j) {
        int properValue = 0;

        if (i == dimension() - 1 && j == dimension() - 1) {
            return properValue;
        }
        properValue = i * tiles.length + j + 1;
        return properValue;
    }

    private void swap(int[][] array, int i1, int j1, int i2, int j2) {
        int tmp = array[i1][j1];
        array[i1][j1] = array[i2][j2];
        array[i2][j2] = tmp;
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
