package lc.sz1288;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Design an iterator to flatten a 2D vector. It should support the next and hasNext operations.
 * <p>
 * Implement the Vector2D class:
 * <p>
 * Vector2D(int[][] vec) initializes the object with the 2D vector vec.
 * <p>
 * next() returns the next element from the 2D vector and moves the pointer one step forward.
 * You may assume that all the calls to next are valid.
 * <p>
 * hasNext() returns true if there are still some elements in the vector, and false otherwise.
 */
public class Vector2D {
  private List<List<Integer>> vec;
  private int arrIndex;
  private int index;

  public Vector2D(List<List<Integer>> vec) {
    this.vec = vec;
    this.arrIndex = 0;
    this.index = 0;
  }

  public int next() {
    return vec
        .get(arrIndex)
        .get(index++);
  }

  public boolean hasNext() {
    while (arrIndex < vec.size() && index >= vec
        .get(arrIndex)
        .size()) {
      arrIndex++;
      index = 0;
    }
    return arrIndex < vec.size();
  }

  public static void main(String[] args) {
    List<List<Integer>> vec = Arrays.asList(
        Arrays.asList(1, 2),
        Collections.singletonList(3),
        Collections.singletonList(4),
        Collections.emptyList(),
        Collections.emptyList(),
        Collections.emptyList()
    );
    Vector2D solution = new Vector2D(vec);
    while (solution.hasNext()) {
      System.out.println(solution.next());
    }
  }
}
