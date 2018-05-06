package com.abelavusau.algorithms.coursera.algorithms.p1.week3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by abelavusau on 5/5/18.
 */
public class BruteCollinearPoints {
    private final Point[] points;
    private int numberOfSegments = -1;

    public BruteCollinearPoints(Point[] points) {
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
        List<LineSegment> segmentList = new ArrayList<>();
        List<String> strSegments = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                slopes[j] = points[i].slopeTo(points[j]);
            }

            for (int k = 0; k < slopes.length; k++) {
                List<Point> pointsInSegment = new ArrayList<>();
                for (int j = 0; j < slopes.length; j++) {
                    if (slopes[k] == slopes[j]) {
                        pointsInSegment.add(points[j]);
                    }
                }

                if (pointsInSegment.size() >= 3) {
                    pointsInSegment.add(points[i]);
                    Collections.sort(pointsInSegment);
                    LineSegment segment = new LineSegment(pointsInSegment.get(0), pointsInSegment.get(pointsInSegment.size() - 1));
                    if (!strSegments.contains(segment.toString())) {
                        strSegments.add(segment.toString());
                        segmentList.add(segment);
                    }
                }
            }
        }

        LineSegment[] segments = segmentList.toArray(new LineSegment[]{});
        return Arrays.copyOf(segments, segments.length);
    }

    public static void main(String[] args) {
        Point p = new Point(6, 9);
        Point q = new Point(7, 9);
        Point[] points = new Point[]{
                new Point(1, 2),
                new Point(0, 0),
                new Point(3, 3),
                new Point(2, 2),
                new Point(11, 5),
                new Point(8, 8),
                new Point(6, 3),
                new Point(12, 4),
                new Point(4, 4),
                new Point(13, 3)
        };

        p.compareTo(q);

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        System.out.println(collinear.segments());
    }
}
