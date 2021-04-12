package com.abelavusau.algorithms.coursera.algorithms.p1.week4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class Solver {
    private final MinPQ<Node> queue = new MinPQ<>(Comparator.comparingInt(o -> o.priority));
    private final Board initial;
    private int moves;
    private final static int MOVE_LIMIT = 1000;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initial = initial;
        queue.insert(new Node(initial, 0, null));
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        int[][] tiles = initial.getTiles();
        int inversions = 0;
        int zeroRow = 0;

        for (int i = 0; i < tiles.length * tiles.length; i++) {
            int r1 = i / tiles.length;
            int c1 = i % tiles.length;

            if (tiles[r1][c1] == 0) {
                zeroRow = r1;
            }

            for (int j = i; j < tiles.length * tiles.length; j++) {
                int r2 = j / tiles.length;
                int c2 = j % tiles.length;

                if (tiles[r2][c2] > 0 && tiles[r1][c1] > tiles[r2][c2]) {
                    inversions++;
                }
            }
        }

        moves = inversions;

        if (tiles.length % 2 != 0 && inversions % 2 != 0) {
            moves = -1;
            return false;
        }

        if (tiles.length % 2 == 0 && (inversions + zeroRow) % 2 == 0) {
            moves = -1;
            return false;
        }

        return true;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        moves = 0;
        Deque<Board> solution = null;
        while (!queue.isEmpty() && moves < MOVE_LIMIT) {
            Node min = queue.delMin();

            if (min.board.isGoal()) {
                solution = new ArrayDeque<>();
                while (min != null) {
                    solution.addFirst(min.board);
                    min = min.previous;
                }

                return solution;
            }

            int finalMoves = moves;
            Node finalMin = min;
            min.board.neighbors().forEach(
                    board -> {
                        if (finalMin.previous == null || !board.equals(finalMin.previous.board)) {
                            queue.insert(new Node(board, board.manhattan() + finalMoves, finalMin));
                        }
                    }
            );

            moves++;
        }

        return solution;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        int[][] tiles = new int[n][n];
//        for (int i = 0; i < n; i++)
//            for (int j = 0; j < n; j++)
//                tiles[i][j] = in.readInt();

        /**
         8  6  7
         2  5  4
         1  3  0
         */
        int[][] tiles = new int[][]{
                {0, 1, 3}, {4, 2, 5}, {7, 8, 6}
        };
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

    private static class Node {
        private final Board board;
        private final int priority;
        private final Node previous;

        public Node(Board board, int priority, Node previous) {
            this.board = board;
            this.priority = priority;
            this.previous = previous;
        }
    }
}
