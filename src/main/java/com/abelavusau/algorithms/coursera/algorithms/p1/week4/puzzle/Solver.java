package com.abelavusau.algorithms.coursera.algorithms.p1.week4.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;

public class Solver {
    private final MinPQ<Node> queue = new MinPQ<>(Comparator.comparingInt(o -> o.priority));
    private final MinPQ<Node> twinQueue = new MinPQ<>(Comparator.comparingInt(o -> o.priority));

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        queue.insert(new Node(initial, 0, 0, null));
        twinQueue.insert(new Node(initial.twin(), 0, 0, null));
        while (!queue.min().board.isGoal() && !twinQueue.min().board.isGoal()) {
            Node min = queue.delMin();

            for (Board board : min.board.neighbors()) {
                if (min.previous == null || !board.equals(min.previous.board)) {
                    queue.insert(new Node(board, board.manhattan() + min.moves + 1, min.moves + 1, min));
                }
            }

            Node twinMin = twinQueue.delMin();

            for (Board board : twinMin.board.neighbors()) {
                if (twinMin.previous == null || !board.equals(twinMin.previous.board)) {
                    twinQueue.insert(new Node(board, board.manhattan() + twinMin.moves + 1, twinMin.moves + 1, twinMin));
                }
            }
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return queue.min().board.isGoal() || !twinQueue.min().board.isGoal();
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (isSolvable()) {
            return queue.min().moves;
        } else {
            return -1;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        Deque<Board> solution = null;

        if (isSolvable()) {
            solution = new ArrayDeque<>();
            Node node = queue.min();
            while (node != null) {
                solution.addFirst(node.board);
                node = node.previous;
            }
        }
        return solution;
    }

    // test client (see below)
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();

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
        private final int moves;
        private final Node previous;

        public Node(Board board, int priority, int moves, Node previous) {
            this.board = board;
            this.priority = priority;
            this.moves = moves;
            this.previous = previous;
        }
    }
}
