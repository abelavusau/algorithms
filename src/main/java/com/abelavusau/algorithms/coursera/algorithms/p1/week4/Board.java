package com.abelavusau.algorithms.coursera.algorithms.p1.week4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
    private final Random r = new Random();
    private final int[][] tiles;
    private Board goal;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    public void setGoal(Board goal) {
        this.goal = goal;
    }

    public int[][] getTiles() {
        return this.tiles;
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
        int h = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != goal.getTiles()[i][j]) {
                    h++;
                }
            }
        }

        return h;
    }

    // sum of Manhattan distances between tiles and goal
    // abs(x_val - x_goal) + abs(y_val - y_goal)
    public int manhattan() {
        int m = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                if (tiles[i][j] != 0 && tiles[i][j] != goal.getTiles()[i][j]) {
                    int value = tiles[i][j] - 1;
                    int x_goal = value % dimension();
                    int y_goal = value / dimension();
                    m += Math.abs(j - x_goal) + Math.abs(i - y_goal);
                }
            }
        }

        return m;
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
        int i1 = 0, i2 = 0, j1 = 0, j2 = 0;

        while (i1 == i2 && j1 == j2) {
            i1 = r.nextInt(dimension());
            j1 = r.nextInt(dimension());
            i2 = r.nextInt(dimension());
            j2 = r.nextInt(dimension());
        }

        int tmp = copy[i1][j1];
        copy[i1][j1] = copy[i2][j2];
        copy[i2][j2] = tmp;

        return new Board(copy);
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        int[][] tiles = new int[][]{
                {1, 2, 3}, {4, 0, 6}, {8, 5, 7}
        };

        int[][] goalTiles = new int[tiles.length][tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                int value = i * tiles.length + j + 1;

                if (value == tiles.length * tiles.length) {
                    value = 0;
                }

                goalTiles[i][j] = value;
            }
        }

        Board b = new Board(tiles);
        Board goal = new Board(goalTiles);
        b.setGoal(goal);
    }
}
