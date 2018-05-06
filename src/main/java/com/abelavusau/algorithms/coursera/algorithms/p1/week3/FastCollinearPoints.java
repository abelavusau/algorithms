package com.abelavusau.algorithms.coursera.algorithms.p1.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by abelavusau on 5/6/18.
 */
public class FastCollinearPoints {
    private final Point[] points;
    private int numberOfSegments = -1;
    private List<LineSegment> segmentList;
    private List<String> strSegments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        Point[] clone = Arrays.copyOf(points, points.length);

        for (int i = 0; i < clone.length; i++) {
            for (int j = 0; j < clone.length; j++) {
                if ((clone[j] == null) || (i != j && clone[i].compareTo(clone[j]) == 0)) {
                    throw new IllegalArgumentException();
                }
            }
        }

        this.points = clone;
    }

    public int numberOfSegments() {
        if (numberOfSegments < 0) {
            numberOfSegments = segments().length;
        }

        return numberOfSegments;
    }

    public LineSegment[] segments() {
        double[] slopes = new double[points.length];
        segmentList = new ArrayList<>();
        strSegments = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                slopes[j] = points[i].slopeTo(points[j]);
            }
            Point[] clone = Arrays.copyOf(points, points.length);
            Arrays.sort(clone, clone[i].slopeOrder());
            Arrays.sort(slopes);
            List<Point> pointsInSegment = new ArrayList<>();

            for (int j = 2; j < slopes.length; j++) {
                if (slopes[j - 1] == slopes[j]) {
                    // border case between zero and first elements: array: 0, 2, 2, 2, ... n
                    if (slopes[j - 2] != slopes[j - 1]) {
                        pointsInSegment.add(clone[j - 1]);
                    }
                    pointsInSegment.add(clone[j]);

                    if (j == slopes.length - 1 && pointsInSegment.size() >= 3) {
                        addSegment(pointsInSegment, clone[0]);
                    }
                } else if (pointsInSegment.size() < 3 && !pointsInSegment.isEmpty()) {
                    pointsInSegment.clear();
                } else if (pointsInSegment.size() >= 3) {
                    addSegment(pointsInSegment, clone[0]);
                }
            }
        }

        LineSegment[] segments = segmentList.toArray(new LineSegment[]{});
        return Arrays.copyOf(segments, segments.length);
    }

    private void addSegment(List<Point> pointsInSegment, Point origin) {
        pointsInSegment.add(origin);
        Collections.sort(pointsInSegment);
        LineSegment segment = new LineSegment(pointsInSegment.get(0), pointsInSegment.get(pointsInSegment.size() - 1));
        if (!strSegments.contains(segment.toString())) {
            strSegments.add(segment.toString());
            segmentList.add(segment);
        }

        pointsInSegment.clear();
    }

    public static void main(String[] args) {
        Point[] points = new Point[]{
                new Point(1, 2),
                new Point(7, 2),
                new Point(9, 4),
                new Point(8, 3),
                new Point(6, 1)
        };

        FastCollinearPoints collinearPoints = new FastCollinearPoints(points);
//        points[5] = new com.abelavusau.algorithms.coursera.algorithms.p1.week3.Point(54, 1001);
//        points[9] = new com.abelavusau.algorithms.coursera.algorithms.p1.week3.Point(10, 129);
        LineSegment[] segments = collinearPoints.segments();
//        int num = collinearPoints.numberOfSegments();
//        points[1] = new com.abelavusau.algorithms.coursera.algorithms.p1.week3.Point(23, 575);
//        segments[0] = new com.abelavusau.algorithms.coursera.algorithms.p1.week3.LineSegment(new com.abelavusau.algorithms.coursera.algorithms.p1.week3.Point(54, 1001), new com.abelavusau.algorithms.coursera.algorithms.p1.week3.Point(10, 129));
//        num = collinearPoints.numberOfSegments();
//        segments = collinearPoints.segments();
        // mutate x 2
        // segments
        // number of segments
        // mutate
        // mutate segments result
        // number
        // segments
        //collinearPoints.segments();
    }
}
