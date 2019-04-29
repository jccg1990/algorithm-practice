package week3;

import java.util.*;
import java.util.stream.Collectors;

public class CoveringSegments {

    private static ArrayList<Integer> optimalPoints(Segment[] segments) {
        ArrayList<Integer> points = new ArrayList<>();

        ArrayList<Segment> sList = new ArrayList<>(Arrays.stream(segments).collect(Collectors.toList()));
        Collections.sort(sList);

        return optimalPointsRecursive(sList, points);
    }

    private static ArrayList<Integer> optimalPointsRecursive(ArrayList<Segment> segments, ArrayList<Integer> points) {
        if (segments.size() == 0) return points;

        ArrayList<Segment> segToRemove = new ArrayList<>();

        for (Segment seg : segments) {
            if (seg.start > segments.get(0).end) break;

            if (seg.start <= segments.get(0).end && seg.end >= segments.get(0).end) {

                segToRemove.add(seg);
            }
        }

        segToRemove.add(segments.get(0));

        points.add(segments.get(0).end);
        segments.removeAll(segToRemove);


        return optimalPointsRecursive(segments, points);
    }

    private static class Segment implements Comparable<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            return Integer.compare(end, o.end);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        ArrayList<Integer> points = optimalPoints(segments);
        System.out.println(points.size());
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
