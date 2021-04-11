package com.abelavusau.algorithms.coursera.algorithms.p1.week4.puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    private final Random r = new Random();
    private final int[][] tiles;
    private int hamming = -1;
    private int manhattan = -1;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
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
                        int x_goal = value % dimension();
                        int y_goal = value / dimension();
                        m += Math.abs(j - x_goal) + Math.abs(i - y_goal);
                    }
                }
            }

            manhattan = m;
        }

        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
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
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] == 0) {
                    int empty = tiles[i][j];
                    // move up
                    if (i - 1 >= 0) {
                        int[][] copy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
                        int tmp = copy[i - 1][j];
                        copy[i - 1][j] = empty;
                        copy[i][j] = tmp;
                        neighbors.add(new Board(copy));
                    }

                    // move down
                    if (i + 1 < dimension()) {
                        int[][] copy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
                        int tmp = copy[i + 1][j];
                        copy[i + 1][j] = empty;
                        copy[i][j] = tmp;
                        neighbors.add(new Board(copy));
                    }

                    // move left
                    if (j - 1 >= 0) {
                        int[][] copy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
                        int tmp = copy[i][j - 1];
                        copy[i][j - 1] = empty;
                        copy[i][j] = tmp;
                        neighbors.add(new Board(copy));
                    }

                    // move right
                    if (j + 1 < dimension()) {
                        int[][] copy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
                        int tmp = copy[i][j + 1];
                        copy[i][j + 1] = empty;
                        copy[i][j] = tmp;
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

        int i = r.nextInt(dimension());
        int j = r.nextInt(dimension());

        while (copy[i][j] == 0) {
            i = r.nextInt(dimension());
            j = r.nextInt(dimension());
        }

        if (j == 0 && copy[i][j + 1] != 0) {
            int tmp = copy[i][j];
            copy[i][j] = copy[i][j + 1];
            copy[i][j + 1] = tmp;
        } else if (copy[i][j - 1] != 0) {
            int tmp = copy[i][j];
            copy[i][j] = copy[i][j - 1];
            copy[i][j - 1] = tmp;
        }

        return new Board(copy);
    }

    private int getProperValue(int i, int j) {
        int properValue = 0;

        if (i != dimension() - 1 || j != dimension() - 1) {
            properValue = i * tiles.length + j + 1;
        }
        return properValue;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {8, 7, 0}
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
