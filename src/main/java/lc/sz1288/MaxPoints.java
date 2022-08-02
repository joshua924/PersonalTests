package lc.sz1288;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import lombok.ToString;

/**
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane,
 * return the maximum number of points that lie on the same straight line.
 */
public class MaxPoints {
  public int maxPoints(int[][] points) {
    Map<Line, Set<Integer>> lines = new HashMap<>();
    int max = 1;
    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        Line line = getLine(points[i], points[j]);
        if (!lines.containsKey(line)) {
          lines.put(line, new HashSet<>());
        }
        Set<Integer> set = lines.get(line);
        set.add(i);
        set.add(j);
        max = Integer.max(max, set.size());
      }
    }
    return max;
  }

  private Line getLine(int[] a, int[] b) {
    if (a[0] == b[0]) {
      return Line.fromX(a[0]);
    }
    if (a[1] == b[1]) {
      return Line.fromY(a[1]);
    }
    double slope = (a[0] - b[0]) / (double) (a[1] - b[1]);
    double offset = a[1] - slope * a[0];
    return new Line(slope, offset);
  }

  @ToString
  private static class Line {
    Double slope;
    Double offset;
    Integer x;
    Integer y;

    Line(double slope, double offset) {
      this.slope = slope;
      this.offset = offset;
    }

    static Line fromX(int x) {
      Line line = new Line();
      line.x = x;
      return line;
    }

    static Line fromY(int y) {
      Line line = new Line();
      line.y = y;
      return line;
    }

    private Line() {
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Line line = (Line) o;
      return Objects.equals(slope, line.slope) && Objects.equals(offset, line.offset) && Objects.equals(x, line.x) && Objects.equals(y, line.y);
    }

    @Override
    public int hashCode() {
      return Objects.hash(slope, offset, x, y);
    }
  }

  public static void main(String[] args) {
    int[][] points = {{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
    MaxPoints mp = new MaxPoints();
    System.out.println(mp.maxPoints(new int[][]{{1, 1}, {1, -1}, {2, 1}, {2, -1}}));
    System.out.println(mp.maxPoints(points));
  }
}
