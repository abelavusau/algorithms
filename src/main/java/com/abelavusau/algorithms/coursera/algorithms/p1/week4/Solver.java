package com.abelavusau.algorithms.coursera.algorithms.p1.week4;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class Solver {
    private final MinPQ<Node> queue = new MinPQ<>(Comparator.comparingInt(o -> o.priority));
    int moves;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        queue.insert(new Node(initial, 0, null));
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return moves > -1;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        moves = 0;
        Deque<Board> solution = new ArrayDeque<>();
        while (true) {
            Node min = queue.delMin();

            if (min.board.isGoal()) {
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
                        if (!board.equals(finalMin.previous.board)) {
                            queue.insert(new Node(board, board.manhattan() + finalMoves, finalMin));
                        }
                    }
            );

            moves++;
        }
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
        int[][] tiles = new int[][]{
                {1, 3, 0}, {4, 2, 5}, {7, 8, 6}
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
